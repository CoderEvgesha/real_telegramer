package real.telegramer.message.dictionary.buttons.menu;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum OrderCommunicationMenu {
    WRITE_ME("Общаться письменно \uD83D\uDCDD"),
    CALL_ME("Запросить звонок \uD83D\uDCF2");;

    private final String text;

    OrderCommunicationMenu(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static List<String> getTextValues() {
        return Arrays.stream(values()).map(OrderCommunicationMenu::getText).collect(Collectors.toList());
    }

    public static Optional<OrderCommunicationMenu> fromValue(String text) {
        return Arrays.stream(values()).filter(v -> v.getText().equals(text)).findFirst();
    }
}
