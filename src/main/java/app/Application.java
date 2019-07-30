package app;

import model.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import post.LogPost;

import java.sql.Time;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class Application {

    static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        BufferedReader bufferedReader = getBufferedReader();
        int delay = 1000;
        while(true){
            try {
                String message = bufferedReader.readLine();

                if (!message.equals("")) {
                    Log log = new Log();
                    log.setDateTime(System.currentTimeMillis());
                    log.setService("Temporary service");
                    log.setMessage(message);
                    logger.info(log+" shipped");
                    LogPost.sendRequest(log);
                    Thread.sleep(delay);
                } else System.out.println("Empty string");
            } catch (IOException | InterruptedException e) {
                logger.error("Unknown error", e);
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