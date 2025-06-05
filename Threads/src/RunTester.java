import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RunTester {
    public static void main(String[] args) {
        Runnable threadJob = new MyRunnable();
        ExecutorService threadExecutor = Executors.newSingleThreadExecutor();
        CountDownLatch latch = new CountDownLatch(1);
        threadExecutor.execute(() -> waitForLatchThenPrint(latch));
        System.out.println("back in main");
        latch.countDown();
        threadExecutor.shutdown();
    }

    private static void waitForLatchThenPrint(CountDownLatch latch) {
        try {
            latch.await();
            System.out.println("Top of the stack");
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }

    }


}
