package real.telegramer.message.service;

import org.springframework.stereotype.Component;
import real.telegramer.message.dictionary.Commands;
import real.telegramer.message.dictionary.buttons.menu.BackMenu;

import java.util.ArrayList;
import java.util.List;

@Component
public class BackService {

    private final List<String> previousCommands;
    private boolean isAddCommand;

    public BackService() {
        this.previousCommands = new ArrayList<>();
        this.isAddCommand = true;
    }

    public void manageStart() {
        this.previousCommands.clear();
        this.isAddCommand = true;
    }

    public String manageBack() {
        isAddCommand = false;
        if (previousCommands.size() > 1) {
            return previousCommands.remove(previousCommands.size() - 2);
        } else {
            return Commands.START.getText();
        }
    }

    public void manageTransition(String currentMessage) {
        if (!isAddCommand) {
            isAddCommand = true;
        } else {
            previousCommands.add(currentMessage);
        }
    }
}
