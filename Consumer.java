import java.util.concurrent.Semaphore;

public class Consumer {
    static class ConsumerThread extends Thread {
        String id = "";
        ConsumerThread(String id){
            this.id = id;
        }
        public void run(){
            System.out.println("Consumer ID: " + id);
        }
    }
}
