package other.io;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.FileWriter;

/**
 * @Author zch
 * @Description
 * @createDate 2018/10/9
 **/
public class OpenCsvExampleA {

    /**
     * @Description:首次使用csv写入东西
     * @Author:zch
     * @CreateDate:22:34 2018/10/9
     * @Version:1.0
     */
    @Test
    public static void testA(){
        try {
            CSVWriter writer = new CSVWriter(new FileWriter("D:\\csv\\yourFile.csv"), '\t');
            // feed in your array (or convert your data to an array)
            String[] entries = "first#second#third".split("#");
            writer.writeNext(entries);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description:读取csv文件
     * @Author:zch
     * @CreateDate:22:37 2018/10/9
     * @Version:1.0
     */
    @Test
    public static void testB(){
        try {
            CSVReader reader = new CSVReader(new FileReader("D:\\csv\\yourFile.csv"));
            String [] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                System.out.println(nextLine[0]  + "etc...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
