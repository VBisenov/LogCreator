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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Application {

    static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        int PERIOD = 1;

        BufferedReader bufferedReader = getBufferedReader();

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleWithFixedDelay(() -> {
            while(true){
                try {
                    String message = bufferedReader.readLine();

                    if (!message.equals("")) {
                        Log log = new Log();
                        log.setDateTime(new Long(new Date().getTime()));
                        log.setService("Temporary service");
                        log.setMessage(message);
                        logLogger.info(message);
                        LogPost.sendRequest(log);
                        Thread.sleep(1000);
                    } else System.out.println("Empty string");
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, PERIOD, TimeUnit.SECONDS);



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