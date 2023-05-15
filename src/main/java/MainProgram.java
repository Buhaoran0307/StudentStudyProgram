import layout.LoginFrame;
import util.IOUtil;
import util.Initialization;

public class MainProgram {
    public static void main(String[] args) {
        //(new Initialization()).DataInitialization();
        IOUtil.readJson();
        new LoginFrame();
    }
}
