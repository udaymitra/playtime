package utils;

import java.util.Random;
import java.util.UUID;

/**
 * Created by soumya on 8/5/15.
 */
public class IdGenerator {

    /**
     * In a perftest, this function generated duplicate ids only 2 times in 10M iterations.
     * Each call to this function is timed at 2 micro seconds.
     */
    public static long generateId() {
        long seed = Math.abs(UUID.randomUUID().getMostSignificantBits());

        long seed1 = seed | 0xFFFF;
        long seed2 = seed >> 32;

        long val = new Random(seed1).nextInt(10000000);
        val = val << 24;
        val += new Random(seed2).nextInt(10000000);

        return Math.abs(val);
    }
}
