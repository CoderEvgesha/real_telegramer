package real.telegramer.message.fabric.photo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import real.telegramer.message.dictionary.Text;
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
                fileFabric.createPhotoForMain());
    }

    public SendPhoto createAnswerForProjects(Long chatId) {
        return photoMessage.createPhotoMessage(chatId,
                fileFabric.createPictureProjects(),
                new UrlData(Url.DETAILS.getText(), urlFabric.details));
    }

    public SendPhoto createAnswerForServices(Long chatId) {
        return photoMessage.createPhotoMessage(chatId, Text.SERVICES.getText(),
                fileFabric.createPhotoForServices(), InterfaceFabric.getButtonsForServicesMenu());
    }

    public SendPhoto createAnswerForOther(Long chatId) {
        return photoMessage.createPhotoMessage(chatId, Text.OTHER.getText(),
                fileFabric.createPhotoForOther(), InterfaceFabric.getButtonsForServicesOrderMenu());
    }

    public SendPhoto createAnswerForDesign(Long chatId) {
        return photoMessage.createPhotoMessage(chatId, Text.DESIGN.getText(),
                fileFabric.createDocForDesign(),
                new UrlData(Url.OPEN_FULL_PRESENTATION.getText(), urlFabric.presentation));
    }

    public SendPhoto createAnswerForHard(Long chatId) {
        return photoMessage.createPhotoMessage(chatId, Text.HARD.getText(),
                fileFabric.createPhotoForHelp(),
                InterfaceFabric.getButtonsForHardMenu());
    }

    public SendPhoto createAnswerForBots(Long chatId) {
        return photoMessage.createPhotoMessage(chatId,
                Text.BOTS.getText(), fileFabric.createPhotoForBot(),
                new UrlData(Url.READ_THE_ARTICLE.getText(), urlFabric.bot));
    }

    public SendPhoto createAnswerForFeedback(Long chatId) {
        return photoMessage.createPhotoMessage(chatId,
                fileFabric.createPhotoForFeedback(),
                new UrlData(Url.OPEN_FEEDBACK_SITE.getText(), urlFabric.feedback));
    }

    public SendPhoto createAnswerForProjectsByKey(Long chatId) {
        return photoMessage.createPhotoMessage(chatId, Text.PROJECTS_BY_KEY.getText(),
                fileFabric.createPhotoForProjectsByKey(),
                new UrlData(Url.SEE.getText(), urlFabric.projectsByKey));
    }

    public SendPhoto createAnswerForEducation(Long chatId) {
        return photoMessage.createPhotoMessage(chatId, Text.EDUCATION.getText(),
                fileFabric.createPhotoForEducation(),
                new UrlData(Url.SEE.getText(), urlFabric.education));
    }

    public SendPhoto createAnswerForTeam(Long chatId) {
        return photoMessage.createPhotoMessage(chatId,
                fileFabric.createPhotoForFounder(),
                new UrlData(Url.DETAILS.getText(), urlFabric.team));
    }
}
