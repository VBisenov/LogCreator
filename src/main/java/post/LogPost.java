package post;

import model.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LogPost {
    public static void sendRequest(Log log) {
        Logger logger = LoggerFactory.getLogger(LogPost.class);

        HttpURLConnection conn = null;
        URL url = null;
        try {
            url = new URL("http://localhost:88/log");
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=windows-1251");

            String input = "{ \"message\" : \""+log.getMessage()+"\", " +
                    "\"dateTime\" : \""+log.getDateTime()+"\", " +
                    "\"source\" : \""+log.getService()+"\" }";

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();


            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output;
            if (conn.getResponseCode() != 201) {
                logger.warn(conn.getResponseCode() + " ");
                while ((output = br.readLine()) != null) {
                    System.out.println(output);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}