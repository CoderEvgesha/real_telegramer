package real.telegramer.message.dictionary.buttons.menu;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum MainMenu {
    SEND_LETTER("Связаться с Настоящим Телеграмщиком"),
    SERVICES("Услуги"),
    ABOUT_US("О нас");

    private final String text;

    MainMenu(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static List<String> getTextValues() {
        return Arrays.stream(values()).map(MainMenu::getText).collect(Collectors.toList());
    }

    public static Optional<MainMenu> fromValue(String text) {
        return Arrays.stream(values()).filter(v -> v.getText().equals(text)).findFirst();
    }
}
