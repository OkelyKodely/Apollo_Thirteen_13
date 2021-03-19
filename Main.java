//By Daniel C
public class Main {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.setGUI();
        try {
            ui.runScript();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}