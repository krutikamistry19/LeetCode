class Solution {
    public boolean winnerSquareGame(int n) {

        boolean[] dp = new boolean[n + 1];

        // dp[0] = false
        // If there are 0 stones, the current player loses.

        for (int i = 1; i <= n; i++) {

            // Try removing 1, 4, 9, 16, ...
            for (int j = 1; j * j <= i; j++) {

                int remaining = i - j * j;

                // If opponent loses from 'remaining',
                // then current player wins.
                if (!dp[remaining]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}