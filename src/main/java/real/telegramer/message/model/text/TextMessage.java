package real.telegramer.message.model.text;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import real.telegramer.message.model.AbstractMessage;
import real.telegramer.message.model.data.UrlData;

import java.util.Collections;
import java.util.List;

public class TextMessage extends AbstractMessage {

    public SendMessage createTextMessage(Long chatId, String text) {
        SendMessage answer = new SendMessage();
        answer.setChatId(String.valueOf(chatId));
        answer.enableMarkdown(true);
        answer.setText(text);
        return answer;
    }

    public SendMessage createTextMessageWithoutMarkdown(Long chatId, String text) {
        SendMessage answer = new SendMessage();
        answer.setChatId(String.valueOf(chatId));
        answer.setText(text);
        answer.enableMarkdown(false);
        return answer;
    }

    public SendMessage createTextMessage(Long chatId, String text, ReplyKeyboard keyboard) {
        var message = createTextMessage(chatId, text);
        message.setReplyMarkup(keyboard);
        return message;
    }

    public SendMessage createTextMessage(Long chatId, String text, List<List<String>> buttons) {
        return createTextMessage(chatId, text, createReplayMarkup(buttons));
    }

    public SendMessage createTextMessage(Long chatId, String text, UrlData urlData) {
        SendMessage message = createTextMessage(chatId, text);
        message.setReplyMarkup(createUrlInlineMarkup(Collections.singletonList(urlData)));
        return message;
    }

    public SendMessage createTextMessage(Long chatId, String text, UrlData urlData,  List<List<String>> buttons) {
        SendMessage message = createTextMessage(chatId, text, urlData);
        message.setReplyMarkup(createReplayMarkup(buttons));
        return message;
    }
}
