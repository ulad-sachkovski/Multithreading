package ForkJoinFactorial;

import java.math.BigInteger;

public class MakeBigIntArray {

    private final BigInteger[] array;

    public MakeBigIntArray(int size) {
        array = new BigInteger[size];
        for (int i = 0; i < size; i++) {
            array[i] = new BigInteger("" + (i+1));
        }
    }

    public BigInteger[] getArray() {
        return array;
    }
}
