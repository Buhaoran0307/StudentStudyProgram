import entity.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    Student student_test = new Student("test",123,456,"nick","password");
    @Test
    void getName() {
        assertEquals("test",student_test.getName());
    }

    @Test
    void setName() {
        assertEquals("test",student_test.getName());
        student_test.setName("hello");
        assertEquals("hello",student_test.getName());
    }

    @Test
    void getStudentNo() {
        assertEquals(123,student_test.getStudentNo());
    }

    @Test
    void setStudentNo() {
        assertEquals(123,student_test.getStudentNo());
        student_test.setStudentNo(789);
        assertEquals(789,student_test.getStudentNo());
    }

    @Test
    void getPhoneNumber() {
        assertEquals(456,student_test.getPhoneNumber());
    }

    @Test
    void setPhoneNumber() {
        assertEquals(456,student_test.getPhoneNumber());
        student_test.setPhoneNumber(135);
        assertEquals(135,student_test.getPhoneNumber());
    }

    @Test
    void getNickName() {
        assertEquals("nick",student_test.getNickName());
    }

    @Test
    void setNickName() {
        assertEquals("nick",student_test.getNickName());
        student_test.setNickName("sean");
        assertEquals("sean",student_test.getNickName());
    }

    @Test
    void getPassword() {
        assertEquals("password",student_test.getPassword());
    }

    @Test
    void setPassword() {
        assertEquals("password",student_test.getPassword());
        student_test.setPassword("PASSWORD");
        assertEquals("PASSWORD",student_test.getPassword());
    }

}