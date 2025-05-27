class Solution {
    public int[] productExceptSelf(int[] nums) {
        int k=nums.length;
        int[] ans=new int[k];
        int s=1,p=1;
        for(int i=0;i<k;i++)
        {
            ans[i]=p;
            p=p*nums[i];
        }
        for(int j=k-1;j>=0;j--)
        {
           ans[j] *= s;
            s *= nums[j];
        }
        return ans;
    }
}