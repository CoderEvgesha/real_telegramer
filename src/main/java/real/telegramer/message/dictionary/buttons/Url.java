package real.telegramer.message.dictionary.buttons;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Url {
    SEE("Посмотреть ➡️"),
    READ_THE_ARTICLE("Прочитать ➡️"),
    OPEN_FULL_VIDEO("Посмотреть полное видео на Youtube \uD83D\uDCF9"),
    OPEN_FULL_PRESENTATION("Открыть презентацию ➡️"),
    READ_THE_INSTRUCTION("Инструкция по составлению ТЗ \uD83D\uDD28"),
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
