package real.telegramer.message.model;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import real.telegramer.message.model.data.CallbackData;
import real.telegramer.message.model.data.UrlData;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMessage {

    protected ReplyKeyboardMarkup createReplayMarkup(List<List<String>> buttons) {
        List<KeyboardRow> rowList = new ArrayList<>();
        buttons.forEach(r -> {
            KeyboardRow row = new KeyboardRow();
            r.forEach(row::add);
            rowList.add(row);
        });
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setSelective(true);
        keyboard.setKeyboard(rowList);
        keyboard.setResizeKeyboard(true);
        return keyboard;
    }

    protected InlineKeyboardMarkup createUrlInlineMarkup(List<UrlData> buttons) {
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        buttons.forEach(b -> {
            List<InlineKeyboardButton> row = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(b.getText());
            button.setUrl(b.getLink());
            row.add(button);
            rows.add(row);
        });
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.setKeyboard(rows);
        return keyboard;
    }

    protected InlineKeyboardMarkup createCallbackInlineMarkup(List<CallbackData> buttons) {
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        buttons.forEach(b -> {
            List<InlineKeyboardButton> row = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(b.getText());
            button.setCallbackData(b.getCallback());
            row.add(button);
            rows.add(row);
        });
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.setKeyboard(rows);
        return keyboard;
    }
}
