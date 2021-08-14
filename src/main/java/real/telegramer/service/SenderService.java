package real.telegramer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import real.telegramer.db.repository.UserRepository;
import real.telegramer.message.dictionary.Commands;
import real.telegramer.message.fabric.text.TextFabric;

import java.util.ArrayList;
import java.util.List;

@Component
public class SenderService {
    @Value("${sender.link}")
    private String senderUsername;
    private final UserRepository userRepository;
    private final TextFabric textFabric;

    public SenderService(@Autowired UserRepository userRepository,
                         @Autowired TextFabric textFabric) {
        this.userRepository = userRepository;
        this.textFabric = textFabric;
    }

    public List process(Message msg) {
        List messages = new ArrayList();
        final var username = msg.getFrom().getUserName();
        final var text = msg.getText();
        if (!username.isBlank()) {
            if (username.equals(formatAdmUsername())) {
                if (text != null && !text.equals(Commands.START.getText())) {
                    userRepository.findAll().forEach(user -> {
                        messages.add(textFabric.createMessageForMailing(user.getChatId(), text));
                    });
                    return messages;
                }
            }
        }
        return messages;
    }

    private String formatAdmUsername() {
        return senderUsername.substring("https://t.me/".length());
    }
}
