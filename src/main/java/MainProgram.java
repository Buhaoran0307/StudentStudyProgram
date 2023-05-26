import layout.LoginFrame;
import util.IOUtil;
import util.Initialization;

/**
 * The main method
 */
public class MainProgram {
    public static void main(String[] args) {
        //(new Initialization()).DataInitialization();
        IOUtil.readJson();
        new LoginFrame();
    }
}
