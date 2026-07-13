class Solution {
    public int countStudents(int[] students, int[] sandwiches) {
        int count0 = 0;
        int count1 = 0;

        for (int student : students) {
            if (student == 0) 
               count0++;
            else 
            count1++;
        }

        for (int i = 0; i < sandwiches.length; i++) {
            if (sandwiches[i] == 0) {
                if (count0 == 0) {
                   return sandwiches.length - i;
                }
                count0--;
            } else {
                if (count1 == 0)
                   return sandwiches.length - i;
                   count1--;
            }
        }
        return 0;
    }
}