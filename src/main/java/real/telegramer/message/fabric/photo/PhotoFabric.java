package real.telegramer.message.fabric.photo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import real.telegramer.message.dictionary.Text;
import real.telegramer.message.dictionary.buttons.Details;
import real.telegramer.message.dictionary.buttons.Url;
import real.telegramer.message.fabric.buttons.InterfaceFabric;
import real.telegramer.message.fabric.file.FileFabric;
import real.telegramer.message.fabric.url.UrlFabric;
import real.telegramer.message.model.data.UrlData;
import real.telegramer.message.model.photo.PhotoMessage;

@Component
public class PhotoFabric {

    private final PhotoMessage photoMessage;
    private final FileFabric fileFabric;
    private final UrlFabric urlFabric;

    public PhotoFabric(@Autowired FileFabric fileFabric,
                       @Autowired UrlFabric urlFabric) {
        this.photoMessage = new PhotoMessage();
        this.fileFabric = fileFabric;
        this.urlFabric = urlFabric;
    }

    public SendPhoto createAnswerForMain(Long chatId) {
        return photoMessage.createPhotoMessage(chatId, Text.WELCOME.getText(),
                fileFabric.createPhotoForMain(), InterfaceFabric.getButtonsForMainMenu());
    }

    public SendPhoto createAnswerForProjects(Long chatId) {
        return photoMessage.createPhotoMessage(chatId, Text.PROJECTS.getText(),
                fileFabric.createPictureProjects(),
                new UrlData(Details.DETAILS.getText(), urlFabric.details));
    }

    public SendPhoto createAnswerForServices(Long chatId) {
        return photoMessage.createPhotoMessage(chatId, Text.SERVICES.getText(),
                fileFabric.createPhotoForServices(), InterfaceFabric.getButtonsForServicesMenu());
    }

    public SendPhoto createAnswerForOther(Long chatId) {
        return photoMessage.createPhotoMessage(chatId, Text.OTHER.getText(),
                fileFabric.createPhotoForOther(), InterfaceFabric.getButtonsForServicesOrderMenu());
    }

    public SendPhoto createAnswerForAboutUs(Long chatId) {
        return photoMessage.createPhotoMessage(chatId, Text.DESIGN.getText(),
                fileFabric.createVideoAboutUs(), new UrlData(Url.SEE.getText(), urlFabric.video));
    }

    public SendPhoto createAnswerForDesign(Long chatId) {
        return photoMessage.createPhotoMessage(chatId, fileFabric.createDocForDesign(),
                new UrlData(Url.OPEN_FULL_PRESENTATION.getText(), urlFabric.presentation));
    }
}
