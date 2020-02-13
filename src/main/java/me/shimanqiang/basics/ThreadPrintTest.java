package me.shimanqiang.basics;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用三个线程按顺序循环打印 abc 三个字母，比如 abcabcabc。
 *
 * @author shimanqiang
 * @since 2020/2/4 12:01
 */
public class ThreadPrintTest {

    public static void main(String[] args) throws Exception {
        PrintWorker printWorker = new PrintWorker(3);

        PrintAThread printAThread = new PrintAThread(printWorker);
        PrintBThread printBThread = new PrintBThread(printWorker);
        PrintCThread printCThread = new PrintCThread(printWorker);

        printCThread.start();
        printBThread.start();
        printAThread.start();

        printWorker.work();
    }
}

class PrintWorker {
    private volatile boolean canRun = false;
    private Lock lock = new ReentrantLock();

    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    private int printCount;

    public PrintWorker(int printCount) {
        this.printCount = printCount;
    }


    public void work() {
        while (!canRun) {
            //nothing
        }
        try {
            lock.lock();
            conditionA.signal();
        } finally {
            lock.unlock();
        }
    }

    public void printA() {
        try {
            lock.lock();
            canRun = true;
            for (int i = 0; i < printCount; i++) {
                conditionA.await();
                System.out.print("a");
                conditionB.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        try {
            lock.lock();
            for (int i = 0; i < printCount; i++) {
                conditionB.await();
                System.out.print("b");
                conditionC.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        try {
            lock.lock();
            for (int i = 0; i < printCount; i++) {
                conditionC.await();
                System.out.print("c");
                conditionA.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class PrintAThread extends Thread {
    private PrintWorker printWorker;

    public PrintAThread(PrintWorker printWorker) {
        this.printWorker = printWorker;
    }

    @Override
    public void run() {
        printWorker.printA();
    }
}

class PrintBThread extends Thread {
    private PrintWorker printWorker;

    public PrintBThread(PrintWorker printWorker) {
        this.printWorker = printWorker;
    }

    @Override
    public void run() {
        printWorker.printB();
    }
}

class PrintCThread extends Thread {
    private PrintWorker printWorker;

    public PrintCThread(PrintWorker printWorker) {
        this.printWorker = printWorker;
    }

    @Override
    public void run() {
        printWorker.printC();
    }
}
