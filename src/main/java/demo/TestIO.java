import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TestIO {
    public static void main(String[] args) {
        File file = new File("hello.txt");
        try {
            boolean b = file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //FileInputStream fileInputStream = null;
        //try{
        //    fileInputStream = new FileInputStream(file);
        //} catch (Exception e) {
        //    e.fillInStackTrace();
        //}
        String s = "23452";
        System.out.println(s.contains("2"));
    }
}
