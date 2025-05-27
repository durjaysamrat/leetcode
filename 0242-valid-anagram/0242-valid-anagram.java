class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length()==t.length())
        {
         int[] chd=new int[26];
         for(int i=0;i<s.length();i++)
         {
            chd[s.charAt(i)-'a']++;
            chd[t.charAt(i)-'a']--;
         }
        for(int count:chd)
        {
            if(count!=0)
            {
                return false;
            }
        }
        return true;
        }
        else
        {
            return false;
        }
    }
}