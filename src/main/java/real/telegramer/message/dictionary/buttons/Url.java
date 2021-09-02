package real.telegramer.message.dictionary.buttons;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Url {
    SEE("Посмотреть ➡️"),
    DETAILS("Подробнее ➡️"),
    OPEN_FEEDBACK_SITE("Перейти на сайт с отзывами ➡️"),
    OPEN_FULL_VIDEO("Посмотреть видео о нас на Youtube \uD83D\uDCF9"),
    OPEN_FULL_PRESENTATION("Посмотреть работы ➡️"),
    WRITE("Отправить сообщение \uD83D\uDCEC");

    private final String text;

    Url(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static List<String> getTextValues() {
        return Arrays.stream(values()).map(Url::getText).collect(Collectors.toList());
    }

    public static Optional<Url> fromValue(String text) {
        return Arrays.stream(values()).filter(v -> v.getText().equals(text)).findFirst();
    }
}
