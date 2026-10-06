class Solution {
    public boolean mergeTriplets(int[][] triplets, int[] target) {

        // found[0] -> can we get target[0]?
        // found[1] -> can we get target[1]?
        // found[2] -> can we get target[2]?
        boolean[] found = new boolean[3];

        for (int[] triplet : triplets) {

            // If any value is greater than target,
            // this triplet cannot be used.
            if (triplet[0] <= target[0] &&
                triplet[1] <= target[1] &&
                triplet[2] <= target[2]) {

                // Check first position
                if (triplet[0] == target[0]) {
                    found[0] = true;
                }

                // Check second position
                if (triplet[1] == target[1]) {
                    found[1] = true;
                }

                // Check third position
                if (triplet[2] == target[2]) {
                    found[2] = true;
                }
            }
        }

        // We must be able to get all 3 values
        return found[0] && found[1] && found[2];
    }
}