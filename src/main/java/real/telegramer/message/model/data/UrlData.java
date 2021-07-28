package real.telegramer.message.model.data;

public class UrlData {

    private final String text;
    private final String link;

    public UrlData(String text, String link) {
        this.text = text;
        this.link = link;
    }

    public String getText() {
        return text;
    }

    public String getLink() {
        return link;
    }
}
