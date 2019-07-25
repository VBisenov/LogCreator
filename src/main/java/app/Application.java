package app;

import model.Log;
import post.LogPost;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

public class Application {
    public static void main(String[] args) {
        BufferedReader bufferedReader = getBufferedReader();
        while(true){
            try {
                String message = bufferedReader.readLine();


                Log log = new Log();
                log.setDateTime(LocalDateTime.now());
                log.setService("Temporary service");
                log.setMessage(message);
                LogPost.sendRequest(log);

                Thread.sleep(10000);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static BufferedReader getBufferedReader(){
        FileReader fr = null;
        BufferedReader bf = null;
        try {
            fr = new FileReader("a_single_man.txt");
            bf = new BufferedReader(fr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bf;
    }
}
