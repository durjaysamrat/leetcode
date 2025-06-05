class Solution {
    int[] parent = new int[26];

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        // Initialize parent[i] = i for 'a' to 'z'
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }

        // Union characters in s1 and s2
        for (int i = 0; i < s1.length(); i++) {
            union(s1.charAt(i) - 'a', s2.charAt(i) - 'a');
        }

        // Build the smallest equivalent string
        StringBuilder result = new StringBuilder();
        for (char c : baseStr.toCharArray()) {
            int smallestChar = find(c - 'a');
            result.append((char) (smallestChar + 'a'));
        }

        return result.toString();
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // path compression
        }
        return parent[x];
    }

    private void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        // Always attach the larger to the smaller for lex smallest parent
        if (rootX == rootY) return;
        if (rootX < rootY) {
            parent[rootY] = rootX;
        } else {
            parent[rootX] = rootY;
        }
    }
}