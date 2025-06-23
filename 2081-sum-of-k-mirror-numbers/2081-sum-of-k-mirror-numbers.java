import java.util.*;

public class Solution {
    public long kMirror(int k, int n) {
        long sum = 0;
        int count = 0;
        int length = 1;

        while (count < n) {
            List<Long> palindromes = generateBase10Palindromes(length);
            for (long num : palindromes) {
                if (isPalindromeInBaseK(num, k)) {
                    sum += num;
                    count++;
                    if (count == n) return sum;
                }
            }
            length++;
        }

        return sum;
    }

    // Generate all base-10 palindromes of a specific digit length
    private List<Long> generateBase10Palindromes(int length) {
        List<Long> result = new ArrayList<>();
        int halfLen = (length + 1) / 2;
        long start = (long) Math.pow(10, halfLen - 1);
        long end = (long) Math.pow(10, halfLen);

        for (long i = start; i < end; i++) {
            String left = Long.toString(i);
            String right = new StringBuilder(left.substring(0, length % 2 == 0 ? left.length() : left.length() - 1))
                                .reverse().toString();
            String palindrome = left + right;
            result.add(Long.parseLong(palindrome));
        }

        return result;
    }

    // Check if number is a palindrome in base-k
    private boolean isPalindromeInBaseK(long num, int k) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(num % k);
            num /= k;
        }
        String s = sb.toString();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) return false;
        }
        return true;
    }

}
