package ForkJoinFactorial;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class RecursiveFactorial extends RecursiveAction{

    private BigInteger[] array;
    public BigInteger result;

    public RecursiveFactorial(BigInteger[] array) {
        this.array = array;
    }

    @Override
    protected void compute() {
        if (array.length == 1) {
            result = array[0];
        } else {
            int midpoint = array.length / 2;
            BigInteger[] l1 = Arrays.copyOfRange(array,0,midpoint);
            BigInteger[] l2 = Arrays.copyOfRange(array,midpoint,array.length);
            RecursiveFactorial rf1 = new RecursiveFactorial(l1);
            RecursiveFactorial rf2 = new RecursiveFactorial(l2);
            rf1.fork();
            rf2.fork();
            rf1.join();
            rf2.join();
            result = rf1.result.multiply(rf2.result);
        }
    }
}
