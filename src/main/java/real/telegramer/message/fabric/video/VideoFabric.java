package real.telegramer.message.fabric.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import real.telegramer.message.dictionary.buttons.Url;
import real.telegramer.message.fabric.file.FileFabric;
import real.telegramer.message.fabric.url.UrlFabric;
import real.telegramer.message.model.data.UrlData;
import real.telegramer.message.model.video.VideoMessage;

@Component
public class VideoFabric {
    private final UrlFabric urlFabric;
    private final VideoMessage videoMessage;
    private final FileFabric fileFabric;

    public VideoFabric(@Autowired FileFabric fileFabric,
                       @Autowired UrlFabric urlFabric) {
        this.videoMessage = new VideoMessage();
        this.fileFabric = fileFabric;
        this.urlFabric = urlFabric;
    }

    public SendVideo createAnswerForAboutUs(Long chatId) {
        return videoMessage.createVideoMessage(chatId,
                fileFabric.createVideoAboutUs(), new UrlData(Url.OPEN_FULL_VIDEO.getText(), urlFabric.video));
    }

}
