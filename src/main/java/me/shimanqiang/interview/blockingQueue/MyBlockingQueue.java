package me.shimanqiang.interview.blockingQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shimanqiang
 * @see ArrayBlockingQueue
 * @see LinkedBlockingQueue
 * @since 2020/7/1 21:39
 */
public class MyBlockingQueue {
    /**
     * 队列元素
     */
    private List<Object> container = new ArrayList<>();

    /**
     * 队列元素个数
     */
    private int count;
    /**
     * 队列容量
     */
    private int capacity;

    /**
     * 锁
     */
    private ReentrantLock lock = new ReentrantLock();

    /**
     * 条件
     */
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();


    public MyBlockingQueue(int capacity) {
        if (capacity <= 0) {
            capacity = 32;
        }
        this.capacity = capacity;
    }

    public void put(Object data) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (count >= capacity) {
                notFull.await();
            }
            container.add(data);
            count++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            Object data = container.get(0);
            container.remove(data);
            count--;
            notFull.signal();
            return data;
        } finally {
            lock.unlock();
        }
    }

}
