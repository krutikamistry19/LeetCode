import java.util.*;

class Solution {
    public int[] validSequence(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        // last[j] = last position in word1
        // where word2[j] can be matched
        int[] last = new int[m];
        Arrays.fill(last, -1);

        int i = n - 1;
        int j = m - 1;

        // Find last possible positions from right to left
        while (i >= 0 && j >= 0) {
            if (word1.charAt(i) == word2.charAt(j)) {
                last[j] = i;
                j--;
            }
            i--;
        }

        int[] ans = new int[m];

        j = 0;
        boolean changed = false;

        for (i = 0; i < n && j < m; i++) {

            // Characters are already equal
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
            }

            // Use our one allowed change
            else if (!changed) {

                // If this is the last character,
                // we can directly change it.
                if (j == m - 1 || i < last[j + 1]) {
                    ans[j] = i;
                    j++;
                    changed = true;
                }
            }
        }

        // Could not form word2
        if (j != m) {
            return new int[0];
        }

        return ans;
    }
}
