package real.telegramer.message.fabric.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.InputFile;

@Component
public class FileFabric {
    @Value("${photo.main}")
    private String main;

    @Value("${video.about.us}")
    private String aboutUs;

    @Value("${photo.projects}")
    private String projects;

    @Value("${photo.services}")
    private String services;

    @Value("${photo.design}")
    private String design;

    @Value("${photo.other}")
    private String other;

    @Value("${photo.bot}")
    private String bot;

    @Value("${photo.help}")
    private String help;

    @Value("${photo.feedback}")
    private String feedback;

    @Value("${photo.by.key}")
    private String projectsByKey;

    @Value("${photo.education}")
    private String education;

    public InputFile createPhotoForEducation() {
        return readFromFile(education);
    }

    public InputFile createPhotoForProjectsByKey() {
        return readFromFile(projectsByKey);
    }

    public InputFile createPhotoForFeedback() {
        return readFromFile(feedback);
    }

    public InputFile createPhotoForBot() {
        return readFromFile(bot);
    }

    public InputFile createPhotoForHelp() {
        return readFromFile(help);
    }

    public InputFile createPhotoForMain() {
        return readFromFile(main);
    }

    public InputFile createDocForDesign() {
        return readFromFile(design);
    }

    public InputFile createPhotoForServices() {
        return readFromFile(services);
    }

    public InputFile createPhotoForOther() {
        return readFromFile(other);
    }

    public InputFile createVideoAboutUs() {
        return readFromFile(aboutUs);
    }

    public InputFile createPictureProjects() {
        return readFromFile(projects);
    }

    private InputFile readFromFile(String path) {
        var stream = getClass().getClassLoader().getResourceAsStream(path);
        return new InputFile(stream, path);
    }
}
