package real.telegramer.message.model.data;

public class CallbackData {

    private final String text;
    private final String callback;

    public CallbackData(String text, String callback) {
        this.text = text;
        this.callback = callback;
    }

    public String getText() {
        return text;
    }

    public String getCallback() {
        return callback;
    }
}
