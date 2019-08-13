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

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Application {

    static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        int PERIOD = 1;
        ArrayList<String> names= new ArrayList<String>();
        names.add("First"); names.add("Second"); names.add("Third");
        Random rnd = new Random();

        BufferedReader bufferedReader = getBufferedReader();

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleWithFixedDelay(() -> {
            int count = 0;
            while(count < 50){
                try {
                    String message = bufferedReader.readLine();

                    if (!message.equals("")) {
                        Log log = new Log();
                        log.setDateTime(System.currentTimeMillis());
                        log.setService(names.get(rnd.nextInt(3)));
                        log.setMessage(message);
                        logger.info(message);
                        LogPost.sendRequest(log);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 1, PERIOD, TimeUnit.SECONDS);



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
