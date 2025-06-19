import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RunThreads {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.execute(()->runJobs("Job1"));
        threadPool.execute(()->runJobs("Job2"));
        threadPool.shutdown();
        try{
            boolean finished = threadPool.awaitTermination(5, TimeUnit.SECONDS);
            System.out.println("Finished ? " + finished);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        threadPool.shutdownNow();
    }
    public static void runJobs(String jobName){
       String threadName =  Thread.currentThread().getName();
        for(int i= 0; i <= 25; i++){
            System.out.printf("Job %s is running on thread:%s  ",jobName,threadName);
            System.out.println();
        }
    }
}
