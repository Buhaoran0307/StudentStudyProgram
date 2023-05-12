package util;

import ConstantPacket.ConstantParameters;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import entity.Subject;
import com.itextpdf.text.pdf.PdfWriter;
import entity.Student;
import entity.SubjectInfo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Image;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;

import static com.itextpdf.text.FontFactory.TIMES_ROMAN;

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

    public static void writeGradePDF(int studentNo) throws Exception{
        Student currentStudent = ConstantParameters.studentMap.get(studentNo);
        String filePath = "src/main/resources/gradePDF/"+studentNo+"-grade.pdf";
        File outputFile = new File(filePath);

        // 如果文件不存在，则创建文件
        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(outputFile));
        document.open();
        ArrayList<Subject> subjects = currentStudent.getSelectedSubjects();
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        Font boldFontText = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD);
        Paragraph paragraphInfo = new Paragraph("Grade report", boldFont);
        paragraphInfo.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraphInfo);
        Paragraph paragraphStudent = new Paragraph("Name: "+currentStudent.getName()+
                "\n"+"Student ID: "+currentStudent.getStudentNo() +"\n");
        paragraphStudent.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraphStudent);
        document.add(new Paragraph("\n"));

        PdfPTable table = new PdfPTable(4);
// Set the table width percentage to 100% and table alignment to center
        table.setWidthPercentage(100);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);

// Add table header
        table.addCell(new PdfPCell(new Phrase("Subject Name", boldFontText)));
        table.addCell(new PdfPCell(new Phrase("Subject ID", boldFontText)));
        table.addCell(new PdfPCell(new Phrase("Grade", boldFontText)));
        table.addCell(new PdfPCell(new Phrase("Rank", boldFontText)));

// Add subject information to the table
        for (Subject s : subjects) {
            // Get subject information from subjectInfoMap using subjectNo
            SubjectInfo SI = ConstantParameters.subjectInfoMap.get(s.getSubjectNo());

            // Add a row to the table with the subject information
            table.addCell(SI.getSubjectName());
            table.addCell(String.valueOf(s.getSubjectNo()));
            table.addCell(String.valueOf(s.getGrade()));
            table.addCell(String.valueOf(s.getRank()));
        }

        // Add the table to the document
        document.add(table);

        document.close();
    }
}
