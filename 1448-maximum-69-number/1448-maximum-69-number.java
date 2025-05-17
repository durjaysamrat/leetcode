class Solution {
    public int maximum69Number(int num) {
        // Convert the number to a character array for easy modification
        char[] digits = String.valueOf(num).toCharArray();

        // Change the first '6' to '9' and break (since only one change is allowed)
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] == '6') {
                digits[i] = '9';
                break;
            }
        }

        // Convert the character array back to integer
        return Integer.parseInt(new String(digits));
    }
}
