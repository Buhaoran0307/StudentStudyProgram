package layout;

import ConstantPacket.ConstantParameters;
import entity.Student;
import util.JsonFileReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JButton forgottenButton;

    Container container = this.getContentPane();

    public Login() {
        //采用所有元素手动布局的方式
        this.setBounds(520, 30, 450, 600);
        this.setTitle("Login");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.WHITE);
        container.setLayout(null);

        //   //设置输入框布局
        JPanel textPanel = new JPanel();
        textPanel.setSize(450, 250);
        textPanel.setLocation(10, 60);
        textPanel.setLayout(null);
        container.add(textPanel);
        //username文字布局
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Comic Sans Ms", Font.BOLD, 20));
        usernameLabel.setSize(200, 20);
        usernameLabel.setLocation(50, 50);
        textPanel.add(usernameLabel);

        //username输入框布局
        usernameField = new JTextField();
        usernameField.setFont(new Font("Comic Sans Ms", Font.BOLD, 20));
        usernameField.setSize(300, 40);
        usernameField.setLocation(50, 85);
        textPanel.add(usernameField);

        //password文字布局
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Comic Sans Ms", Font.BOLD, 20));
        passwordLabel.setSize(200, 20);
        passwordLabel.setLocation(50, 140);
        textPanel.add(passwordLabel);

        //password输入框布局
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Comic Sans Ms", Font.BOLD, 20));
        passwordField.setSize(300, 40);
        passwordField.setLocation(50, 175);
        textPanel.add(passwordField);

        //    //设置完成

        //    //设置button布局
        JPanel buttonPanel = new JPanel();
        buttonPanel.setSize(450, 200);
        buttonPanel.setLocation(10, 300);
        buttonPanel.setLayout(null);
        container.add(buttonPanel);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        forgottenButton = new JButton("Forget your password?");
        //添加button布局
        loginButton.setSize(100, 40);
        loginButton.setLocation(60, 10);
        loginButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        loginButton.setBackground(Color.WHITE);

        registerButton.setSize(130, 40);
        registerButton.setLocation(200, 10);
        registerButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        registerButton.setBackground(Color.WHITE);

        forgottenButton.setSize(270, 40);
        forgottenButton.setLocation(60, 80);
        forgottenButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        forgottenButton.setBackground(Color.WHITE);

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(forgottenButton);

        loginButton.addActionListener(new LoginListener());
        registerButton.addActionListener(new RegisterListener());

        setVisible(true);
        //    //设置完成

    }

//    //内部类实现button响应
    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (authenticate(username, password)) {
                JOptionPane.showMessageDialog(Login.this, "Login successful!");
                int StudentID = Integer.parseInt(username);
                WindowsFrame.localUser = ConstantParameters.studentMap.get(StudentID);
                setVisible(false);
                new WindowsFrame();
            } else {
                JOptionPane.showMessageDialog(Login.this, "Invalid username or password.");
            }
        }

        //登陆验证
        private boolean authenticate(String username, String password) {
            new JsonFileReader().readJson();
            int username_int = Integer.parseInt(username);
            //是否查询非空，是否登陆成功
            if(ConstantParameters.studentMap.get(username_int) != null){
                String password_from_json = ConstantParameters.studentMap.get(username_int).getPassword();
                return password.equals(password_from_json);
            }
            else return false;
        }

    }
    class RegisterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new Register();
        }
    }
//    //实现结束
    public static void main(String[] args) {
        new Login();
    }

}