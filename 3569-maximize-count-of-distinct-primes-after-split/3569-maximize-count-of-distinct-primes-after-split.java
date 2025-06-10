import java.util.*;

public class Solution {
    int max_val = 100000;
    boolean[] isPrime;
    
    public void precomputePrimes() {
        isPrime = new boolean[max_val + 1];
        if (max_val < 2) {
            return;
        }
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i * i <= max_val; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= max_val; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }
    
    static class FenwTree {
        int n;
        int[] tree;
        
        public FenwTree(int size) {
            this.n = size;
            tree = new int[n + 1];
        }
        
        public void update(int index, int delta) {
            index++;
            while (index <= n) {
                tree[index] += delta;
                index += index & -index;
            }
        }
        
        public int query(int index) {
            index++;
            int sum = 0;
            while (index > 0) {
                sum += tree[index];
                index -= index & -index;
            }
            return sum;
        }
    }
    
    static class SegmentTree {
        int size;
        int[] max;
        int[] lazy;
        
        public SegmentTree(int n) {
            this.size = n;
            max = new int[4 * n];
            lazy = new int[4 * n];
        }
        
        private void push(int node) {
            if (lazy[node] != 0) {
                lazy[2 * node + 1] += lazy[node];
                lazy[2 * node + 2] += lazy[node];
                max[2 * node + 1] += lazy[node];
                max[2 * node + 2] += lazy[node];
                lazy[node] = 0;
            }
        }
        
        private void update(int node, int segL, int segR, int l, int r, int delta) {
            if (l > segR || r < segL) {
                return;
            }
            if (l <= segL && segR <= r) {
                max[node] += delta;
                lazy[node] += delta;
                return;
            }
            push(node);
            int mid = (segL + segR) / 2;
            update(2 * node + 1, segL, mid, l, r, delta);
            update(2 * node + 2, mid + 1, segR, l, r, delta);
            max[node] = Math.max(max[2 * node + 1], max[2 * node + 2]);
        }
        
        public void updateRange(int l, int r, int delta) {
            if (l > r) {
                return;
            }
            update(0, 0, size - 1, l, r, delta);
        }
        
        public int getMax() {
            return max[0];
        }
    }
    
    public int[] maximumCount(int[] nums, int[][] queries) {
        int n = nums.length;
        if (n == 1) {
            return new int[queries.length];
        }
        
        precomputePrimes();
        
        FenwTree fenw = new FenwTree(n);
        SegmentTree segTree = new SegmentTree(n - 1);
        Map<Integer, TreeSet<Integer>> primeTreeSet = new HashMap<>();
        int[] currentNums = Arrays.copyOf(nums, n);
        
        for (int i = 0; i < n; i++) {
            int x = currentNums[i];
            if (x <= max_val && isPrime[x]) {
                TreeSet<Integer> set = primeTreeSet.computeIfAbsent(x, k -> new TreeSet<>());
                set.add(i);
            }
        }
        
        for (Map.Entry<Integer, TreeSet<Integer>> entry : primeTreeSet.entrySet()) {
            TreeSet<Integer> set = entry.getValue();
            int first = set.first();
            int last = set.last();
            fenw.update(first, 1);
            if (first < last) {
                segTree.updateRange(first, last - 1, 1);
            }
        }
        
        int q = queries.length;
        int[] res = new int[q];
        
        for (int i = 0; i < q; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int oldVal = currentNums[idx];
            
            if (oldVal <= max_val && isPrime[oldVal]) {
                TreeSet<Integer> set = primeTreeSet.get(oldVal);
                if (set != null) {
                    int oldFirst = set.first();
                    int oldLast = set.last();
                    set.remove(idx);
                    if (set.isEmpty()) {
                        fenw.update(oldFirst, -1);
                        if (oldFirst < oldLast) {
                            segTree.updateRange(oldFirst, oldLast - 1, -1);
                        }
                        primeTreeSet.remove(oldVal);
                    } else {
                        int newFirst = set.first();
                        int newLast = set.last();
                        if (oldFirst != newFirst) {
                            fenw.update(oldFirst, -1);
                            fenw.update(newFirst, 1);
                        }
                        if (oldFirst < oldLast) {
                            segTree.updateRange(oldFirst, oldLast - 1, -1);
                        }
                        if (newFirst < newLast) {
                            segTree.updateRange(newFirst, newLast - 1, 1);
                        }
                    }
                }
            }
            
            currentNums[idx] = val;
            if (val <= max_val && isPrime[val]) {
                TreeSet<Integer> set = primeTreeSet.computeIfAbsent(val, k -> new TreeSet<>());
                boolean wasEmpty = set.isEmpty();
                set.add(idx);
                int newFirst = set.first();
                int newLast = set.last();
                if (wasEmpty) {
                    fenw.update(newFirst, 1);
                    if (newFirst < newLast) {
                        segTree.updateRange(newFirst, newLast - 1, 1);
                    }
                } else {
                    int oldFirst = set.first();
                    int oldLast = set.last();
                    set.remove(idx);
                    int tempFirst = set.first();
                    int tempLast = set.last();
                    set.add(idx);
                    if (idx < tempFirst) {
                        fenw.update(tempFirst, -1);
                        fenw.update(idx, 1);
                        if (tempFirst < tempLast) {
                            segTree.updateRange(tempFirst, tempLast - 1, -1);
                        }
                        if (idx < newLast) {
                            segTree.updateRange(idx, newLast - 1, 1);
                        }
                    } else if (idx > tempLast) {
                        if (tempFirst < tempLast) {
                            segTree.updateRange(tempFirst, tempLast - 1, -1);
                        }
                        if (newFirst < idx) {
                            segTree.updateRange(newFirst, idx - 1, 1);
                        }
                    }
                }
            }
            
            int D = fenw.query(n - 1);
            int maxC = segTree.getMax();
            res[i] = D + maxC;
        }
        
        return res;
    }
}