class Solution {
    public void sortColors(int[] nums) {
        int o1=0;
        int o2=0;
        int o0=0;
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]==0)
            {
                o0++;
            
            }
            else if(nums[i]==1)
            {
                o1++;
            }
            else {

                o2++;
            }
        }
        int j=0;
        while(o0>0)
        {
          nums[j++]=0;
          o0--;
        }
        while(o1>0)
        {
          nums[j++]=1;
          o1--;
        }
        while(o2>0)
        {
            nums[j++]=2;
            o2--;
        }
    }
}