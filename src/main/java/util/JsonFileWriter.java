package util;

import ConstantPacket.ConstantParameters;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonFileWriter {
    public static void writeStudentJson() {
        Gson gson = new Gson();
        String filePath = "src/main/java/DataSet/student.json";

        try {
//            JsonReader reader = new JsonReader(new FileReader(filePath));
//            ConstantParameters.subjectInfoMap = gson.fromJson(reader, new TypeToken<Map<Integer, SubjectInfo>>(){}.getType());
//            reader = new JsonReader(new FileReader(filePath1));
//            ConstantParameters.studentMap = gson.fromJson(reader, new TypeToken<Map<Integer, Student>>(){}.getType());
//            reader.close();

            FileWriter writer = new FileWriter(new File(filePath));
            writer.write(gson.toJson(ConstantParameters.studentMap));
            writer.close();
            System.out.println("finish");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}