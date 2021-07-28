package real.telegramer.message.fabric.buttons;

import real.telegramer.message.dictionary.buttons.menu.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InterfaceFabric {
    public static List<List<String>> getButtonsForMainMenu() {
        List<List<String>> outer = new ArrayList<>();
        List<String> row1 = new ArrayList<>();
        row1.add(MainMenu.SEND_LETTER.getText());
        List<String> row2 = new ArrayList<>();
        row2.add(MainMenu.ABOUT_US.getText());
        row2.add(MainMenu.SERVICES.getText());
        outer.add(row1);
        outer.add(row2);
        return outer;
    }

    public static List<List<String>> getButtonsForAboutUsMenu() {
        List<List<String>> outer = new ArrayList<>();
        List<String> row1 = new ArrayList<>();
        row1.add(AboutUsMenu.FEEDBACK.getText());
        List<String> row2 = new ArrayList<>();
        row2.add(AboutUsMenu.PROJECTS.getText());
        row2.add(AboutUsMenu.TEAM.getText());
        outer.add(row1);
        outer.add(row2);
        outer.add(getButtonForBack());
        return outer;
    }

    public static List<List<String>> getButtonsForServicesMenu() {
        List<List<String>> outer = new ArrayList<>();
        List<String> row1 = new ArrayList<>();
        row1.add(ServicesMenu.EDUCATION.getText());
        row1.add(ServicesMenu.PROJECT_BY_KEY.getText());
        List<String> row2 = new ArrayList<>();
        row2.add(ServicesMenu.DESIGN.getText());
        row2.add(ServicesMenu.OTHER.getText());
        List<String> row3 = new ArrayList<>();
        row3.add(ServicesMenu.BOTS.getText());
        outer.add(row1);
        outer.add(row2);
        outer.add(row3);
        outer.add(getButtonForBack());
        return outer;
    }

    public static List<List<String>> getButtonsForServicesOrderMenu() {
        List<List<String>> outer = new ArrayList<>();
        List<String> row1 = new ArrayList<>();
        row1.add(OrderMenu.ORDER.getText());
        outer.add(row1);
        outer.add(getButtonForBack());
        return outer;
    }

    public static List<List<String>> getButtonsForOrderCommunicationMenu() {
        List<List<String>> outer = new ArrayList<>();
        List<String> row1 = new ArrayList<>();
        row1.add(OrderCommunicationMenu.WRITE_ME.getText());
        row1.add(OrderCommunicationMenu.CALL_ME.getText());
        outer.add(row1);
        outer.add(getButtonForBack());
        return outer;
    }

    public static List<List<String>> getButtonsForWriteMenu() {
        List<List<String>> outer = new ArrayList<>();
        List<String> row1 = new ArrayList<>();
        row1.add(WriteMenu.HARD.getText());
        row1.add(WriteMenu.OK.getText());
        outer.add(row1);
        outer.add(getButtonForBack());
        return outer;
    }

    public static List<List<String>> getButtonsForHardMenu() {
        List<List<String>> outer = new ArrayList<>();
        List<String> row1 = new ArrayList<>();
        row1.add(HardMenu.RECALL_ME.getText());
        outer.add(row1);
        outer.add(getButtonForBack());
        return outer;
    }

    public static List<List<String>> getButtonsForBackMenu() {
        List<List<String>> outer = new ArrayList<>();
        outer.add(getButtonForBack());
        return outer;
    }

    private static List<String> getButtonForBack() {
        return Collections.singletonList(BackMenu.BACK.getText());
    }
}
