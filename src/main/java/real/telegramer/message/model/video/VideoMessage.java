package real.telegramer.message.model.video;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import real.telegramer.message.model.AbstractMessage;
import real.telegramer.message.model.data.CallbackData;
import real.telegramer.message.model.data.UrlData;

import java.util.Collections;
import java.util.List;

public class VideoMessage extends AbstractMessage {

    public SendVideo createVideoMessage(Long chatId) {
        SendVideo sendVideo = new SendVideo();
        sendVideo.setChatId(String.valueOf(chatId));
        sendVideo.setParseMode(ParseMode.MARKDOWN);
        return sendVideo;
    }

    public SendVideo createVideoMessage(Long chatId, InputFile video) {
        SendVideo message = createVideoMessage(chatId);
        message.setVideo(video);
        return message;
    }

    public SendVideo createVideoMessage(Long chatId, InputFile video, UrlData urlData) {
        SendVideo message = createVideoMessage(chatId, video);
        message.setReplyMarkup(createUrlInlineMarkup(Collections.singletonList(urlData)));
        return message;
    }

    public SendVideo createVideoMessage(Long chatId, InputFile video, List<List<String>> buttons) {
        SendVideo message = createVideoMessage(chatId, video);
        message.setReplyMarkup(createReplayMarkup(buttons));
        return message;
    }

    public SendVideo createVideoMessage(Long chatId, InputFile video, CallbackData callbackData) {
        SendVideo message = createVideoMessage(chatId, video);
        message.setReplyMarkup(createCallbackInlineMarkup(Collections.singletonList(callbackData)));
        return message;
    }
}
