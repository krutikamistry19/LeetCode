import java.util.*;

class Solution {
    public int missingInteger(int[] nums) {

        // Store all numbers in a HashSet
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        // Find sequential prefix sum
        int sum = nums[0];

        for (int i = 1; i < nums.length; i++) {

            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else {
                break;
            }
        }

        // Find the smallest number > sum
        // which is not present in nums
        while (set.contains(sum)) {
            sum++;
        }

        return sum;
    }
}