package real.telegramer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class AdminService {

    @Value("${team.link}")
    private String adminUsername;
    private Long chatId;

    public AdminService() {
        this.chatId = 0L;
    }

    public Long getChatId() {
        return chatId;
    }

    public boolean process(Message message) {
        final var messageChatId = message.getChatId();
        final var username = message.getFrom().getUserName();
        if (!username.isBlank()) {
            if (username.equals(formatAdmUsername()) && !chatId.equals(messageChatId)) {
                chatId = message.getChatId();
                return false;
            }
        }
        return true;
    }

    private void readSaveId() {
        // ToDo
    }

    private String formatAdmUsername() {
        String format = adminUsername.substring("https://t.me/".length());
        return format;
    }
}
