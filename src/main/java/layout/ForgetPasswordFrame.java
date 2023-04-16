package layout;

import entity.Student;
import util.DataUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import static ConstantPacket.ConstantParameters.studentMap;
import static util.IOUtil.readJson;
import static util.IOUtil.writeStudentJson;

public class ForgetPasswordFrame extends JFrame {
    Container container = this.getContentPane();
    private JTextField userNameField;
    private JPasswordField newPasswordField;
    private JTextField phoneNumberFiled;
    private JButton confirmButton;
    private JButton backButton;

    public ForgetPasswordFrame() {
        //set forget password page's frame
        this.setBounds(520, 30, 450, 600);
        this.setTitle("Forget Password");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.WHITE);
        container.setLayout(null);

        //set input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setSize(450, 320);
        inputPanel.setLocation(10, 30);
        inputPanel.setLayout(null);
        container.add(inputPanel);

        //set userName text label
        JLabel userNameLabel = new JLabel("Username:");
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
        JLabel passwordLabel = new JLabel("Set new password:");
        passwordLabel.setFont(new Font("Comic Sans Ms", Font.BOLD, 20));
        passwordLabel.setSize(200, 20);
        passwordLabel.setLocation(50, 140);
        inputPanel.add(passwordLabel);

        //set password text filed
        newPasswordField = new JPasswordField();
        newPasswordField.setFont(new Font("Comic Sans Ms", Font.BOLD, 20));
        newPasswordField.setSize(300, 40);
        newPasswordField.setLocation(50, 175);
        inputPanel.add(newPasswordField);

        //set phone number text label
        JLabel phoneLabel = new JLabel("Phone number:");
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

        //set button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setSize(450, 200);
        buttonPanel.setLocation(10, 335);
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
        backButton.setFont(new Font("Comic Sans Ms", Font.BOLD, 15));
        backButton.setSize(80, 30);
        backButton.setLocation(40, 150);
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
                JOptionPane.showMessageDialog(ForgetPasswordFrame.this, "wrong user name", "wrong userName", JOptionPane.NO_OPTION);
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