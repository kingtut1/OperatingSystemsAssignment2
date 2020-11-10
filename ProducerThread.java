import java.util.concurrent.Semaphore;

public class ProducerThread extends Thread {
    static Semaphore producerReady = new Semaphore(1, true);
    String id = "";

    ProducerThread(String id) {
        this.id = id;
    }

    public void run() {
        try {
            producerReady.acquire();
            System.out.println("Procucer ID: " + id);
            System.out.println("Acquired the mutex");
            // Put some size into the repository
            
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Release the mutex");
            producerReady.release();
        }

    }
    public static void main(String[] args) {
        int repo[] = new int[10];
        ProducerThread A = new ProducerThread("AThread");
        ProducerThread B = new ProducerThread("BThread");
        ProducerThread C = new ProducerThread("CThread");

        A.start();
        B.start();
        C.start();

    }
    

}