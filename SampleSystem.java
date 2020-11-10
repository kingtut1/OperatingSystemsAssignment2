import java.util.concurrent.Semaphore;

import Producer.ProducerThread;

public class SampleSystem {
    //array 
    static Semaphore mutex = new Semaphore(1, true);
    public static void main(String[] args){
        //ProducerThread A = new ProducerThread("AThread");
        Producer.ProducerThread A = new Producer.ProducerThread("AThread");
        A.run();
        

    }
}
