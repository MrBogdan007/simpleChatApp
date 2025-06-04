import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunTester {
    public static void main(String[] args) {
        Runnable threadJob = new MyRunnable();
        ExecutorService threadExecutor = Executors.newSingleThreadExecutor();
        threadExecutor.execute(threadJob);
        System.out.println(Thread.currentThread().getName()+": back in main");
        tellStory();
        Thread.dumpStack();
        threadExecutor.shutdown();
    }

    private static void tellStory() {
        System.out.println("Story");
    }

}
