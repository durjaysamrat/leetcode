class Solution {
    public String triangleType(int[] nums) {
        int a=nums[0];
        int b=nums[1];
        int c=nums[2];
        String str1="isosceles";
        String str="equilateral";
        String str2="scalene";
        if(a==b&&b==c)
        {
          return str;
        }
        if(a==b||b==c||c==a)
        {
          return str1;
        }
        else
        {
            return str2;
        }
    }
}