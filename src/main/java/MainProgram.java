import ConstantPacket.ConstantParameters;
import layout.AwardPage;
import layout.LoginFrame;
import layout.MainFrame;
import layout.MainPage;
import util.IOUtil;

public class MainProgram {
    public static void main(String[] args) {
        //(new Initialization()).DataInitialization();
        IOUtil.readJson();
        new LoginFrame();
    }
}
