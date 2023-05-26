import entity.Student;
import entity.Subject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class SubjectTest {
    Subject subject_test1 = new Subject(123,"2020/09/30",90,1);
    Subject subject_test2 = new Subject(140,"2021/03/01",78,34);
    @Test
    void getSubjectNo() {
        assertEquals(123,subject_test1.getSubjectNo());
        assertEquals(140,subject_test2.getSubjectNo());
    }

    @Test
    void setSubjectNo() {
        subject_test1.setSubjectNo(150);
        assertEquals(150,subject_test1.getSubjectNo());
        subject_test2.setSubjectNo(110);
        assertEquals(110,subject_test2.getSubjectNo());
    }

    @Test
    void getStartTime() {
        assertEquals("2020/09/30",subject_test1.getStartTime());
        assertEquals("2021/03/01",subject_test2.getStartTime());
    }

    @Test
    void setStartTime() {
        subject_test1.setStartTime("2023/05/01");
        assertEquals("2023/05/01",subject_test1.getStartTime());
        subject_test2.setStartTime("2024/09/01");
        assertEquals("2024/09/01",subject_test2.getStartTime());
    }

    @Test
    void getGrade() {
        assertEquals(90,subject_test1.getGrade());
        assertEquals(78,subject_test2.getGrade());
    }

    @Test
    void setGrade() {
        assertEquals(90,subject_test1.getGrade());
        subject_test1.setGrade(80);
        assertEquals(80,subject_test1.getGrade());
        assertEquals(78,subject_test2.getGrade());
        subject_test2.setGrade(80);
        assertEquals(80,subject_test2.getGrade());
    }

    @Test
    void getRank() {
        assertEquals(1,subject_test1.getRank());
        assertEquals(34,subject_test2.getRank());
    }

    @Test
    void setRank() {
        assertEquals(1,subject_test1.getRank());
        subject_test1.setRank(2);
        assertEquals(2,subject_test1.getRank());
        assertEquals(34,subject_test2.getRank());
        subject_test2.setRank(2);
        assertEquals(2,subject_test2.getRank());
    }
}