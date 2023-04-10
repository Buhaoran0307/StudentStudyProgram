package layout;

import ConstantPacket.ConstantParameters;
import com.google.gson.Gson;
import entity.Student;
import util.JsonFileReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

public class Register extends JFrame{
    private JTextField name;
    private JTextField nickname;
    private JTextField phone;
    private JTextField password;

    private JButton signUp;
    Container container = this.getContentPane();
    public Register(){
        //采用所有元素手动布局的方式
        this.setBounds(520,30,450,600);
        this.setTitle("Login");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.WHITE);
        container.setLayout(null);

//   //设置输入框布局
        JPanel textPanel = new JPanel();
        textPanel.setSize(450,400);
        textPanel.setLocation(10,0);
        textPanel.setLayout(null);
        container.add(textPanel);
        //name文字布局
        JLabel usernameLabel = new JLabel("Name:");
        usernameLabel.setFont(new Font("Comic Sans Ms",Font.BOLD,20));
        usernameLabel.setSize(200,20);
        usernameLabel.setLocation(50,50);
        textPanel.add(usernameLabel);

        //name输入框布局
        name = new JTextField();
        name.setFont(new Font("Comic Sans Ms",Font.BOLD,20));
        name.setSize(300,40);
        name.setLocation(50,85);
        textPanel.add(name);

        //nickname文字布局
        JLabel nicknameLabel = new JLabel("Nikcname:");
        nicknameLabel.setFont(new Font("Comic Sans Ms",Font.BOLD,20));
        nicknameLabel.setSize(200,20);
        nicknameLabel.setLocation(50,140);
        textPanel.add(nicknameLabel);

        //nickname输入框布局
        nickname = new JTextField();
        nickname.setFont(new Font("Comic Sans Ms",Font.BOLD,20));
        nickname.setSize(300,40);
        nickname.setLocation(50,175);
        textPanel.add(nickname);

        //phone文字布局
        JLabel phoneLabel = new JLabel("Phone No.:");
        phoneLabel.setFont(new Font("Comic Sans Ms",Font.BOLD,20));
        phoneLabel.setSize(200,20);
        phoneLabel.setLocation(50,230);
        textPanel.add(phoneLabel);

        //phone输入框布局
        phone = new JTextField();
        phone.setFont(new Font("Comic Sans Ms",Font.BOLD,20));
        phone.setSize(300,40);
        phone.setLocation(50,265);
        textPanel.add(phone);

        //password文字布局
        JLabel passwordLable = new JLabel("Password:");
        passwordLable.setFont(new Font("Comic Sans Ms",Font.BOLD,20));
        passwordLable.setSize(200,20);
        passwordLable.setLocation(50,320);
        textPanel.add(passwordLable);

        //phone输入框布局
        password = new JPasswordField();
        password.setFont(new Font("Comic Sans Ms",Font.BOLD,20));
        password.setSize(300,40);
        password.setLocation(50,355);
        textPanel.add(password);
  //    //设置完成
  //    //设置Button和布局
        JPanel buttonPanel = new JPanel();
        buttonPanel.setSize(450,200);
        buttonPanel.setLocation(10,400);
        buttonPanel.setLayout(null);
        container.add(buttonPanel);

        //添加button
        signUp = new JButton("Sign Up");
        signUp.setSize(150,40);
        signUp.setLocation(115,30);
        signUp.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        signUp.setBackground(Color.WHITE);

        //添加响应
        signUp.addActionListener(new signUpListener());
        buttonPanel.add(signUp);
        //    //设置完成
        this.setVisible(true);
    }

//    //button响应
    class signUpListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            String name_from_text = name.getText();
            String nickName_from_text = nickname.getText();
            int phoneNumber_from_text = Integer.parseInt(phone.getText());
            String password_from_text = password.getText();
            //轮询给出学号
            new JsonFileReader().readJson();
            int temp_username=2020001;
            while(ConstantParameters.studentMap.get(temp_username)!=null){
                temp_username++;
            }
            Student student = new Student(name_from_text,temp_username,phoneNumber_from_text,nickName_from_text,password_from_text);
            ConstantParameters.studentMap.put(temp_username,student);
            System.out.println(ConstantParameters.studentMap.size());

            //写回json
            Gson gson = new Gson();
            String studentJson = gson.toJson(ConstantParameters.studentMap);
            try {
                FileWriter fileWriter = new FileWriter("src/main/java/DataSet/student.json");
                fileWriter.write(studentJson);
                fileWriter.flush();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            JOptionPane.showMessageDialog(null, "Successfully Registered!\nYour id is "+temp_username+".");
            dispose();
        }
}
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Register();
            }
        });
    }
}
