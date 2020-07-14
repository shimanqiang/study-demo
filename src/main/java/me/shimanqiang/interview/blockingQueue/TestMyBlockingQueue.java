package me.shimanqiang.interview.blockingQueue;

/**
 * @author shimanqiang
 * @since 2020/7/1 22:03
 */
public class TestMyBlockingQueue {

    public static void main(String[] args) throws Exception {
        MyBlockingQueue queue = new MyBlockingQueue(5);
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    queue.put(i);
                    System.out.println("生产：" + i);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (; ; ) {
                try {

                    System.out.println("消费：" + queue.take());
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
