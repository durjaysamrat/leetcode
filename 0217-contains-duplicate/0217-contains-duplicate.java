class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> m=new HashSet<Integer>();
        m.add(nums[0]);
        for(int i=1;i<nums.length;i++)
        {
         if(m.contains(nums[i]))
         {
            return true;
         }
         else
         {
            m.add(nums[i]);
         }
        }
        return false;
    }
}