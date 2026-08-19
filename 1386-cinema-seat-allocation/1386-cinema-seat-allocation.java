import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        // Store reserved seats for each row
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            map.computeIfAbsent(row, k -> new HashSet<>()).add(col);
        }

        int answer = 0;

        // Rows with reserved seats
        for (int row : map.keySet()) {

            Set<Integer> reserved = map.get(row);

            boolean left = true;   // seats 2-5
            boolean middle = true; // seats 4-7
            boolean right = true;  // seats 6-9

            // Check seats 2-5
            for (int seat = 2; seat <= 5; seat++) {
                if (reserved.contains(seat)) {
                    left = false;
                    break;
                }
            }

            // Check seats 4-7
            for (int seat = 4; seat <= 7; seat++) {
                if (reserved.contains(seat)) {
                    middle = false;
                    break;
                }
            }

            // Check seats 6-9
            for (int seat = 6; seat <= 9; seat++) {
                if (reserved.contains(seat)) {
                    right = false;
                    break;
                }
            }

            if (left && right) {
                // Can place 2 families
                answer += 2;
            } else if (left || middle || right) {
                // Can place 1 family
                answer += 1;
            }
        }

        // Rows without any reserved seats
        answer += (n - map.size()) * 2;

        return answer;
    }
}