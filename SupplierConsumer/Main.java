package SupplierConsumer;

public class Main {



    public static void main(String[] args) {
        final ProducerConsumer prodCons = new ProducerConsumer();

        Thread prodThread = new Thread( () -> {
            try {
                prodCons.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread consThread = new Thread(() -> {
            try {
                prodCons.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        prodThread.start();
        consThread.start();

        try {
            prodThread.join();
            consThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
