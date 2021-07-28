package real.telegramer.message.model.photo;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import real.telegramer.message.model.AbstractMessage;
import real.telegramer.message.model.data.CallbackData;
import real.telegramer.message.model.data.UrlData;

import java.util.Collections;
import java.util.List;

public class PhotoMessage extends AbstractMessage {

    public SendPhoto createPhotoMessage(Long chatId, String text) {
        SendPhoto sendPhoto = createPhotoMessage(chatId);
        sendPhoto.setCaption(text);
        sendPhoto.setParseMode(ParseMode.MARKDOWN);
        return sendPhoto;
    }

    public SendPhoto createPhotoMessage(Long chatId) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(String.valueOf(chatId));
        return sendPhoto;
    }

    public SendPhoto createPhotoMessage(Long chatId, String text, InputFile photo) {
        SendPhoto message = createPhotoMessage(chatId, text);
        message.setPhoto(photo);
        return message;
    }

    public SendPhoto createPhotoMessage(Long chatId, InputFile photo) {
        SendPhoto message = createPhotoMessage(chatId);
        message.setPhoto(photo);
        return message;
    }

    public SendPhoto createPhotoMessage(Long chatId, String text, InputFile photo, UrlData urlData) {
        SendPhoto message = createPhotoMessage(chatId, text, photo);
        message.setReplyMarkup(createUrlInlineMarkup(Collections.singletonList(urlData)));
        return message;
    }

    public SendPhoto createPhotoMessage(Long chatId, String text, InputFile photo, List<List<String>> buttons) {
        SendPhoto message = createPhotoMessage(chatId, text, photo);
        message.setReplyMarkup(createReplayMarkup(buttons));
        return message;
    }

    public SendPhoto createPhotoMessage(Long chatId, String text, InputFile photo, CallbackData callbackData) {
        SendPhoto message = createPhotoMessage(chatId, text, photo);
        message.setReplyMarkup(createCallbackInlineMarkup(Collections.singletonList(callbackData)));
        return message;
    }

    public SendPhoto createPhotoMessage(Long chatId, InputFile photo, UrlData urlData) {
        SendPhoto message = createPhotoMessage(chatId, photo);
        message.setReplyMarkup(createUrlInlineMarkup(Collections.singletonList(urlData)));
        return message;
    }
}
