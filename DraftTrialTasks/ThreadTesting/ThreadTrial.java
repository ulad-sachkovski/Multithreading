package ThreadTesting;

// Идея в том, что есть max потоков, которые могут работать одновременно
// Ведется счетчик count кол-ва потоков. В данном примере 3 потока, одновременно могут работать только 2
// Третий поток должен запуститься, как отработает первый или второй (через 2 сек). Но он не запускается((9999(((

import java.util.ArrayList;

public class ThreadTrial extends Thread{

    private final Object lock = new Object();

    private static volatile int freeSlots = 2;

    public synchronized void run() {
        synchronized (lock) {
            while (freeSlots == 0) {
                try {
                   lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
            this.makeAction();
            this.release();
        }

    private synchronized void makeAction() {
        synchronized (lock) {
            freeSlots--;
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private synchronized void release() {
        synchronized (lock){
            freeSlots++;
            lock.notify();
        }
    }


    public static void main(String[] args) {

        ArrayList<Thread> listThread = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            listThread.add(new ThreadTrial());
        }
        for (Thread thread: listThread) {
            thread.start();
        }
    }
}
