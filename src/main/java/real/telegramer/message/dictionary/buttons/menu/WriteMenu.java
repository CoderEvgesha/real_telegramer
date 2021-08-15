package real.telegramer.message.dictionary.buttons.menu;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum WriteMenu {
    HARD("Трудности с постановкой задачи \uD83E\uDDD0"),
    OK("Ок \uD83D\uDC4C");

    private final String text;

    WriteMenu(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static List<String> getTextValues() {
        return Arrays.stream(values()).map(WriteMenu::getText).collect(Collectors.toList());
    }

    public static Optional<WriteMenu> fromValue(String text) {
        return Arrays.stream(values()).filter(v -> v.getText().equals(text)).findFirst();
    }
}
