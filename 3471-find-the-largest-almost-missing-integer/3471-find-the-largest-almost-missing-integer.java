import java.util.HashMap;
import java.util.Map;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        
        // Count total frequencies of each number in nums
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Case 1: k == 1
        // Every element is its own subarray. 
        // Find the maximum element whose total count in nums is 1.
        if (k == 1) {
            int maxVal = -1;
            for (int num : nums) {
                if (freqMap.get(num) == 1) {
                    maxVal = Math.max(maxVal, num);
                }
            }
            return maxVal;
        }

        // Case 2: k == n
        // The entire array is the only subarray.
        // Every unique element appears in exactly 1 subarray.
        if (k == n) {
            int maxVal = -1;
            for (int num : nums) {
                maxVal = Math.max(maxVal, num);
            }
            return maxVal;
        }

        // Case 3: 1 < k < n
        // Only nums[0] and nums[n-1] can potentially belong to exactly 1 subarray of size k.
        int ans = -1;

        // Check nums[0]: valid if it occurs nowhere else in the array
        if (freqMap.get(nums[0]) == 1) {
            ans = Math.max(ans, nums[0]);
        }

        // Check nums[n - 1]: valid if it occurs nowhere else in the array
        if (freqMap.get(nums[n - 1]) == 1) {
            ans = Math.max(ans, nums[n - 1]);
        }

        return ans;
    }
}