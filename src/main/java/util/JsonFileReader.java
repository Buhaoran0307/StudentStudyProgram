package util;

import ConstantPacket.ConstantParameters;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import entity.Student;
import entity.SubjectInfo;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class JsonFileReader {
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

}
