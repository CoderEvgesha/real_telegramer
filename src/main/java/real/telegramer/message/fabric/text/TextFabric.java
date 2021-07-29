package real.telegramer.message.fabric.text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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

    public SendMessage createAnswerForFeedback(Long chatId) {
        return textMessage.createTextMessage(chatId, Text.FEEDBACK.getText(),
                new UrlData(Url.SEE.getText(), urlFabric.feedback));
    }

    public SendMessage createAnswerForTeam(Long chatId) {
        return textMessage.createTextMessage(chatId, urlFabric.team);
    }

    public SendMessage createAnswerForEducation(Long chatId) {
        return textMessage.createTextMessage(chatId, Text.EDUCATION.getText(),
                new UrlData(Url.SEE.getText(), urlFabric.education));
    }

    public SendMessage createAnswerForProjectsByKey(Long chatId) {
        return textMessage.createTextMessage(chatId, Text.PROJECTS_BY_KEY.getText(),
                new UrlData(Url.SEE.getText(), urlFabric.projectsByKey));
    }

    public SendMessage createAnswerForBots(Long chatId) {
        return textMessage.createTextMessage(chatId, Text.BOTS.getText(),
                InterfaceFabric.getButtonsForServicesOrderMenu());
    }

    public SendMessage createAnswerForOrder(Long chatId) {
        return textMessage.createTextMessage(chatId, Text.ORDER.getText(),
                InterfaceFabric.getButtonsForOrderCommunicationMenu());
    }

    public SendMessage createAnswerForWriteMe(Long chatId) {
        return textMessage.createTextMessage(chatId, Text.WRITE_ME.getText(),
                InterfaceFabric.getButtonsForWriteMenu());
    }

    public SendMessage createAnswerForHard(Long chatId) {
        return textMessage.createTextMessage(chatId, Text.HARD.getText(),
                InterfaceFabric.getButtonsForHardMenu());
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
        return textMessage.createTextMessage(chatId, Text.ABOUT_US.getText(),
                InterfaceFabric.getButtonsForAboutUsMenu());
    }

    public SendMessage createAnswerForDesign(Long chatId) {
        return textMessage.createTextMessage(chatId, Text.DESIGN.getText(),
                InterfaceFabric.getButtonsForServicesOrderMenu());
    }

    public SendMessage createAnswerForOrderText(Long chatId) {
        return textMessage.createTextMessage(chatId, Text.ORDER_ANSWER.getText());
    }
}
