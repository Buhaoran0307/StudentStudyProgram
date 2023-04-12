import ConstantPacket.ConstantParameters;
import entity.Student;
import layout.Login;
import layout.WindowsFrame;
import org.junit.jupiter.api.Test;
import util.JsonFileReader;

public class TestProgram {
    public static void main(String[] args) {
        //new Initialization().DataInitialization();
        JsonFileReader.readJson();

        //System.out.println(ConstantParameters.subjectInfoMap.get(1));
        //System.out.println(ConstantParameters.studentMap.get(2020001));

        new Login();
        //new PersonalPage();
        //new WindowsFrame();
        //JsonFileReader.readJson();
        //WindowsFrame.localUser = ConstantParameters.studentMap.get(2020002);
        //new WindowsFrame();
    }

    @Test
    public void testWindowFrame(){
        JsonFileReader.readJson();
        WindowsFrame.localUser = ConstantParameters.studentMap.get(2020002);
        new WindowsFrame();
    }

    @Test
    public void testLogin(){
        JsonFileReader.readJson();
        new Login();
    }
}
