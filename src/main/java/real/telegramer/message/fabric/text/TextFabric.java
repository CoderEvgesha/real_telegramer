package real.telegramer.message.fabric.text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import real.telegramer.message.dictionary.Text;
import real.telegramer.message.dictionary.buttons.Url;
import real.telegramer.message.fabric.buttons.InterfaceFabric;
import real.telegramer.message.fabric.url.UrlFabric;
import real.telegramer.message.model.data.UrlData;
import real.telegramer.message.model.text.TextMessage;

@Component
public class TextFabric {

    private final UrlFabric urlFabric;
    private final TextMessage textMessage;

    public TextFabric(@Autowired UrlFabric urlFabric) {
        this.urlFabric = urlFabric;
        this.textMessage = new TextMessage();
    }

    public SendMessage createAnswerForOrder(Long chatId) {
        return textMessage.createTextMessage(chatId, Text.ORDER.getText(),
                InterfaceFabric.getButtonsForOrderCommunicationMenu());
    }

    public SendMessage createAnswerForWriteMe(Long chatId) {
        return textMessage.createTextMessage(chatId, Text.WRITE_ME.getText());
    }

    public SendMessage createAnswerForWriteMeWithKeyBoard(Long chatId) {
        return textMessage.createTextMessage(chatId, Text.CHOOSE_OPTION.getText(),
                InterfaceFabric.getButtonsForWriteMenu());
    }

    public SendMessage createAnswerForOk(Long chatId) {
        return textMessage.createTextMessage(chatId, Text.OK.getText(),
                new UrlData(Url.WRITE.getText(), urlFabric.write));
    }

    public SendMessage createAnswerForRecallMe(Long chatId) {
        return textMessage.createTextMessage(chatId, Text.RECALL_ME.getText(),
                InterfaceFabric.getButtonsForBackMenu());
    }

    public SendMessage createAnswerForUnknownText(Long chatId) {
        return textMessage.createTextMessage(chatId, Text.NOT_KNOW.getText());
    }

    public SendMessage createAnswerForAboutUs(Long chatId) {
        return textMessage.createTextMessage(chatId, Text.CHOOSE_OPTION.getText(),
                InterfaceFabric.getButtonsForAboutUsMenu());
    }

    public SendMessage createAnswerForDesign(Long chatId) {
        return textMessage.createTextMessage(chatId, Text.CHOOSE_OPTION.getText(),
                InterfaceFabric.getButtonsForServicesOrderMenu());
    }

    public SendMessage createAnswerForOrderText(Long chatId) {
        return textMessage.createTextMessage(chatId, Text.ORDER_ANSWER.getText());
    }

    public SendMessage createNotificationForAdmin(Long chatId, User user, String text, String section) {
        String fullMessage;
        String username = user.getUserName();
        if (username == null || username.isBlank()) {
            fullMessage = String.format(Text.NOTIFICATION_ANONYMOUS.getText(), section, text);
        } else {
            fullMessage = String.format(Text.NOTIFICATION.getText(), username, section, text);
        }
        return textMessage.createTextMessageWithoutMarkdown(chatId, fullMessage);
    }

    public SendMessage createNotificationForAdmin(Long chatId, User user, String text) {
        String fullMessage;
        String username = user.getUserName();
        if (username == null || username.isBlank()) {
            fullMessage = String.format(Text.UNKNOWN_ANONYMOUS_NOTIFICATION.getText(), text);
        } else {
            fullMessage = String.format(Text.UNKNOWN_NOTIFICATION.getText(), username, text);
        }
        return textMessage.createTextMessageWithoutMarkdown(chatId, fullMessage);
    }

    public SendMessage createAnswerForMain(Long chatId) {
        return textMessage.createTextMessage(chatId, Text.CHOOSE_OPTION.getText(),
                InterfaceFabric.getButtonsForMainMenu());
    }

    public SendMessage createMessageForMailing(Long chatId, String text) {
        return textMessage.createTextMessage(chatId, text);
    }

    public SendMessage createAnswerForBots(Long chatId) {
        return textMessage.createTextMessage(chatId, Text.CHOOSE_OPTION.getText(),
                InterfaceFabric.getButtonsForServicesOrderMenu());
    }
}
