class Solution {
    public boolean isValidSudoku(char[][] board) {
        int n=9;
        HashSet <Character>[] row=new HashSet[n];
        HashSet <Character>[] col=new HashSet[n];
        HashSet <Character>[] box=new HashSet[n];
        for(int r=0;r<n;r++)
        {
            row[r]=new HashSet<Character>();
            col[r]=new HashSet<Character>();
            box[r]=new HashSet<Character>();
        }
        for(int r=0;r<n;r++)
        {
            for(int c=0;c<n;c++)
            {
              char val=board[r][c];
              if(val=='.'){
                continue;
              }
              if(row[r].contains(val)){
                return false;
              }
              row[r].add(val);
              if(col[c].contains(val)){
                return false;
              }
              col[c].add(val);
              int idx=(r/3)*3+c/3;
              if(box[idx].contains(val)){
                return false;
              }
              box[idx].add(val);
            }
        }
        return true;
    }
}