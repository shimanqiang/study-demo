package me.shimanqiang.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2020/2/13.
 */
public class CpuTest {

    /**
     * https://blog.csdn.net/smart_ferry/article/details/85345270
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //获得当前系统的CPU数量，根据这个数值创建对应数量的线程
        Runtime r = Runtime.getRuntime();
        int cpuCont = r.availableProcessors();
        System.out.println("Cpu个数：" + cpuCont);
        ExecutorService pool = Executors.newFixedThreadPool(cpuCont);
        for (int i = 0; i < r.availableProcessors(); i++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    long busyTime = 10;
                    long idleTime = busyTime;
                    while (true) {
                        long nowSys = System.currentTimeMillis();
                                                                                                   while ((System.currentTimeMillis() - nowSys) <= busyTime) {
                        }
                        try {
                            Thread.sleep(idleTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        pool.shutdown();

    }
}
