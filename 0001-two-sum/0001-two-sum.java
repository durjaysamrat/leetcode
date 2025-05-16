class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] nums2={0,0};
        for(int i=0;i<nums.length;i++)
        {
            int p=target-nums[i];
             for(int j=i+1;j<nums.length;j++)
             {
                if(nums[j]==p)
                {
                    nums2[0]=i;
                    nums2[1]=j;
                }
             }
        }
        return nums2;
    }
}