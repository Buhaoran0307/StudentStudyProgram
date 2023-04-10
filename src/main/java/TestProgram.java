import ConstantPacket.ConstantParameters;
import layout.Login;
import layout.MainPage;
import layout.PersonalPage;
import layout.WindowsFrame;
import util.Initialization;
import util.JsonFileReader;

public class TestProgram {
    public static void main(String[] args) {
        //new Initialization().DataInitialization();
        JsonFileReader.readJson();

        System.out.println(ConstantParameters.subjectInfoMap.get(1));
        System.out.println(ConstantParameters.studentMap.get(2020001));

        new Login();
        //new PersonalPage();
        //new WindowsFrame();
    }
}
