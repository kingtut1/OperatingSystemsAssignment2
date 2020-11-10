import java.util.concurrent.Semaphore;

public class SampleSystem {
    static Semaphore mutex = new Semaphore(1, true);

    // array with a max size of 10
    static String repo[] = new String[10];
    static int size = 0;
    

    public static class ProducerThread extends Thread {
        String id = "";

        ProducerThread(String id) {
            this.id = id;
        }

        public void run() {
            try {
                mutex.acquire();
                System.out.println("Procucer ID: " + id);
                System.out.println("Acquired the mutex");
                printRepo();
                // Put some size into the repository
                repo[size] = id;
                size++;

            } catch (Exception e) {
                System.out.println(e);
            } finally {
                System.out.println("Release the mutex");
                mutex.release();
            }

        }

    }

    public static class ConsumerThread extends Thread {
        String id = "";

        ConsumerThread(String id) {
            this.id = id;
        }

        public void run() {
            try {
                //Check to see if producer and another consumer is not writing
                mutex.acquire();
                System.out.println("Consumer ID: " + id);
                System.out.println("Acquired the mutex");
                printRepo();
                if(size != 0){
                    // Take some size out of the repository
                    repo[size] = null;
                    size--;
                }
                

            } catch (Exception e) {
                System.out.println(e);
            } finally {
                System.out.println("Release the mutex");
                mutex.release();
            }

        }
    }

    private static void printRepo(){
        System.out.println("Current state of repo");
        System.out.print("{ ");
        for(int i = 0; i < size; i++){
            System.out.print(repo[i] + " , ");
        }
        System.out.println("}");
    }
    public static void main(String[] args) {
        System.out.println("Run");
        
        ProducerThread A = new ProducerThread("AThread");
        ProducerThread B = new ProducerThread("BThread");
        ProducerThread C = new ProducerThread("CThread");
        ConsumerThread D = new ConsumerThread("DThread");
        ConsumerThread E = new ConsumerThread("EThread");
        ConsumerThread F = new ConsumerThread("FThread");
        ConsumerThread G = new ConsumerThread("GThread");


        A.start();
        B.start();
        C.start();
        D.start();
        E.start();
        F.start();
        G.start();
        
    }
}
