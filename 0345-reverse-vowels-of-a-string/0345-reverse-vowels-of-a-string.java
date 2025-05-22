class Solution {
    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) return s;

        // Convert string to char array for in-place swapping
        char[] chars = s.toCharArray();

        // Define two pointers
        int left = 0;
        int right = s.length() - 1;

        // Define vowels
        String vowels = "aeiouAEIOU";

        while (left < right) {
            // Move left pointer until a vowel is found
            while (left < right && vowels.indexOf(chars[left]) == -1) {
                left++;
            }
            // Move right pointer until a vowel is found
            while (left < right && vowels.indexOf(chars[right]) == -1) {
                right--;
            }
            // Swap the vowels
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;

            // Move pointers inward
            left++;
            right--;
        }

        // Return the modified string
        return new String(chars);
    }
}
