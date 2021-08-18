package real.telegramer.message.fabric.url;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlFabric {
    @Value("${feedback.site}")
    public String feedback;

    @Value("${education.site}")
    public String education;

    @Value("${projects.by.key}")
    public String projectsByKey;

    @Value("${team.telegraph}")
    public String team;

    @Value("${details.telegraph}")
    public String details;

    @Value("${manager.link}")
    public String write;

    @Value("${about.us.link}")
    public String video;

    @Value("${presentation.link}")
    public String presentation;

    @Value("${bots.link}")
    public String bot;
}
