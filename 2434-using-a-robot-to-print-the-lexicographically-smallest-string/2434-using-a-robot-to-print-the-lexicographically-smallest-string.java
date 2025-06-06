import java.util.*;

class Solution {
    public String robotWithString(String s) {
        int n = s.length();
        char[] minSuffix = new char[n];
        minSuffix[n - 1] = s.charAt(n - 1);

        // Precompute the minimum character from i to end
        for (int i = n - 2; i >= 0; i--) {
            minSuffix[i] = (char) Math.min(s.charAt(i), minSuffix[i + 1]);
        }

        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < n; i++) {
            char curr = s.charAt(i);
            stack.push(curr);

            // While the top of the stack is <= min of the rest of s, pop to result
            while (!stack.isEmpty() && (i == n - 1 || stack.peek() <= minSuffix[i + 1])) {
                result.append(stack.pop());
            }
        }

        return result.toString();
    }
}
