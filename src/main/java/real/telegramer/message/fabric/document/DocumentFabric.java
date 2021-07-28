package real.telegramer.message.fabric.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import real.telegramer.message.dictionary.Text;
import real.telegramer.message.fabric.buttons.InterfaceFabric;
import real.telegramer.message.fabric.file.FileFabric;
import real.telegramer.message.fabric.url.UrlFabric;
import real.telegramer.message.model.document.DocumentMessage;
import real.telegramer.message.model.photo.PhotoMessage;

@Component
public class DocumentFabric {

    private final DocumentMessage documentMessage;
    private final FileFabric fileFabric;

    public DocumentFabric(@Autowired FileFabric fileFabric) {
        this.documentMessage = new DocumentMessage();
        this.fileFabric = fileFabric;
    }

    public SendDocument createAnswerForDesign(Long chatId) {
        return documentMessage.createDocumentMessage(chatId,
                Text.DESIGN.getText(), fileFabric.createDocForDesign(),
                InterfaceFabric.getButtonsForServicesOrderMenu());
    }

}
