package Socket;

/**
 * Created by yml on 16-5-16.
 */
public class Test extends Thread {
    public static void main(String[] args) {
        new Test().start();
    }

    public void run() {
        for (int i = 0; i < 1000000; i++) {
            if (i % 10 == 0) {
                System.out.println("-------" + i);
            }
            try {
                Thread.sleep(10);
                System.out.println("线程睡眠一秒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
