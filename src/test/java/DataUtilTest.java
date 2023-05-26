import ConstantPacket.ConstantParameters;
import entity.Student;
import entity.Subject;
import org.junit.jupiter.api.Test;
import util.DataUtil;
import util.IOUtil;

import static org.junit.jupiter.api.Assertions.*;

class DataUtilTest {
    // Static class testing
    // No need to create a calss
    @Test
    void checkPhoneNumber() {
        assertTrue(DataUtil.checkPhoneNumber("12312312"));
        assertTrue(DataUtil.checkPhoneNumber("12345678"));
        assertFalse(DataUtil.checkPhoneNumber("1678"));
        assertFalse(DataUtil.checkPhoneNumber("Abcd1213"));
        assertFalse(DataUtil.checkPhoneNumber("1234321A"));
    }

    @Test
    void checkPassword() {
        assertTrue(DataUtil.checkPassword("Ab123"));
        assertTrue(DataUtil.checkPassword("123Cd"));
        assertFalse(DataUtil.checkPassword("Test"));
        assertFalse(DataUtil.checkPassword("test123"));
    }

    @Test
    void calculateGPA() {
        IOUtil.readJson();
        assertEquals(80.0,DataUtil.calculateGPA(ConstantParameters.studentMap.get(2020001)));
        assertEquals(85.0,DataUtil.calculateGPA(ConstantParameters.studentMap.get(2020002)));
    }

    @Test
    void calculateGPARank() {
    }
}