package layout;

import entity.Student;
import util.DataUtil;
import util.IOUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import static ConstantPacket.ConstantParameters.studentMap;
import static util.IOUtil.readJson;
import static util.IOUtil.writeStudentJson;

/**
 * Create forget password function
 */
public class ForgetPasswordFrame extends JFrame {
    /**
     *this is used to get the container of the jFrame
     */
    Container container = this.getContentPane();
    /**
     *this is used to get the input of the username
     */
    private final JTextField userNameField;
    /**
     * this field is used to get the input of the user password
     */
    private final JPasswordField newPasswordField;
    /**
     * this field to used to get the input of the phone number
     */
    private final JTextField phoneNumberFiled;
    /**
     * the confirm button that get the input data for further process
     */
    private final JButton confirmButton;
    /**
     * this button is used to quit the forget password frame and back to the main page
     */
    private final JButton backButton;

    public ForgetPasswordFrame() {
        //set forget password page's frame
        this.setBounds(520, 30, 450, 600);
        this.setTitle("Forget Password");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.WHITE);
        container.setLayout(null);

        Image buptImage = IOUtil.gainImage("src/main/resources/Icon/BUPT.png");
        JLabel buptImgLable = new JLabel(new ImageIcon(buptImage));
        buptImgLable.setLocation(10,10);
        buptImgLable.setSize(51,48);
        container.add(buptImgLable);

        Image QMULImage = IOUtil.gainImage("src/main/resources/Icon/QMUL.png");
        JLabel QMULImgLable = new JLabel(new ImageIcon(QMULImage));
        QMULImgLable.setLocation(240,10);
        QMULImgLable.setSize(190,48);
        container.add(QMULImgLable);

        JLabel welcomeLabel = new JLabel("<html>Using your phone number to reset your password</html>");
        welcomeLabel.setSize(500,100);
        welcomeLabel.setLocation(12,50);
        welcomeLabel.setFont(new Font("Comic Sans Ms", Font.BOLD, 25));
        container.add(welcomeLabel);

        //set input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setSize(450, 360);
        inputPanel.setLocation(10, 100);
        inputPanel.setLayout(null);
        container.add(inputPanel);

        //set userName text label
        Image userIDImg = IOUtil.gainImage("src/main/resources/Icon/userID.jpg");
        ImageIcon userIDIcon = new ImageIcon(userIDImg);
        JLabel userNameLabel = new JLabel("Username:",userIDIcon,JLabel.LEFT);
        userNameLabel.setSize(200, 20);
        userNameLabel.setFont(new Font("Comic sans Ms", Font.BOLD, 20));
        userNameLabel.setLocation(50, 50);
        inputPanel.add(userNameLabel);

        //set UserName text Filed
        userNameField = new JTextField();
        userNameField.setFont(new Font("Comic sans Ms", Font.BOLD, 20));
        userNameField.setLocation(50, 85);
        userNameField.setSize(300, 40);
        inputPanel.add(userNameField);

        //set password text label
        Image passwordImg = IOUtil.gainImage("src/main/resources/Icon/password.jpg");
        ImageIcon passwordIcon = new ImageIcon(passwordImg);
        JLabel passwordLabel = new JLabel("Set new password:",passwordIcon,JLabel.LEFT);
        passwordLabel.setFont(new Font("Comic Sans Ms", Font.BOLD, 20));
        passwordLabel.setSize(300, 20);
        passwordLabel.setLocation(50, 140);
        inputPanel.add(passwordLabel);

        //set password text filed
        newPasswordField = new JPasswordField();
        newPasswordField.setFont(new Font("Comic Sans Ms", Font.BOLD, 20));
        newPasswordField.setSize(300, 40);
        newPasswordField.setLocation(50, 175);
        inputPanel.add(newPasswordField);

        //set phone number text label
        Image phoneImg = IOUtil.gainImage("src/main/resources/Icon/phone.jpg");
        ImageIcon phoneIcon = new ImageIcon(phoneImg);
        JLabel phoneLabel = new JLabel("Phone number:",phoneIcon,JLabel.LEFT);
        phoneLabel.setFont(new Font("Comic Sans Ms", Font.BOLD, 20));
        phoneLabel.setSize(200, 20);
        phoneLabel.setLocation(50, 230);
        inputPanel.add(phoneLabel);

