package layout;

import entity.Student;
import util.JsonFileReader;
import util.JsonFileWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import util.JsonFileWriter;

import static ConstantPacket.ConstantParameters.studentMap;
import static util.JsonFileReader.readJson;
import static util.JsonFileWriter.writeStudentJson;

public class ForgetPassword extends JFrame {
    private JTextField userNameField;
    private JPasswordField newPasswordField;
    private JTextField phoneNumberFiled;
    private JButton confirmButton;


    Container container = this.getContentPane();
    public ForgetPassword()
    {
        //set forget password page's frame
        this.setBounds(520, 30, 450, 600);
        this.setTitle("Forget Password");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.WHITE);
        container.setLayout(null);

        //set input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setSize(450,320);
        inputPanel.setLocation(10,30);
        inputPanel.setLayout(null);
        container.add(inputPanel);

        //set userName text label
        JLabel userNameLabel = new JLabel("Username:");
        userNameLabel.setSize(200,20);
        userNameLabel.setFont(new Font("Comic sans Ms",Font.BOLD,20));
        userNameLabel.setLocation(50,50);
        inputPanel.add(userNameLabel);

        //set UserName text Filed
        userNameField = new JTextField();
        userNameField.setFont(new Font("Comic sans Ms",Font.BOLD,20));
        userNameField.setLocation(50,85);
        userNameField.setSize(300,40);
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
        buttonPanel.setSize(450,200);
        buttonPanel.setLocation(10,335);
        buttonPanel.setLayout(null);
        container.add(buttonPanel);

        //set confirm button
        confirmButton = new JButton("confirm");
        confirmButton.setFont(new Font("Comic Sans Ms", Font.BOLD, 20));
        confirmButton.setSize(130,40);
        confirmButton.setLocation(130,30);
        confirmButton.setBackground(Color.white);
        buttonPanel.add(confirmButton);
        confirmButton.addActionListener(new confirmButtonListener());


        setVisible(true);
    }
    class confirmButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String inputUserName = userNameField.getText();
            int studentID = Integer.parseInt(inputUserName);
            if(checkID(studentID)){
                int phoneNumber = Integer.parseInt(phoneNumberFiled.getText());
                if (checkPhoneNumber(studentID, phoneNumber)) {
                    String password = new String(newPasswordField.getPassword());
                    changePassword(studentID, password);
                    JOptionPane messageDialog = new JOptionPane();
                    messageDialog.showMessageDialog(null, "Password has been modified");
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            messageDialog.setVisible(false);
                            setVisible(false);
                            new Login();
                        }
                    }, 1);
                }else{
                    JOptionPane.showMessageDialog(ForgetPassword.this,"wrong phone number");
                }

            }else{
                JOptionPane.showMessageDialog(ForgetPassword.this,"wrong user name","wrong userName",JOptionPane.NO_OPTION);
            }
        }
        private boolean checkID(int studentID)
        {
            readJson();
            if(studentMap.get(studentID) != null)
            {
                return true;
            }
            else return false;
        }
        private boolean checkPhoneNumber(int studentID, int phoneNumber){
            if(studentMap.get(studentID).getPhoneNumber() == phoneNumber)
            {
                return true;
            }
            else return false;
        }
        private void changePassword(int studentID,String password)
        {
            Student current = studentMap.get(studentID);
            current.setPassword(password);
            writeStudentJson();
        }


    }

    public static void main(String[] args) {
        new ForgetPassword();
    }

}
