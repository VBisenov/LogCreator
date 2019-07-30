package app;

import model.Log;
import post.LogPost;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        BufferedReader bufferedReader = getBufferedReader();
        while(true){
            try {
                String message = bufferedReader.readLine();

                if (!message.equals("")) {
                    Log log = new Log();
                    log.setDateTime(System.currentTimeMillis());
                    log.setService("Temporary service");
                    log.setMessage(message);
                    System.out.println(message);
                    LogPost.sendRequest(log);
                    Thread.sleep(1000);
                } else System.out.println("Empty string");
            } catch (InterruptedException | IOException e) {
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
