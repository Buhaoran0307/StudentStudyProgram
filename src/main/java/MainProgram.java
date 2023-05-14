import layout.LoginFrame;
import util.IOUtil;

public class MainProgram {
    public static void main(String[] args) {
        IOUtil.readJson();
        new LoginFrame();
    }
}
