package real.telegramer.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import real.telegramer.message.dictionary.Commands;
import real.telegramer.message.dictionary.buttons.menu.MainMenu;
import real.telegramer.message.dictionary.buttons.menu.OrderMenu;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
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

    public String getInformationAboutSection() {
        for (int i = previousCommands.size() - 1; i >= 0; i--) {
            if (previousCommands.get(i).equals(OrderMenu.ORDER.getText())) {
                return previousCommands.get(i - 1);
            }
        }
        var communicate = previousCommands.stream().filter(c ->
                c.equals(MainMenu.SEND_LETTER.getText())).findFirst();
        return communicate.orElse("");
    }
}
