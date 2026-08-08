import java.util.*;

class Solution {
    public boolean isIsomorphic(String s, String t) {

        HashMap<Character, Character> map = new HashMap<>();
        HashSet<Character> used = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {

            char a = s.charAt(i);
            char b = t.charAt(i);

            // a is already mapped
            if (map.containsKey(a)) {
                if (map.get(a) != b) {
                    return false;
                }
            }
            else {
                // b is already mapped to another character
                if (used.contains(b)) {
                    return false;
                }

                map.put(a, b);
                used.add(b);
            }
        }

        return true;
    }
}