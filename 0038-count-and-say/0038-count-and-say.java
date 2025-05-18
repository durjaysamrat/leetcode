class Solution {
    public String countAndSay(int n) {
        if (n <= 0) return "";

        String result = "1";  // base case: countAndSay(1)

        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            int count = 1;
            char prevChar = result.charAt(0);

            for (int j = 1; j < result.length(); j++) {
                char currentChar = result.charAt(j);
                if (currentChar == prevChar) {
                    count++;
                } else {
                    sb.append(count).append(prevChar);
                    count = 1;
                    prevChar = currentChar;
                }
            }

            // Append the last group
            sb.append(count).append(prevChar);
            result = sb.toString();  // update for next iteration
        }

        return result;
    }
}
