import ConstantPacket.ConstantParameters;
import layout.MainFrame;
import org.junit.jupiter.api.Test;
import util.IOUtil;

public class TestMainJFrame {
    @Test
    public void testLayout(){
        IOUtil.readJson();
        MainFrame.localUser = ConstantParameters.studentMap.get(2020002);
        new MainFrame();
    }
}
