package real.telegramer.message.fabric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import real.telegramer.db.repository.MessageRepository;
import real.telegramer.message.dictionary.Commands;
import real.telegramer.message.dictionary.buttons.menu.*;
import real.telegramer.message.fabric.photo.PhotoFabric;
import real.telegramer.message.fabric.text.TextFabric;
import real.telegramer.message.fabric.video.VideoFabric;
import real.telegramer.service.AdminService;
import real.telegramer.service.ChatService;
import real.telegramer.service.SenderService;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnswerFabric {

    private final TextFabric textFabric;
    private final PhotoFabric photoFabric;
    private final VideoFabric videoFabric;
    private final ChatService chatService;
    private final AdminService adminService;
    private final MessageRepository messageRepository;
    private final SenderService senderService;

    public AnswerFabric(@Autowired PhotoFabric photoFabric,
                        @Autowired TextFabric textFabric,
                        @Autowired ChatService chatService,
                        @Autowired VideoFabric videoFabric,
                        @Autowired AdminService adminService,
                        @Autowired MessageRepository messageRepository,
                        @Autowired SenderService senderService) {
        this.photoFabric = photoFabric;
        this.textFabric = textFabric;
        this.chatService = chatService;
        this.adminService = adminService;
        this.videoFabric = videoFabric;
        this.messageRepository = messageRepository;
        this.senderService = senderService;
    }

    public Object createAnswer(Message message) {
        if (!adminService.process(message)) return null;
        List mailing = senderService.process(message);
        if (mailing.size() > 0) return mailing;
        var chatId = message.getChatId();
        var text = message.getText();
        var from = message.getFrom();
        if (chatService.getOrderServiceForCurrentUser(chatId).isOrder()) {
            if (text.equals(BackMenu.BACK.getText()) || text.equals(Commands.START.getText())) {
                chatService.getOrderServiceForCurrentUser(chatId).sendOrder();
            } else {
                return createAnswerForOrderText(text, chatId, from);
            }
        }
        var answer = createAnswer(text, chatId);
        if (answer == null) {
            return createAnswerForUnknownText(text, chatId, from);
        }
        return answer;
    }

    public Object createAnswer(String text, Long chatId) {
        var command = Commands.fromValue(text);
        if (command.isPresent()) {
            chatService.getBackServiceForCurrentUser(chatId).manageStart();
            return createAnswerForStart(command.get(), chatId);
        }
        var mainMenu = MainMenu.fromValue(text);
        if (mainMenu.isPresent()) {
            chatService.getBackServiceForCurrentUser(chatId).manageTransition(text);
            return createAnswerForMainMenu(mainMenu.get(), chatId);
        }
        var aboutUsMenu = AboutUsMenu.fromValue(text);
        if (aboutUsMenu.isPresent()) {
            return createAnswerForAboutUsMenu(aboutUsMenu.get(), chatId);
        }
        var servicesMenu = ServicesMenu.fromValue(text);
        if (servicesMenu.isPresent()) {
            var service = servicesMenu.get();
            if (service != ServicesMenu.EDUCATION
                    && service != ServicesMenu.PROJECT_BY_KEY) {
                chatService.getBackServiceForCurrentUser(chatId).manageTransition(text);
            }
            return createAnswerForServicesMenu(service, chatId);
        }
        var orderMenu = OrderMenu.fromValue(text);
        if (orderMenu.isPresent()) {
            chatService.getBackServiceForCurrentUser(chatId).manageTransition(text);
            return createAnswerForOrderMenu(orderMenu.get(), chatId);
        }
        var orderCommunicationMenu = OrderCommunicationMenu.fromValue(text);
        if (orderCommunicationMenu.isPresent()) {
            var orderCommand = orderCommunicationMenu.get();
            if (orderCommand == OrderCommunicationMenu.CALL_ME) {
                chatService.getOrderServiceForCurrentUser(chatId).setOrder();
            }
            chatService.getBackServiceForCurrentUser(chatId).manageTransition(text);
            return createAnswerForOrderCommunicationMenu(orderCommand, chatId);
        }
        var writeMenu = WriteMenu.fromValue(text);
        if (writeMenu.isPresent()) {
            var write = writeMenu.get();
            if (!write.equals(WriteMenu.OK)) {
                chatService.getBackServiceForCurrentUser(chatId).manageTransition(text);
            }
            return createAnswerForWriteMenu(writeMenu.get(), chatId);
        }
        var hardMenu = HardMenu.fromValue(text);
        if (hardMenu.isPresent()) {
            chatService.getOrderServiceForCurrentUser(chatId).setOrder();
            chatService.getBackServiceForCurrentUser(chatId).manageTransition(text);
            return createAnswerForHardMenu(hardMenu.get(), chatId);
        }
        var backMenu = BackMenu.fromValue(text);
        if (backMenu.isPresent()) {
            return createAnswer(chatService.getBackServiceForCurrentUser(chatId).manageBack(), chatId);
        }
        return null;
    }

    private Object createMessageForNotification(Long chatId, User from, String text) {
        Long idAdminChat = adminService.getChatId();
        String section = chatService.getBackServiceForCurrentUser(chatId).getInformationAboutSection();
        messageRepository.save(new real.telegramer.db.model.Message(text, from.getUserName(), chatId));
        return textFabric.createNotificationForAdmin(idAdminChat, from, text, section);
    }

    private Object createMessageForUnknownNotification(User from, String text, Long chatId) {
        Long idAdminChat = adminService.getChatId();
        messageRepository.save(new real.telegramer.db.model.Message(text, from.getUserName(), chatId));
        return textFabric.createNotificationForAdmin(idAdminChat, from, text);
    }

    private List<Object> createAnswerForOrderText(String text, Long chatId, User from) {
        List list = new ArrayList();
        list.add(createMessageForNotification(chatId, from, text));
        list.add(textFabric.createAnswerForOrderText(chatId));
        list.add(createAnswer(Commands.SECOND_START.getText(), chatId));
        chatService.getOrderServiceForCurrentUser(chatId).sendOrder();
        return list;
    }

    private Object createAnswerForUnknownText(String text, Long chatId, User from) {
        List list = new ArrayList();
        list.add(createMessageForUnknownNotification(from, text, chatId));
        list.add(textFabric.createAnswerForUnknownText(chatId));
        return list;
    }

    private Object createAnswerForHardMenu(HardMenu hardMenu, Long chatId) {
        if (hardMenu == HardMenu.RECALL_ME) {
            return textFabric.createAnswerForRecallMe(chatId);
        }
        return null;
    }

    private Object createAnswerForWriteMenu(WriteMenu writeMenu, Long chatId) {
        return switch (writeMenu) {
            case HARD -> photoFabric.createAnswerForHard(chatId);
            case OK -> textFabric.createAnswerForOk(chatId);
        };
    }

    private Object createAnswerForOrderMenu(OrderMenu orderMenu, Long chatId) {
        if (orderMenu == OrderMenu.ORDER) {
            return textFabric.createAnswerForOrder(chatId);
        }
        return null;
    }

    private Object createAnswerForStart(Commands command, Long chatId) {
        return switch (command) {
            case START -> createAnswerForMain(chatId);
            case SECOND_START -> textFabric.createAnswerForMain(chatId);
        };
    }

    private Object createAnswerForMain(Long chatId) {
        List list = new ArrayList();
        list.add(photoFabric.createAnswerForMain(chatId));
        list.add(textFabric.createAnswerForMain(chatId));
        return list;
    }

    private Object createAnswerForMainMenu(MainMenu mainMenu, Long chatId) {
        List<Message> list = new ArrayList<>();
        return switch (mainMenu) {
            case SEND_LETTER -> textFabric.createAnswerForOrder(chatId);
            case ABOUT_US -> createAnswerForAboutUsInMainMenu(chatId);
            case SERVICES -> photoFabric.createAnswerForServices(chatId);
        };
    }

    private Object createAnswerForAboutUsInMainMenu(Long chatId) {
        List list = new ArrayList<>();
        list.add(videoFabric.createAnswerForAboutUs(chatId));
        list.add(textFabric.createAnswerForAboutUs(chatId));
        return list;
    }

    private Object createAnswerForAboutUsMenu(AboutUsMenu aboutUsMenu, Long chatId) {
        return switch (aboutUsMenu) {
            case FEEDBACK -> photoFabric.createAnswerForFeedback(chatId);
            case TEAM -> textFabric.createAnswerForTeam(chatId);
            case PROJECTS -> photoFabric.createAnswerForProjects(chatId);
        };
    }

    private Object createAnswerForServicesMenu(ServicesMenu servicesMenu, Long chatId) {
        return switch (servicesMenu) {
            case EDUCATION -> photoFabric.createAnswerForEducation(chatId);
            case PROJECT_BY_KEY -> photoFabric.createAnswerForProjectsByKey(chatId);
            case DESIGN -> createAnswerForDesignInServiceMenu(chatId);
            case OTHER -> photoFabric.createAnswerForOther(chatId);
            case BOTS -> createAnswerForBots(chatId);
        };
    }

    private Object createAnswerForBots(Long chatId) {
        List list = new ArrayList<>();
        list.add(photoFabric.createAnswerForBots(chatId));
        list.add(textFabric.createAnswerForBots(chatId));
        return list;
    }

    private Object createAnswerForDesignInServiceMenu(Long chatId) {
        List list = new ArrayList<>();
        list.add(photoFabric.createAnswerForDesign(chatId));
        list.add(textFabric.createAnswerForDesign(chatId));
        return list;
    }

    private Object createAnswerForOrderCommunicationMenu(OrderCommunicationMenu orderCommunicationMenu, Long chatId) {
        return switch (orderCommunicationMenu) {
            case WRITE_ME -> createAnswerForWriteMe(chatId);
            case CALL_ME -> textFabric.createAnswerForRecallMe(chatId);
        };
    }

    private Object createAnswerForWriteMe(Long chatId) {
        List list = new ArrayList<>();
        list.add(textFabric.createAnswerForWriteMe(chatId));
        list.add(textFabric.createAnswerForWriteMeWithKeyBoard(chatId));
        return list;
    }
}
