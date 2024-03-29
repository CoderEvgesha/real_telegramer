package real.telegramer.message.dictionary.buttons.menu;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum AboutUsMenu {
    TEAM("О команде \uD83D\uDC65"),
    PROJECTS("Проекты \uD83D\uDCDA"),
    FEEDBACK("Отзывы \uD83D\uDCDD");

    private final String text;

    AboutUsMenu(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static List<String> getTextValues() {
        return Arrays.stream(values()).map(AboutUsMenu::getText).collect(Collectors.toList());
    }

    public static Optional<AboutUsMenu> fromValue(String text) {
        return Arrays.stream(values()).filter(v -> v.getText().equals(text)).findFirst();
    }
}