        //set phone number text filed
        phoneNumberFiled = new JTextField();
        phoneNumberFiled.setFont(new Font("Comic Sans Ms", Font.BOLD, 20));
        phoneNumberFiled.setSize(300, 40);
        phoneNumberFiled.setLocation(50, 265);
        inputPanel.add(phoneNumberFiled);

        JLabel infoLabel = new JLabel("<html>Your password should contains at least one <br>UPPERCASE letter,one LOWER case " +
                "letter and a DIGITAl</html>");
        infoLabel.setSize(350,45);
        infoLabel.setLocation(35,315);
        infoLabel.setFont(new Font("Comic Sans Ms", Font.BOLD, 12));
        infoLabel.setBackground(Color.YELLOW);
        infoLabel.setOpaque(true);
        inputPanel.add(infoLabel);

        //set button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setSize(450, 200);
        buttonPanel.setLocation(10, 435);
        buttonPanel.setLayout(null);
        container.add(buttonPanel);

        //set confirm button
        confirmButton = new JButton("confirm");
        confirmButton.setFont(new Font("Comic Sans Ms", Font.BOLD, 20));
        confirmButton.setSize(130, 40);
        confirmButton.setLocation(130, 30);
        confirmButton.setBackground(Color.white);
        buttonPanel.add(confirmButton);
        confirmButton.addActionListener(new confirmButtonListener());

        //set back button
        backButton = new JButton("Back");
        backButton.setFont(new Font("Comic Sans Ms", Font.BOLD, 13));
        backButton.setSize(70, 30);
        backButton.setLocation(20, 90);
        backButton.setBackground(Color.white);
        buttonPanel.add(backButton);
        backButton.addActionListener(new BackListener());



        setVisible(true);
    }

    class confirmButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(newPasswordField.getText().equals("")||userNameField.getText().equals("")||phoneNumberFiled.getText().equals("")){
                JOptionPane.showMessageDialog(ForgetPasswordFrame.this, "Warning! None of the blank could be space.");
                return;
            }
            String inputUserName = userNameField.getText();
            int studentID = Integer.parseInt(inputUserName);
            if (checkID(studentID)) {
                boolean isValidPhone = DataUtil.checkPhoneNumber(phoneNumberFiled.getText());
                if (!isValidPhone) {
                    JOptionPane.showMessageDialog(ForgetPasswordFrame.this, "Please enter the phone number in the correct format ");
                    return;
                }
                int phoneNumber = Integer.parseInt(phoneNumberFiled.getText());
                if (checkPhoneNumber(studentID, phoneNumber)) {
                    String password = new String(newPasswordField.getPassword());
                    boolean isValidPassword = DataUtil.checkPassword(password);
                    if (!isValidPassword) {
                        JOptionPane.showMessageDialog(ForgetPasswordFrame.this, "password should contains at least one uppercase letter, one lowercase letter, and one digit, and no other symbols");
                        return;
                    }
                    changePassword(studentID, password);
                    JOptionPane messageDialog = new JOptionPane();
                    JOptionPane.showMessageDialog(ForgetPasswordFrame.this, "Password has been modified");
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            messageDialog.setVisible(false);
                            setVisible(false);
                            new LoginFrame();
                        }
                    }, 1);
                } else {
                    JOptionPane.showMessageDialog(ForgetPasswordFrame.this, "wrong phone number");
                }

            } else {
                JOptionPane.showMessageDialog(ForgetPasswordFrame.this, "wrong user name", "wrong userName", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        /**
         * this method is used to check whether the input studentID is in the data set.
         *
         * @param studentID the input userID
         * @return ture:valid ID; false: invalid ID
         */
        private boolean checkID(int studentID) {
            readJson();
            return studentMap.get(studentID) != null;
        }

        /**
         * this method is used to check whether the input phone number is correct
         *
         * @param studentID   user ID
         * @param phoneNumber input phone number
         * @return whether phone number is matched.
         */
        private boolean checkPhoneNumber(int studentID, int phoneNumber) {
            return studentMap.get(studentID).getPhoneNumber() == phoneNumber;
        }

        /**
         * this method is used to change the password
         *
         * @param studentID user ID
         * @param password  new password
         */
        private void changePassword(int studentID, String password) {
            Student current = studentMap.get(studentID);
            current.setPassword(password);
            writeStudentJson();
        }
    }
    class BackListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            new LoginFrame();
            dispose();
        }
    }

}