import java.util.Random;
import java.util.concurrent.Semaphore;

public class SampleSystem {
    static Semaphore mutex = new Semaphore(1, true);

    // array with a max size of 10
    static String repo[] = new String[10];
    static int size = 0;

    public static class ProducerThread extends Thread {
        String id = "";
        int maxSize = 0;
        int threadSize = 0;

        ProducerThread(String id, int maxSize) {
            this.id = id;
            this.maxSize = maxSize;
        }

        public void run() {
            try {
                mutex.acquire();
                System.out.println(id + " acquired the mutex");
                printRepo();
                if (threadSize < maxSize) {
                    // Put some size into the repository
                    // Size is randomly generated between 1 element to the max size that is allowed for this producer
                    Random rand = new Random();
                    int randomNum = 1 + rand.nextInt(maxSize - 1 + 1);
                    System.out.println(id + " is producing a size of: " + randomNum);

                    for( int i = 0; i < randomNum; i++){
                        repo[size] = id;
                        size++;
                        threadSize++;
                    }
        
                }

            } catch (Exception e) {
                System.out.println(e);
            } finally {
                printRepo();
                System.out.println(id + " is releasing the mutex");
                
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
            // while(true){
            try {
                // Check to see if producer and another consumer is not writing
                mutex.acquire();
                System.out.println(id + " acquired the mutex");
                
                    
                //System.out.println("size:" + size);
                if (size != 0) {
                    //Generate some random size to take out of the repo
                    Random rand = new Random();
                    int randomNum = 1 + rand.nextInt(size - 1 + 1);
                    System.out.println(id + " is consuming a size of " + randomNum);

                    // Take some size out of the repository
                    //repo[size] = null;
                    //size--;
                    
                    for(int i = 0; i < randomNum; i++){
                        repo[size] = null;
                        size--;
                    }
                    
                    
                }
                else{
                    System.out.println(id + " - I cannot do anything because size is 0");
                }

            } catch (Exception e) {
                System.out.println(e);
            } finally {
                System.out.println(id + " is releasing the mutex");
                printRepo();
                mutex.release();
            }
            // }

        }
    }

    private static void printRepo() {
        System.out.println("Current state of repo");
        System.out.print("{ ");
        for (int i = 0; i < size; i++) {
            System.out.print(repo[i] + " , ");
        }
        System.out.println("}");
    }

    public static void main(String[] args) {
        System.out.println("Run");

        ProducerThread A = new ProducerThread("AThread", 2);
        ProducerThread B = new ProducerThread("BThread", 3);
        ProducerThread C = new ProducerThread("CThread", 4);

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
