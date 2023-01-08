package CounterBack;

import java.util.concurrent.TimeUnit;

public class CounterBack {
    static volatile boolean switcher = false;

    public static void main(String[] args) {

       Thread thread1 =  new Thread( () -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    switcher = !switcher;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
       thread1.setDaemon(true);

       Thread thread2 = new Thread( () -> {
            int i = 30;
            while (i >= 0) {
              //  while (true) {
                    if (switcher) {
                        try {
                            System.out.println(i);
                            i--;
                            TimeUnit.MILLISECONDS.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                //    }
                }
            }
       });
       thread1.start();
       thread2.start();
    }
}
