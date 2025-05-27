class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> s=new HashMap<>();
        for(int i=0;i<nums.length;i++)
        {
            if(!s.containsKey(nums[i]))
            {
                s.put(nums[i],1);
            }
            else
            {
                int q=s.get(nums[i]);
                s.put(nums[i],++q);
            }
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(s.entrySet());
        list.sort(Map.Entry.comparingByValue());

        List<Integer> sortedKeys = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : list) {
            sortedKeys.add(entry.getKey());
        }
        int[] a=new int[k];
        int j=sortedKeys.size()-1;
        for(int i=0;i<k;i++)
        {
         a[i]=sortedKeys.get(j--);
        }
        return a;
    }
}