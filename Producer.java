import java.util.concurrent.Semaphore;

public class Producer{
    static Semaphore mutex = new Semaphore(1, true);
    
    static class ProducerThread extends Thread {
        String id = "";
        ProducerThread(String id){
            this.id = id;
        }
        public void run(){
            try{
                System.out.println("Procucer ID: " + id);
                mutex.acquire();
                System.out.println("Acquired the mutex");
                //Put some size into the repository


            }
            catch(Exception e){
                System.out.println(e);
            }
            finally{
                System.out.println("Release the mutex");
                mutex.release();
            }
            
        }
    }
    public static void main(String[] args){
        int repo[] = new int[10];
        ProducerThread A = new ProducerThread("AThread");
        ProducerThread B = new ProducerThread("BThread");
        ProducerThread C = new ProducerThread("CThread");
        A.run();
        B.run();
        C.run();

    }
    
}