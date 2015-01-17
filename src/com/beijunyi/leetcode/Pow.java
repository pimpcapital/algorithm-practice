package com.beijunyi.leetcode;

/**
 * Implement pow(x, n).
 */
public class Pow {

    public static class Solution2 {
        public double pow(double x, int n) {
            if(n == 0)
                return 1;
            if(n == 1)
                return x;
            if(n == -1)
                return 1 / x;
            double sub = pow(x, n/2);
            double result = sub * sub;
            int r = n % 2;
            if(r != 0) {
                if(r == 1)
                    result *= x;
                else
                    result /= x;
            }
            return result;
        }
    }

    /**
     *   x^7 = x^(1+2+4) = (x^1)(x^2)(x^4)
     */
    public static class Solution1 {
        public double pow(double x, int n) {
            if (n < 0) {
                n = -n;
                x = 1 / x;
            }
            double result = 1;
            for (double f = x; n > 0; n = n >> 1) {
                if (n % 2 == 1) {
                    result *= f;
                }
                f = f * f;
            }
            return result;
        }
    }

    public static void main(String args[]) {
        System.out.println(new Solution1().pow(123.45, -12));
        System.out.println(new Solution2().pow(123.45, -12));
    }

}
