package real.telegramer.message.fabric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.Message;
import real.telegramer.message.dictionary.Commands;
import real.telegramer.message.dictionary.buttons.menu.*;
import real.telegramer.message.fabric.document.DocumentFabric;
import real.telegramer.message.fabric.photo.PhotoFabric;
import real.telegramer.message.fabric.text.TextFabric;
import real.telegramer.message.fabric.video.VideoFabric;
import real.telegramer.message.service.BackService;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnswerFabric {

    private final TextFabric textFabric;
    private final PhotoFabric photoFabric;
    private final VideoFabric videoFabric;
    private final DocumentFabric documentFabric;
    private final BackService backService;

    public AnswerFabric(@Autowired PhotoFabric photoFabric,
                        @Autowired TextFabric textFabric,
                        @Autowired VideoFabric videoFabric,
                        @Autowired DocumentFabric documentFabric,
                        @Autowired BackService backService) {
        this.photoFabric = photoFabric;
        this.textFabric = textFabric;
        this.videoFabric = videoFabric;
        this.documentFabric = documentFabric;
        this.backService = backService;
    }

    public Object createAnswer(String text, Long chatId) {
        var command = Commands.fromValue(text);
        if (command.isPresent()) {
            backService.manageStart();
            return createAnswerForStart(command.get(), chatId);
        }
        var mainMenu = MainMenu.fromValue(text);
        if (mainMenu.isPresent()) {
            backService.manageTransition(text);
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
                backService.manageTransition(text);
            }
            return createAnswerForServicesMenu(service, chatId);
        }
        var orderMenu = OrderMenu.fromValue(text);
        if (orderMenu.isPresent()) {
            backService.manageTransition(text);
            return createAnswerForOrderMenu(orderMenu.get(), chatId);
        }
        var orderCommunicationMenu = OrderCommunicationMenu.fromValue(text);
        if (orderCommunicationMenu.isPresent()) {
            backService.manageTransition(text);
            return createAnswerForOrderCommunicationMenu(orderCommunicationMenu.get(), chatId);
        }
        var writeMenu = WriteMenu.fromValue(text);
        if (writeMenu.isPresent()) {
            var write = writeMenu.get();
            if (!write.equals(WriteMenu.OK)) {
                backService.manageTransition(text);
            }
            return createAnswerForWriteMenu(writeMenu.get(), chatId);
        }
        var hardMenu = HardMenu.fromValue(text);
        if (hardMenu.isPresent()) {
            backService.manageTransition(text);
            return createAnswerForHardMenu(hardMenu.get(), chatId);
        }
        var backMenu = BackMenu.fromValue(text);
        if (backMenu.isPresent()) {
            return createAnswer(backService.manageBack(), chatId);
        }
        return createAnswerForUnknownText(chatId);
    }

    private Object createAnswerForUnknownText(Long chatId) {
        return textFabric.createAnswerForUnknownText(chatId);
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
