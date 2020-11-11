import java.util.Random;
import java.util.concurrent.Semaphore;

public class ConsumerThread extends Thread {
    static Semaphore consumerReady = new Semaphore(1, true);
    String id = "";

    ConsumerThread(String id) {
        this.id = id;
    }

    public void run() {
        try {
            consumerReady.acquire();
            System.out.println("Consumer ID: " + id);
            System.out.println("Acquired the mutex");
            // Take some size out of the repository

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Release the mutex");
            consumerReady.release();
        }

    }

    public static void main(String[] args){
        /*
        ConsumerThread D = new ConsumerThread("DThread");
        ConsumerThread E = new ConsumerThread("EThread");
        ConsumerThread F = new ConsumerThread("FThread");
        ConsumerThread G = new ConsumerThread("GThread");

        D.start();
        E.start();
        F.start();
        G.start();
        */
        int maxSize = 0;
        Random rand = new Random();
        int randomNum;// = 1 + rand.nextInt(maxSize - 1 + 1);
        for(int i = 0; i < 10; i++){
            randomNum = 1 + rand.nextInt(maxSize - 1 + 1);
            System.out.println("Random:" + randomNum);
        }

    }


}
