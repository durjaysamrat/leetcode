class Solution {
    public int minMaxDifference(int num) {
        String original = Integer.toString(num);
        
        // Find the first digit that is not '9'
        char firstNon9 = '9';
        for (char c : original.toCharArray()) {
            if (c != '9') {
                firstNon9 = c;
                break;
            }
        }

        // Create max string by replacing firstNon9 with '9'
        StringBuilder maxString = new StringBuilder();
        for (char c : original.toCharArray()) {
            if (c == firstNon9) {
                maxString.append('9');
            } else {
                maxString.append(c);
            }
        }
        int maxVal = Integer.parseInt(maxString.toString());

        // Create min string by replacing the first digit with '0'
        char firstChar = original.charAt(0);
        StringBuilder minString = new StringBuilder();
        for (char c : original.toCharArray()) {
            if (c == firstChar) {
                minString.append('0');
            } else {
                minString.append(c);
            }
        }
        int minVal = Integer.parseInt(minString.toString());

        return maxVal - minVal;
    }
}
