package ForkJoinFactorial;

import java.math.BigInteger;
import java.time.Clock;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        Clock clock = Clock.systemDefaultZone();
        long start, stop, duration1, duration2;
        MakeBigIntArray test = new MakeBigIntArray(20000);

        int nThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("Available processors are: " + nThreads);

        // Parallel results
        RecursiveFactorial rSum = new RecursiveFactorial(test.getArray());
        ForkJoinPool pool = new ForkJoinPool(nThreads);
        start = clock.millis();
        pool.invoke(rSum);
        stop = clock.millis();
        duration1 = stop - start;
        BigInteger result = rSum.result;
        System.out.println("Time for parallel calculating is ms: " + duration1);
        System.out.println("The result is: " + result);
        
        // Sequential results
        BigInteger sum = new BigInteger("1");
        start = clock.millis();
        for (int i = 0; i < test.getArray().length; i++) {
            sum = sum.multiply(test.getArray()[i]);
        }
        stop = clock.millis();
        duration2 = stop - start;
        System.out.println("Time for sequential calculation is ms: " + duration2);
        System.out.println("The result is: " + sum);

        String coef = String.format("%.2f",(double)duration2/duration1);
        System.out.println();
        System.out.println("Parallel calculation is " + coef + "x higher, than sequential");
    }
}
