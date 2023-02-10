package SupplierConsumer;

import java.util.LinkedList;

public class ProducerConsumer {

    LinkedList<Integer> list = new LinkedList<>();
    int capacity = 2;

    public void produce() throws InterruptedException {
        int value = 0;
        while (value <= 10) {
            synchronized (this) {
                while (list.size() == capacity) {
                    wait();
                }
                System.out.println("Producer produced - " + value);
                list.add(value++);
                notifyAll();
                Thread.sleep(400);
            }
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (list.size() == 0) {
                    wait();
                }
                int value = list.removeFirst();
                System.out.println("Consumer consumed - " + value);
                notifyAll();
                Thread.sleep(400);
                if (value == 10) break;
            }
        }
    }
}
