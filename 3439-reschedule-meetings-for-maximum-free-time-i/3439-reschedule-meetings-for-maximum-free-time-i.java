import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        if (eventTime > endTime[endTime.length - 1]) {
            int[] newStartTime = new int[startTime.length + 1];
            int[] newEndTime = new int[endTime.length + 1];
            System.arraycopy(startTime, 0, newStartTime, 0, startTime.length);
            System.arraycopy(endTime, 0, newEndTime, 0, endTime.length);
            newStartTime[startTime.length] = eventTime;
            newEndTime[endTime.length] = eventTime;
            startTime = newStartTime;
            endTime = newEndTime;
        }
        
        int n = startTime.length;
        int max_sum = 0;
        int win_sum = 0;
        int pos = 0;
        int last_end = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        
        while (pos < n) {
            win_sum += (startTime[pos] - last_end);
            dq.addLast(startTime[pos] - last_end);
            max_sum = Math.max(max_sum, win_sum);
            if (dq.size() > k) {
                win_sum -= dq.removeFirst();
            }
            last_end = endTime[pos];
            pos++;
        }
        return max_sum;
    }
}