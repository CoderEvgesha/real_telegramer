package real.telegramer.message.dictionary;

import java.util.Arrays;
import java.util.Optional;

public enum Commands {
    START("/start"),
    ;

    private final String text;

    Commands(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static Optional<Commands> fromValue(String text) {
        return Arrays.stream(values()).filter(v -> v.getText().equals(text)).findFirst();
    }
}
