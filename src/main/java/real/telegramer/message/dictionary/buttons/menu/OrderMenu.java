package real.telegramer.message.dictionary.buttons.menu;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum OrderMenu {
    ORDER("Заказать");

    private final String text;

    OrderMenu(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static List<String> getTextValues() {
        return Arrays.stream(values()).map(OrderMenu::getText).collect(Collectors.toList());
    }

    public static Optional<OrderMenu> fromValue(String text) {
        return Arrays.stream(values()).filter(v -> v.getText().equals(text)).findFirst();
    }
}
