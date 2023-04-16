package util;

import ConstantPacket.ConstantParameters;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import entity.Student;
import entity.SubjectInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Map;

public class IOUtil {
    public static Image gainImage(String path) {
        Image image = null;
        FileInputStream fileInputStream;
        File file = new File(String.valueOf(path));
        if (file.exists() && file.isFile()){
            try {
                fileInputStream = new FileInputStream(file);
                image = ImageIO.read(fileInputStream);
            }catch (Exception e){
                System.out.println("[Error] Something wrong with the image in " + path);
            }
        }else {
            System.out.println("[Error] There is no image " + path);
        }
        return image;
    }

    public static void readJson() {
        Gson gson = new Gson();
        String filePath = "src/main/java/DataSet/subjectInfo.json";
        String filePath1 = "src/main/java/DataSet/student.json";

        try {
            JsonReader reader = new JsonReader(new FileReader(filePath));
            ConstantParameters.subjectInfoMap = gson.fromJson(reader, new TypeToken<Map<Integer, SubjectInfo>>(){}.getType());
            reader = new JsonReader(new FileReader(filePath1));
            ConstantParameters.studentMap = gson.fromJson(reader, new TypeToken<Map<Integer, Student>>(){}.getType());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeStudentJson() {
        Gson gson = new Gson();
        String filePath = "src/main/java/DataSet/student.json";
        try {
            FileWriter writer = new FileWriter(new File(filePath));
            writer.write(gson.toJson(ConstantParameters.studentMap));
            writer.close();
            System.out.println("finish");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Image selectPicture() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(imageFilter);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedImage bufferedImage = ImageIO.read(selectedFile);
                if (bufferedImage != null) {
                    return bufferedImage;
                } else {
                    JOptionPane.showMessageDialog(null, "Please select image !", "Error", JOptionPane.ERROR_MESSAGE);
                    return selectPicture();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Can't load that image, please try again !", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }
}
