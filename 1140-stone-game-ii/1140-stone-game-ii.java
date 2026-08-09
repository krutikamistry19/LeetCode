class Solution {

    int[] suffix;
    int[][] dp;

    public int stoneGameII(int[] piles) {

        int n = piles.length;

        // suffix[i] = total stones from i to end
        suffix = new int[n];

        suffix[n - 1] = piles[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = piles[i] + suffix[i + 1];
        }

        dp = new int[n][n + 1];

        return solve(piles, 0, 1);
    }

    private int solve(int[] piles, int i, int M) {

        if (i >= piles.length) {
            return 0;
        }

        // If we can take all remaining stones
        if (i + 2 * M >= piles.length) {
            return suffix[i];
        }

        if (dp[i][M] != 0) {
            return dp[i][M];
        }

        int best = 0;

        // Try taking X stones
        // X can be from 1 to 2*M
        for (int X = 1; X <= 2 * M; X++) {

            // Stones we leave for the opponent
            int opponent = solve(
                piles,
                i + X,
                Math.max(M, X)
            );

            // Current player's stones
            int current = suffix[i] - opponent;

            best = Math.max(best, current);
        }

        dp[i][M] = best;

        return best;
    }
}
