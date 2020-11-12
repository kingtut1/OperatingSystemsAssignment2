import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SampleSystem {
    static Semaphore mutex = new Semaphore(1, true);

    // array with a max size of 100
    static String repo[] = new String[100]; //Make sure your max thread sizes dont exceed 100
    static int size = 0;

    public static class ProducerThread extends Thread {
        String id = "";
        int maxSize = 0;
        int threadSize = 0;

        // Constructor that takes an id and the maxSize that the thread can hold.
        ProducerThread(String id, int maxSize) {
            this.id = id;
            this.maxSize = maxSize;
        }

        public void run() {
            while (true) {
                try {
                    mutex.acquire();
                    System.out.println(id + " acquired the mutex");
                    System.out.println(id + " has a threadsize of " + threadSize);
                    printRepo();
                    if (threadSize < maxSize) {
                        // Size is randomly generated between 1 element to the max size that is allowed for this producer
                        Random rand = new Random();
                        int randomNum = 1 + rand.nextInt(maxSize - 1 + 1);
                        System.out.println(id + " is producing a size of: " + randomNum);

                        // Put some random size into the repository
                        for (int i = 0; i < randomNum; i++) {
                            repo[size] = id;
                            size++;
                            threadSize++;
                        }

                    }
                    else{
                        System.out.println(id + " cannot produce anymore because reached max size limit");
                    }

                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    printRepo();
                    System.out.println(id + "'s current threadsize: " + threadSize);
                    System.out.println(id + " is releasing the mutex\n\n");
                    mutex.release();
                    
                }
            }

        }

    }

    public static class ConsumerThread extends Thread {
        String id = ""; 
        ProducerThread A;
        ProducerThread B;
        ProducerThread C;

        //Consumer takes in an id, along with the other 3 producers
        ConsumerThread(String id, ProducerThread A, ProducerThread B, ProducerThread C) {
            this.id = id;
            this.A = A;
            this.B = B;
            this.C = C;
        }

        public void run() {
            while (true) {
                try {
                    mutex.acquire();
                    System.out.println(id + " acquired the mutex");
                    printRepo();

                    if (size != 0) {
                        // Generate some random size to take out of the repo
                        Random rand = new Random();
                        int randomNum = 1 + rand.nextInt(size - 1 + 1);
                        System.out.println(id + " is consuming a size of " + randomNum);

                        // Take some size out of the repository
                        for (int i = 0; i < randomNum; i++) {
                            //Decreasing from the respective threads
                            if( A.id == repo[size-1]){
                                A.threadSize -= 1;
                            }
                            else if( B.id == repo[size-1]){
                                B.threadSize -= 1;
                            }
                            else if( C.id == repo[size-1]){
                                C.threadSize -= 1;
                            }
                            repo[size-1] = null;
                            size--;
                        }

                    } 
                    else {
                        System.out.println(id + " cannot do anything because the size of the repo is 0");
                    }

                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    printRepo();
                    System.out.println(id + " is releasing the mutex\n\n");
                    mutex.release();
                }
            }

        }
    }

    private static void printRepo() {
        System.out.print("Current state of repo: { ");
        for (int i = 0; i < size; i++) {
            System.out.print(repo[i] + " , ");
        }
        System.out.println("}");
    }

    public static void main(String[] args) throws InterruptedException {

        ProducerThread A = new ProducerThread("AThread", 5);
        ProducerThread B = new ProducerThread("BThread", 6);
        ProducerThread C = new ProducerThread("CThread", 1);

        ConsumerThread D = new ConsumerThread("DThread", A, B, C);
        ConsumerThread E = new ConsumerThread("EThread", A, B, C);
        ConsumerThread F = new ConsumerThread("FThread", A, B, C);
        ConsumerThread G = new ConsumerThread("GThread", A, B, C);

        A.start();
        B.start();
        C.start();
        D.start();
        E.start();
        F.start();
        G.start();

    }
}
