import java.util.*;

class Solution {
    public String[] divideString(String s, int k, char fill) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < s.length(); i += k) {
            String group = s.substring(i, Math.min(i + k, s.length()));

            if (group.length() < k) {
                StringBuilder sb = new StringBuilder(group);
                while (sb.length() < k) {
                    sb.append(fill);
                }
                group = sb.toString();
            }

            result.add(group);
        }

        return result.toArray(new String[0]);
    }
}
