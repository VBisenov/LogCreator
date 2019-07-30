package app;

import model.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import post.LogPost;

import java.sql.Time;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class Application {

    static final Logger logLogger = LogManager.getRootLogger();

    public static void main(String[] args) {
        BufferedReader bufferedReader = getBufferedReader();
        while(true){
            try {
                String message = bufferedReader.readLine();

                if (!message.equals("")) {
                    Log log = new Log();
                    log.setDateTime(new Time(new Date().getTime()));
                    log.setService("Temporary service");
                    log.setMessage(message);
                    logLogger.info(message);
                    LogPost.sendRequest(log);
                    Thread.sleep(1000);
                } else System.out.println("Empty string");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static BufferedReader getBufferedReader(){
        FileReader fr = null;
        BufferedReader bf = null;
        try {
            fr = new FileReader("Carrie.txt");
            bf = new BufferedReader(fr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bf;
    }
}
