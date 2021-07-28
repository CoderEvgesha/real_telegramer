package real.telegramer.message.dictionary.buttons.menu;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum ServicesMenu {
    EDUCATION("Обучение"),
    PROJECT_BY_KEY("Проект под ключ"),
    DESIGN("Оформление"),
    OTHER("Другое"),
    BOTS("Создание Телеграм-ботов");

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