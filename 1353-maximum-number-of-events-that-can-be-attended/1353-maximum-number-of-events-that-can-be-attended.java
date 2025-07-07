class Solution {
    public int maxEvents(int[][] events) {
        int n = events.length;
        
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
        int day = events[0][0];
        int i = 0;
        int count = 0; 

        while(!pq.isEmpty() || i < n) {

            if(pq.isEmpty()) {
                day = events[i][0];
            }

            while(i < n && events[i][0] == day) {
                pq.add(events[i][1]);
                i++;
            }

            if(!pq.isEmpty()) {
                pq.poll(); // Attend an event on this day
                count++; // Counting the result
            }

            day++;

            // Skip events whose end day is less than the current day
            while(!pq.isEmpty() && pq.peek() < day) {
                pq.poll();
            }
        }

        return count;
    }
}