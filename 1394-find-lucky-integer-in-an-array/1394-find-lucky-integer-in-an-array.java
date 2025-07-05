class Solution {
    public int findLucky(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int result = -1;
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (entry.getKey() == entry.getValue()) {
                result = Math.max(result, entry.getKey());
            }
        }

        return result;
    }
}
