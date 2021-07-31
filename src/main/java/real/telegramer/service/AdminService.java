package real.telegramer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import real.telegramer.db.model.Admin;
import real.telegramer.db.repository.AdminRepository;

@Component
public class AdminService {

    @Value("${team.link}")
    private String adminUsername;
    private Long chatId;
    private final AdminRepository adminRepository;

    public AdminService(@Autowired AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
        readSaveId();
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
                saveId(chatId);
                return false;
            }
        }
        return true;
    }

    private void readSaveId() {
        Admin admin = adminRepository.findTopByOrderByIdDesc();
        if (admin == null) {
            chatId = 0L;
        } else {
            chatId = admin.getChatId();
        }
    }

    private void saveId(Long chatId) {
        adminRepository.save(new Admin(chatId));
    }

    private String formatAdmUsername() {
        return adminUsername.substring("https://t.me/".length());
    }
}
