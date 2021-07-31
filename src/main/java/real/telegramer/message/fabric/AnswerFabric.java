package real.telegramer.message.fabric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import real.telegramer.message.dictionary.Commands;
import real.telegramer.message.dictionary.buttons.menu.*;
import real.telegramer.message.fabric.photo.PhotoFabric;
import real.telegramer.message.fabric.text.TextFabric;
import real.telegramer.message.fabric.video.VideoFabric;
import real.telegramer.message.service.AdminService;
import real.telegramer.message.service.ChatService;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnswerFabric {

    private final TextFabric textFabric;
    private final PhotoFabric photoFabric;
    private final VideoFabric videoFabric;
    private final ChatService chatService;
    private final AdminService adminService;

    public AnswerFabric(@Autowired PhotoFabric photoFabric,
                        @Autowired TextFabric textFabric,
                        @Autowired VideoFabric videoFabric,
                        @Autowired ChatService chatService,
                        @Autowired AdminService adminService) {
        this.photoFabric = photoFabric;
        this.textFabric = textFabric;
        this.videoFabric = videoFabric;
        this.chatService = chatService;
        this.adminService = adminService;
    }

    public Object createAnswer(Message message) {
        if (!adminService.process(message)) return null;
        var chatId = message.getChatId();
        var text = message.getText();
        var from = message.getFrom();
        if (chatService.getOrderServiceForCurrentUser(chatId).isOrder()) {
            return createAnswerForOrderText(text, chatId, from);
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
        String section = chatService.getBackServiceForCurrentUser(chatId).getInformationAboutSection();
        Long idAdminChat = adminService.getChatId();
        return textFabric.createNotificationForAdmin(idAdminChat, from, text, section);
    }

    private Object createMessageForUnknownNotification(User from, String text) {
        Long idAdminChat = adminService.getChatId();
        return textFabric.createNotificationForAdmin(idAdminChat, from, text);
    }

    private List<Object> createAnswerForOrderText(String text, Long chatId, User from) {
        List list = new ArrayList();
        list.add(createMessageForNotification(chatId, from, text));
        list.add(textFabric.createAnswerForOrderText(chatId));
        list.add(createAnswer(Commands.START.getText(), chatId));
        chatService.getOrderServiceForCurrentUser(chatId).sendOrder();
        return list;
    }

    private Object createAnswerForUnknownText(String text, Long chatId, User from) {
        List list = new ArrayList();
        list.add(createMessageForUnknownNotification(from, text));
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
            case HARD -> textFabric.createAnswerForHard(chatId);
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
        if (command == Commands.START) {
            return photoFabric.createAnswerForMain(chatId);
        }
        return null;
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
            case FEEDBACK -> textFabric.createAnswerForFeedback(chatId);
            case TEAM -> textFabric.createAnswerForTeam(chatId);
            case PROJECTS -> photoFabric.createAnswerForProjects(chatId);
        };
    }

    private Object createAnswerForServicesMenu(ServicesMenu servicesMenu, Long chatId) {
        return switch (servicesMenu) {
            case EDUCATION -> textFabric.createAnswerForEducation(chatId);
            case PROJECT_BY_KEY -> textFabric.createAnswerForProjectsByKey(chatId);
            case DESIGN -> createAnswerForDesignInServiceMenu(chatId);
            case OTHER -> photoFabric.createAnswerForOther(chatId);
            case BOTS -> textFabric.createAnswerForBots(chatId);
        };
    }

    private Object createAnswerForDesignInServiceMenu(Long chatId) {
        List list = new ArrayList<>();
        list.add(photoFabric.createAnswerForDesign(chatId));
        list.add(textFabric.createAnswerForDesign(chatId));
        return list;
    }

    private Object createAnswerForOrderCommunicationMenu(OrderCommunicationMenu orderCommunicationMenu, Long chatId) {
        return switch (orderCommunicationMenu) {
            case WRITE_ME -> textFabric.createAnswerForWriteMe(chatId);
            case CALL_ME -> textFabric.createAnswerForRecallMe(chatId);
        };
    }
}
