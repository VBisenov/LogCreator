package post;

import model.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LogPost {
    public static void sendRequest(Log log) {
        try {
            URL url = new URL("http://localhost:88/log");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=windows-1251");

            String input = "{ \"message\" : \""+log.getMessage()+"\", " +
//                    "\"dateTime\" : \""+log.getDateTime()+"\", " +
                    "\"source\" : \""+log.getService()+"\" }";

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output;
            System.out.println("Output: ");
            while ((output = br.readLine()) != null){
                System.out.println(output);
            }
            conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
