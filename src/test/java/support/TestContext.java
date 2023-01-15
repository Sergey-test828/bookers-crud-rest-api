package support;

import org.yaml.snakeyaml.Yaml;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TestContext {

    private static final Map<String, Object> testData = new HashMap();

    public static void saveData(String key, Object data) {
        testData.put(key, data);
    }
    public static Integer getTestDataInteger(String key) {
        return (Integer) testData.get(key);
    }
    public static Map<String, String> getDataByFileName(String fileName) {
        return new Yaml().load(getStream(fileName));
    }
    private static InputStream getStream(String fileName) {
        String path = System.getProperty("user.dir") + "/src/test/resources/data/" + fileName + ".yml";
        try {
            return new FileInputStream(path);
        } catch (FileNotFoundException e) {
            throw new Error(e);
        }
    }
    public static Booking getBooking(String type) {
        return new Yaml().loadAs(getStream(type), Booking.class);
    }
}