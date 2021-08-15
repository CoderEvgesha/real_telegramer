package real.telegramer.message.dictionary.buttons;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Details {
    DETAILS("Подробнее ➡️");

    private final String text;

    Details(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static List<String> getTextValues() {
        return Arrays.stream(values()).map(Details::getText).collect(Collectors.toList());
    }

    public static Optional<Details> fromValue(String text) {
        return Arrays.stream(values()).filter(v -> v.getText().equals(text)).findFirst();
    }
}
