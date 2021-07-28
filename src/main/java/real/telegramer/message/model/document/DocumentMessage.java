package real.telegramer.message.model.document;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import real.telegramer.message.model.AbstractMessage;

import java.util.List;

public class DocumentMessage extends AbstractMessage {
    public SendDocument createDocumentMessage(Long chatId, String text) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(String.valueOf(chatId));
        sendDocument.setCaption(text);
        sendDocument.setParseMode(ParseMode.MARKDOWN);
        return sendDocument;
    }

    public SendDocument createDocumentMessage(Long chatId, String text, InputFile document) {
        SendDocument message = this.createDocumentMessage(chatId, text);
        message.setDocument(document);
        return message;
    }

    public SendDocument createDocumentMessage(Long chatId, String text, InputFile photo, List<List<String>> buttons) {
        SendDocument message = createDocumentMessage(chatId, text, photo);
        message.setReplyMarkup(createReplayMarkup(buttons));
        return message;
    }

}
