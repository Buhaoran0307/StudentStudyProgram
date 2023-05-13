import ConstantPacket.ConstantParameters;
import layout.LoginFrame;
import layout.MainFrame;
import org.junit.jupiter.api.Test;
import util.IOUtil;

import java.util.Map;

public class TestProgram {
    public static void main(String[] args) {
        //new Initialization().DataInitialization();
        IOUtil.readJson();

        //System.out.println(ConstantParameters.subjectInfoMap.get(1));
        //System.out.println(ConstantParameters.studentMap.get(2020001));

        //new LoginFrame();
        //new ForgetPasswordFrame();
        //new PersonalFrame();
        //new MainFrame();
        //JsonFileReader.readJson();
        MainFrame.localUser = ConstantParameters.studentMap.get(2020002);
        new MainFrame();
    }

    @Test
    public void testMap(){
        Map<String ,String> map = IOUtil.readSubjectHelper();
        System.out.println(map);
    }
}
