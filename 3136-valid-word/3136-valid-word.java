class Solution {
    public boolean isValid(String word) {
        if(word.length()<3)
        {
            return false;
        }
        int count=0;
        int v=0;
        int c=0;
        int dp=0;
        for(int i=0;i<word.length();i++){
            char a=word.charAt(i);
            if(a=='0'||a=='1'||a=='2'||a=='3'||a=='4'||a=='5'||a=='6'||a=='7'||a=='8'||a=='9')
            {
                count++;
            }
            else if(a=='a'||a=='e'||a=='i'||a=='o'||a=='u'||a=='A'||a=='E'||a=='I'||a=='O'||a=='U')
            {
                v++;
            }
            else if(a>='a'&&a<='z'||a>='A'&&a<='Z')
            {
                c++;
            }
            else
            {
                dp++;
            }
        }
        System.out.println(+count+" "+c+" "+v);
        if(count>=0&&v>=1&&c>=1&&dp==0)
        {
        return true;
        }
        return false;
    }
}