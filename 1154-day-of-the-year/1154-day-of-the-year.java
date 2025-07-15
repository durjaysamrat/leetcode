class Solution {
    public int dayOfYear(String date) {
        String dateval[] = date.split("-");
        int year=Integer.parseInt(dateval[0]);
        int month=Integer.parseInt(dateval[1]);
        int day = Integer.parseInt(dateval[2]);

        int[] dateofmonth={ 31,28,31,30,31,30,31,31,30,31,30,31};
        int days=day;
        for(int i=0;i<month-1;i++)
        {
            days=days+dateofmonth[i];
        }
        if(year%400==0&&month>2)
        {
            days=days+1;
        }
        else if(year%4==0&&year%100!=0&&month>2)
        {
            days=days+1;
        }
        return days;
    }
}