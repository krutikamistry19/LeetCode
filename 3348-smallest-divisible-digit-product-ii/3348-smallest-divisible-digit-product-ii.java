class Solution {

    // factors[d] = prime factor counts of digit d
    // index: 2, 3, 5, 7
    private static final int[][] FACTORS = {
        {0, 0, 0, 0}, // 0
        {0, 0, 0, 0}, // 1
        {1, 0, 0, 0}, // 2
        {0, 1, 0, 0}, // 3
        {2, 0, 0, 0}, // 4
        {0, 0, 1, 0}, // 5
        {1, 1, 0, 0}, // 6
        {0, 0, 0, 1}, // 7
        {3, 0, 0, 0}, // 8
        {0, 2, 0, 0}  // 9
    };

    public String smallestNumber(String num, long t) {

        // ---------------------------------------------------------
        // Step 1: Factorize t into 2, 3, 5, 7
        // ---------------------------------------------------------
        int[] need = new int[4];
        int[] primes = {2, 3, 5, 7};

        for (int i = 0; i < 4; i++) {
            while (t % primes[i] == 0) {
                need[i]++;
                t /= primes[i];
            }
        }

        // If anything is left, t has a prime > 7.
        // No digit 1..9 can provide that prime.
        if (t != 1) {
            return "-1";
        }

        // ---------------------------------------------------------
        // Step 2: Find the minimum number of digits needed
        // to provide all factors of t.
        // ---------------------------------------------------------
        String smallest = buildNumber(need);

        // If we need more digits than num has,
        // answer must have more digits than num.
        if (smallest.length() > num.length()) {
            return smallest;
        }

        // ---------------------------------------------------------
        // Step 3: Count prime factors in num
        // ---------------------------------------------------------
        int[] total = new int[4];

        for (int i = 0; i < num.length(); i++) {
            int d = num.charAt(i) - '0';

            // Zero is not allowed in the answer.
            // We don't add any factors for zero.
            if (d != 0) {
                addFactors(total, FACTORS[d]);
            }
        }

        // Find first zero.
        int firstZero = num.indexOf('0');

        // If num contains no zero and already satisfies t,
        // num itself is the answer.
        if (firstZero == -1 && containsAll(total, need)) {
            return num;
        }

        // ---------------------------------------------------------
        // Step 4:
        // Try changing one digit from right to left.
        //
        // We make the changed digit slightly bigger,
        // then fill the remaining positions as small as possible.
        // ---------------------------------------------------------

        int[] suffixFactors = total.clone();

        for (int i = num.length() - 1; i >= 0; i--) {

            int currentDigit = num.charAt(i) - '0';

            // Remove current digit from suffix/prefix count.
            if (currentDigit != 0) {
                removeFactors(suffixFactors, FACTORS[currentDigit]);
            }

            // If there is a zero before this position,
            // changing this position cannot remove that zero.
            if (firstZero != -1 && i > firstZero) {
                continue;
            }

            // Try every larger digit.
            for (int bigger = currentDigit + 1; bigger <= 9; bigger++) {

                int[] remaining = need.clone();

                // Factors already supplied by digits before i.
                for (int j = 0; j < 4; j++) {
                    remaining[j] -= suffixFactors[j];
                    remaining[j] -= FACTORS[bigger][j];

                    // We don't need extra factors once requirement is met.
                    remaining[j] = Math.max(0, remaining[j]);
                }

                // Build the smallest suffix that supplies remaining factors.
                String suffix = buildNumber(remaining);

                int positionsLeft = num.length() - i - 1;

                // We can fill unused positions with '1'.
                if (suffix.length() <= positionsLeft) {

                    StringBuilder ans = new StringBuilder();

                    // Keep prefix unchanged.
                    ans.append(num, 0, i);

                    // Put the bigger digit.
                    ans.append(bigger);

                    // Remaining unused positions become 1.
                    for (int k = suffix.length(); k < positionsLeft; k++) {
                        ans.append('1');
                    }

                    // Add required digits.
                    ans.append(suffix);

                    return ans.toString();
                }
            }
        }

        // ---------------------------------------------------------
        // Step 5:
        // No answer with the same length.
        // So use one extra digit.
        // ---------------------------------------------------------
        String suffix = buildNumber(need);

        StringBuilder ans = new StringBuilder();

        // Extra leading 1s.
        for (int i = suffix.length(); i < num.length() + 1; i++) {
            ans.append('1');
        }

        ans.append(suffix);

        return ans.toString();
    }

    // Add factor counts.
    private void addFactors(int[] a, int[] b) {
        for (int i = 0; i < 4; i++) {
            a[i] += b[i];
        }
    }

    // Remove factor counts.
    private void removeFactors(int[] a, int[] b) {
        for (int i = 0; i < 4; i++) {
            a[i] -= b[i];
        }
    }

    // Check whether a contains all factors required by b.
    private boolean containsAll(int[] a, int[] b) {
        for (int i = 0; i < 4; i++) {
            if (a[i] < b[i]) {
                return false;
            }
        }
        return true;
    }

    /*
     * Build the smallest string of digits whose product
     * contains all required prime factors.
     *
     * Example:
     * 2^3 -> 8
     * 3^2 -> 9
     * 2 * 3 -> 6
     * 2^2 -> 4
     */
    private String buildNumber(int[] count) {

        int c2 = count[0];
        int c3 = count[1];
        int c5 = count[2];
        int c7 = count[3];

        StringBuilder ans = new StringBuilder();

        // 5 and 7 cannot be combined with anything.
        for (int i = 0; i < c5; i++) {
            ans.append('5');
        }

        for (int i = 0; i < c7; i++) {
            ans.append('7');
        }

        // Make 8 = 2 * 2 * 2
        while (c2 >= 3) {
            ans.append('8');
            c2 -= 3;
        }

        // Make 9 = 3 * 3
        while (c3 >= 2) {
            ans.append('9');
            c3 -= 2;
        }

        // Make 6 = 2 * 3
        while (c2 >= 1 && c3 >= 1) {
            ans.append('6');
            c2--;
            c3--;
        }

        // Make 4 = 2 * 2
        while (c2 >= 2) {
            ans.append('4');
            c2 -= 2;
        }

        // Remaining 2
        while (c2 > 0) {
            ans.append('2');
            c2--;
        }

        // Remaining 3
        while (c3 > 0) {
            ans.append('3');
            c3--;
        }

        // We need the smallest possible number,
        // so sort the digits in ascending order.
        char[] chars = ans.toString().toCharArray();
        java.util.Arrays.sort(chars);

        return new String(chars);
    }
}