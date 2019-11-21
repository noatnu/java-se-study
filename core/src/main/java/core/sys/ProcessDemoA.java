package core.sys;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by zch on 2019-11-15.
 */
public class ProcessDemoA {

    @Test
    public void testA() {
        ProcessBuilder processBuilder = new ProcessBuilder("ipconfig", "/all");
        try {
            Process process = processBuilder.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));

            String line;
            while ((line = br.readLine()) != null) {
                if (line.indexOf("IPv4") != -1) {
                    System.out.println(line);
                }
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
