package com.miguelsanchez;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.PI;

class Operations {
    static int N = 101;
    static int D = 15;
    static double mean = 0.0;
    static double variance = 1;
    static double deviation = 1;

    private static BigDecimal erf (double xi) {
        BigDecimal x = new BigDecimal(xi);
        BigDecimal result = new BigDecimal (0.0);
        for (int n = 0; n<N; n++) {
            result = result.add(((new BigDecimal(Math.pow(-1, n))).multiply(elevate(x, 2 * n + 1))).divide(factorial(n).multiply(new BigDecimal(2 * n + 1)), D, RoundingMode.HALF_UP));
        }
        result = result.multiply(new BigDecimal (2/Math.sqrt(PI)));
        return result;
    }

    private static BigDecimal elevate (BigDecimal x, int n) {
        BigDecimal result = new BigDecimal (1);
        for (int i =0; i<n; i++) {
            result = result.multiply(x);
        }
        return result;
    }

    private static BigDecimal factorial (int n) {
        BigDecimal result = new BigDecimal (1);
        for (int i = n; i>0; i--) {
            result = result.multiply(new BigDecimal (i));
        }
        return result;
    }

    static BigDecimal finalResult (double z) {
        BigDecimal result = new BigDecimal(1).add(erf(z/Math.sqrt(2)));
        result = result.divide(new BigDecimal(2), D, RoundingMode.HALF_UP);

        return result;
    }
}
