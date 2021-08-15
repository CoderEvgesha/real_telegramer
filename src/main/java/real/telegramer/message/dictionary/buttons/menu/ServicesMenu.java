package real.telegramer.message.dictionary.buttons.menu;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum ServicesMenu {
    EDUCATION("Обучение \uD83D\uDD8A"),
    PROJECT_BY_KEY("Проект под ключ \uD83D\uDDDD"),
    DESIGN("Оформление \uD83C\uDFA8"),
    OTHER("Другие услуги \uD83D\uDCDC"),
    BOTS("Разработка Telegram-ботов \uD83D\uDD27");

    private final String text;

    ServicesMenu(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static List<String> getTextValues() {
        return Arrays.stream(values()).map(ServicesMenu::getText).collect(Collectors.toList());
    }

    public static Optional<ServicesMenu> fromValue(String text) {
        return Arrays.stream(values()).filter(v -> v.getText().equals(text)).findFirst();
    }
}
