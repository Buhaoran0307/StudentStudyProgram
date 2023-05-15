import org.junit.jupiter.api.Test;
import util.IOUtil;

import java.util.Map;

public class TestIOUtil {
    @Test
    public void testMap(){
        Map<String ,String> map = IOUtil.readSubjectHelper();
        System.out.println(map);
    }
}
