class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] count = new int[3];

        // Count stones based on remainder modulo 3
        for (int stone : stones) {
            count[stone % 3]++;
        }

        // If there are no remainder-1 or remainder-2 stones,
        // Alice cannot avoid making the sum divisible by 3.
        if (count[1] == 0 && count[2] == 0) {
            return false;
        }

        // If count[0] is even, Alice wins if there is at least
        // one stone of remainder 1 and one of remainder 2.
        if (count[0] % 2 == 0) {
            return count[1] > 0 && count[2] > 0;
        }

        // If count[0] is odd, Alice can win if one side has
        // at least 2 more stones than the other.
        return Math.abs(count[1] - count[2]) > 2;
    }
}