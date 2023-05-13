import layout.LoginFrame;
import util.IOUtil;

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
        //MainFrame.localUser = ConstantParameters.studentMap.get(2020002);
        //new MainFrame();
        try {
            IOUtil.writeGPAPDF(2020001);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
