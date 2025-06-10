class Solution {
    public int maxDifference(String s) {
        int freq[]=new int[26];
        for(char ch:s.toCharArray())
        {
         freq[ch-'a']++;
        }
        int max=0;
        int min=9999;
        for(int count:freq)
        {
                        if (count == 0) continue;
          if (count % 2 == 1) {
                max = Math.max(max, count);
            } else {
                min = Math.min(min, count);
            }
        }
        return max-min;
    }
}