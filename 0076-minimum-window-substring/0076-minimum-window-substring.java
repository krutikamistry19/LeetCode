class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";

        int[] count = new int[128];

        for (char c : t.toCharArray()) {
            count[c]++;
        }

        int left = 0;
        int start = 0;
        int minLen = Integer.MAX_VALUE;
        int required = t.length();

        for (int right = 0; right < s.length(); right++) {

            if (count[s.charAt(right)] > 0) {
                required--;
            }

            count[s.charAt(right)]--;

            while (required == 0) {

                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }

                count[s.charAt(left)]++;

                if (count[s.charAt(left)] > 0) {
                    required++;
                }

                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}