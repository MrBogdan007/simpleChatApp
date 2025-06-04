public class MyRunnable implements Runnable{
    @Override
    public void run() {
        go();
    }

    private void go() {
        doMore();
    }

    private void doMore() {
        System.out.println(Thread.currentThread().getName()+ ": top of the stack");
        Thread.dumpStack();
    }
}
