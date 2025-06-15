class Solution {
    public int maxDiff(int num) {
        String original = Integer.toString(num);

        // Create maxString by replacing first non-9 digit with 9
        char firstNon9 = '9';
        for (char c : original.toCharArray()) {
            if (c != '9') {
                firstNon9 = c;
                break;
            }
        }

        StringBuilder maxString = new StringBuilder();
        for (char c : original.toCharArray()) {
            if (c == firstNon9) {
                maxString.append('9');
            } else {
                maxString.append(c);
            }
        }
        int maxVal = Integer.parseInt(maxString.toString());

        // Create minString with specific logic
        char firstChar = original.charAt(0);
        StringBuilder minString = new StringBuilder();

        if (firstChar != '1') {
            // Replace all occurrences of first digit with '1'
            for (char c : original.toCharArray()) {
                if (c == firstChar) {
                    minString.append('1');
                } else {
                    minString.append(c);
                }
            }
        } else {
            // Find first digit that is not '0' and not '1'
            char changeChar = 0;
            for (char c : original.toCharArray()) {
                if (c != '0' && c != '1') {
                    changeChar = c;
                    break;
                }
            }

            for (char c : original.toCharArray()) {
                if (c == changeChar) {
                    minString.append('0');
                } else {
                    minString.append(c);
                }
            }
        }

        int minVal = Integer.parseInt(minString.toString());

        return maxVal - minVal;
    }
}
