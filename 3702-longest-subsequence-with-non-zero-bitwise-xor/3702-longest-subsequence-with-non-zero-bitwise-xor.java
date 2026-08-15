class Solution {
    public int longestSubsequence(int[] nums) {

        int xor = 0;
        int n = nums.length;
        int zeroCount = 0;

        for (int num : nums) {
            xor ^= num;

            if (num == 0) {
                zeroCount++;
            }
        }

        // Case 1: Whole array has non-zero XOR
        if (xor != 0) {
            return n;
        }

        // Case 3: All elements are zero
        if (zeroCount == n) {
            return 0;
        }

        // Case 2: XOR is zero, but at least one non-zero exists
        return n - 1;
    }
}