package util;

import ConstantPacket.ConstantParameters;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import entity.StudentAward;
import entity.Subject;
import com.itextpdf.text.pdf.PdfWriter;
import entity.Student;
import entity.SubjectInfo;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Paragraph;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Image;
import java.io.*;
import java.time.LocalDate;
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
    /**
     * this method is to raed subjectInfo.json and student.json
     */
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
    /**
     * this method is to raed studentAward.json
     */
    public static void readAwardHelper(){
        Gson gson = new Gson();
        String filePath = "src/main/java/DataSet/studentAward.json";

        try {
            JsonReader reader = new JsonReader(new FileReader(filePath));
            ConstantParameters.studentAwardMap = gson.fromJson(reader, new TypeToken<Map<Integer, StudentAward>>(){}.getType());

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * this method is to write student.json
     */
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

    /**
     * this method is used to write the student's grade into specific PDF document
     * @param studentNo the student's ID who want to write out his grade
     * @throws Exception IOException
     */
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

    /**
     * this method is used to write the student's GPA certification into specific PDF document
     * @param studentNo the student's ID who want to write out his GPA
     * @throws Exception IOException
     */
    public static void  writeGPAPDF(int studentNo) throws Exception {
        Student currentStudent = ConstantParameters.studentMap.get(studentNo);
        String filePath = "src/main/resources/GPAPDF/"+studentNo+"-GPA.pdf";
        File outputFile = new File(filePath);

        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(outputFile));
        document.open();

        Font boldFontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 25, Font.BOLD,BaseColor.RED);
        Chunk chunk = new Chunk("BUPT Academic affairs office", boldFontTitle);
        chunk.setUnderline(0.1f, -2f);
        Paragraph paragraphTitle = new Paragraph(chunk);
        paragraphTitle.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraphTitle);

        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        Paragraph paragraphInfo = new Paragraph("GPA Certification", boldFont);
        paragraphInfo.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraphInfo);
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        LocalDate today = LocalDate.now();
        float GPA = DataUtil.calculateGPA(currentStudent);
        int rank = DataUtil.calculateGPARank(GPA);
        Paragraph paragraph1 = new Paragraph();
        Chunk chunk1 = new Chunk("This is to certify that ");
        Chunk chunk2 = new Chunk(currentStudent.getName());
        chunk2.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
        chunk2.setUnderline(0.1f, -2f);
        Chunk chunk3 = new Chunk(" was admitted into the full-time undergraduate program in the International School, Beijing University of Posts and Telecommunications, in Sep. 2020. By the end of ");
        Chunk chunk4 = new Chunk(today.toString());
        Chunk chunk5 = new Chunk(", the student’s GPA is ");
        Chunk chunk6 = new Chunk(String.valueOf(GPA));
        chunk6.setUnderline(0.1f, -2f);
        chunk6.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
        Chunk chunk7 = new Chunk(" and ranks ");
        Chunk chunk8 = new Chunk(String.valueOf(rank));
        chunk8.setUnderline(0.1f, -2f);
        chunk8.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
        Chunk chunk9 = new Chunk(" among a total of ");
        Chunk chunk10 = new Chunk(String.valueOf(ConstantParameters.studentMap.size()));
        Chunk chunk11 = new Chunk(" students in the major.");

        paragraph1.add(chunk1);
        paragraph1.add(chunk2);
        paragraph1.add(chunk3);
        paragraph1.add(chunk4);
        paragraph1.add(chunk5);
        paragraph1.add(chunk6);
        paragraph1.add(chunk7);
        paragraph1.add(chunk8);
        paragraph1.add(chunk9);
        paragraph1.add(chunk10);
        paragraph1.add(chunk11);

        paragraph1.setAlignment(Element.ALIGN_LEFT);
        document.add(paragraph1);
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));

        Paragraph paragraphCal = new Paragraph();

        paragraphCal.add("NOTE: ");
        paragraphCal.add(new Chunk("GPA = ", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD)));
        paragraphCal.add(new Chunk("SUM (Grade × Credits)/SUM (Credits)\n", FontFactory.getFont(FontFactory.HELVETICA, 12)));

        document.add(paragraphCal);



        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));

        //set the information lines
        Paragraph paragraphEmail = new Paragraph();

        // Add each line of text as a separate Chunk to the Paragraph
        paragraphEmail.add(new Chunk("Academic Affairs Office of BUPT"));
        paragraphEmail.add(Chunk.NEWLINE); // add a new line
        paragraphEmail.add(new Chunk("10 Xitucheng Road, Haidian District"));
        paragraphEmail.add(Chunk.NEWLINE);
        paragraphEmail.add(new Chunk("Beijing, 100876. P. R. China"));
        paragraphEmail.add(Chunk.NEWLINE);
        paragraphEmail.add(new Chunk("Tel: 010-101010"));
        paragraphEmail.add(Chunk.NEWLINE);
        paragraphEmail.add(new Chunk("Email: test@bupt.edu.cn"));

        // Set the alignment of the Paragraph to right-aligned
        paragraphEmail.setAlignment(Element.ALIGN_RIGHT);

        // Set the position of the Paragraph to the bottom of the page
        paragraphEmail.setSpacingBefore(30); // adjust this value to position the paragraph as needed

        // Add the Paragraph to the document
        document.add(paragraphEmail);

        document.close();

    }
    /**
     * this method is get the map tough read subjectHelper.json
     * @return map
     */
    public static Map<String,String> readSubjectHelper(){
        Map<String,String> map = null;
        Gson gson = new Gson();
        String filePath = "src/main/java/DataSet/subjectHelper.json";

        try {
            JsonReader reader = new JsonReader(new FileReader(filePath));
            map = gson.fromJson(reader, new TypeToken<Map<String, String>>(){}.getType());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

}
