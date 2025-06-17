import java.util.Arrays;

public class Solution {
    private static final int MOD = (int)1e9 + 7;
    private int[] fact;
    private int[] invFact;

    private int binaryExp(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                res = (res * a) % MOD;
            }
            a = (a * a) % MOD;
            b >>= 1;
        }
        return (int)res;
    }

    private int mmi(int val) {
        return binaryExp(val, MOD - 2);
    }

    private void inverseFact(int n) {
        invFact = new int[n + 1];
        invFact[n] = mmi(fact[n]);
        for (int i = n; i > 0; i--) {
            invFact[i - 1] = (int)((1L * invFact[i] * i) % MOD);
        }
    }

    private void factorial(int n) {
        fact = new int[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = (int)((1L * i * fact[i - 1]) % MOD);
        }
    }

    private void precompute(int n) {
        factorial(n);
        inverseFact(n);
    }

    public int countGoodArrays(int n, int m, int k) {
        precompute(n);

        int runWays = (int)((1L * fact[n - 1] * invFact[n - k - 1] % MOD * invFact[k]) % MOD);
        int waysToAssign = (int)(1L * m * binaryExp(m - 1, n - k - 1) % MOD);
        return (int)(1L * runWays * waysToAssign % MOD);
    }
}