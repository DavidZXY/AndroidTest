public class InterruptedThread extends Thread {
    @Override
    public void run() {
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        interrupted();
    }
}
