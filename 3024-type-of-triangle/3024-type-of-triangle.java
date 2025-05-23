class Solution {
    public String triangleType(int[] nums) {
        int a = nums[0];
        int b = nums[1];
        int c = nums[2];

        // Check for valid triangle
        if (a + b > c && b + c > a && c + a > b) {
            if (a == b && b == c) {
                return "equilateral";
            } else if (a == b || b == c || c == a) {
                return "isosceles";
            } else {
                return "scalene";
            }
        } else {
            return "none";
        }
    }
}