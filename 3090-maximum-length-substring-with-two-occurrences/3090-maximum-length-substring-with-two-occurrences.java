class Solution {

    public int maximumLengthSubstring(String s) {

        int[] freq = new int[26];

        int left = 0;
        int ans = 0;

        for (int right = 0; right < s.length(); right++) {

            // Add current character
            char ch = s.charAt(right);
            freq[ch - 'a']++;

            // If character appears more than 2 times,
            // shrink window
            while (freq[ch - 'a'] > 2) {

                char leftChar = s.charAt(left);

                freq[leftChar - 'a']--;

                left++;
            }

            // Current window is valid
            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}