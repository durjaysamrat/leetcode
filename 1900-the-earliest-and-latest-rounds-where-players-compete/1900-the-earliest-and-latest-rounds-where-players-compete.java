import java.util.*;

class Solution {
    int mn = Integer.MAX_VALUE;
    int mx = Integer.MIN_VALUE;

    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        List<Integer> arr = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            arr.add(i);
        }
        dfs(arr, 1, firstPlayer, secondPlayer);
        return new int[]{mn, mx};
    }

    private void dfs(List<Integer> arr, int round, int firstPlayer, int secondPlayer) {
        int n = arr.size();
        int size = n / 2;

        for (int i = 0; i < size; i++) {
            if ((arr.get(i) == firstPlayer && arr.get(n - i - 1) == secondPlayer) ||
                (arr.get(i) == secondPlayer && arr.get(n - i - 1) == firstPlayer)) {
                mn = Math.min(mn, round);
                mx = Math.max(mx, round);
                return;
            }
        }

        boolean f1 = false, f2 = false;
        for (int num : arr) {
            if (num == firstPlayer) f1 = true;
            if (num == secondPlayer) f2 = true;
        }

        if (!f1 || !f2) return;

        int combinations = 1 << size;
        for (int i = 0; i < combinations; i++) {
            List<Integer> nextArr = new ArrayList<>(Collections.nCopies(size + (n % 2), 0));
            int left = 0, right = nextArr.size() - 1;

            for (int j = 0; j < size; j++) {
                if ((i & (1 << j)) != 0) {
                    nextArr.set(left++, arr.get(j));
                } else {
                    nextArr.set(right--, arr.get(n - j - 1));
                }
            }

            if (n % 2 == 1) {
                nextArr.set(left, arr.get(n / 2));
            }

            dfs(nextArr, round + 1, firstPlayer, secondPlayer);
        }
    }
}
