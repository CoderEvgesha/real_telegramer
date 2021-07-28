package real.telegramer.message.dictionary.buttons.menu;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum HardMenu {
    RECALL_ME("Перезвоните мне");

    private final String text;

    HardMenu(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static List<String> getTextValues() {
        return Arrays.stream(values()).map(HardMenu::getText).collect(Collectors.toList());
    }

    public static Optional<HardMenu> fromValue(String text) {
        return Arrays.stream(values()).filter(v -> v.getText().equals(text)).findFirst();
    }
}
