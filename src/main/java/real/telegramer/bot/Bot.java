package real.telegramer.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import real.telegramer.db.model.User;
import real.telegramer.db.repository.UserRepository;
import real.telegramer.message.fabric.AnswerFabric;
import real.telegramer.message.model.video.VideoMessage;

import java.util.List;

@Component
public class Bot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String username;

    @Value("${bot.token}")
    private String token;

    private final AnswerFabric answerFabric;
    private final UserRepository userRepository;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    public Bot(@Autowired AnswerFabric answerFabric,
               @Autowired UserRepository userRepository) {
        this.answerFabric = answerFabric;
        this.userRepository = userRepository;
    }

    @Override
    public void onUpdateReceived(Update update) {
        var msg = update.getMessage();
        if (msg != null) {
            saveInformationAboutUser(msg);
            if (msg.getText() != null) {
                var answer = answerFabric.createAnswer(msg);
                if (answer != null) {
                    sendMessage(answer);
                }
            }
        }
    }

    private void saveInformationAboutUser(Message msg) {
        Long chatId = msg.getChatId();
        Long userId = msg.getFrom().getId();
        String username = msg.getFrom().getUserName();
        userRepository.save(new User(userId, chatId, username));
    }

    private void sendMessage(Object message) {
        try {
            if (message instanceof SendMessage) {
                execute((SendMessage) message);
            }
            if (message instanceof SendPhoto) {
                execute((SendPhoto) message);
            }
            if (message instanceof SendVideo) {
                execute((SendVideo) message);
            }
            if (message instanceof SendDocument) {
                execute((SendDocument) message);
            }
            if (message instanceof List) {
                ((List) message).forEach(this::sendMessage);
            }
        } catch (Exception e) {
            System.out.println("Не удалось отправить сообщение: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
