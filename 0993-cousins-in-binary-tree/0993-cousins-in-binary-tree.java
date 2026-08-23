/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            int size = queue.size();

            boolean foundX = false;
            boolean foundY = false;

            for (int i = 0; i < size; i++) {

                TreeNode current = queue.poll();

                // Check left child
                if (current.left != null) {

                    // x and y are siblings
                    if ((current.left.val == x && current.right != null
                            && current.right.val == y) ||
                        (current.left.val == y && current.right != null
                            && current.right.val == x)) {
                        return false;
                    }

                    if (current.left.val == x) {
                        foundX = true;
                    }

                    if (current.left.val == y) {
                        foundY = true;
                    }

                    queue.offer(current.left);
                }

                // Check right child
                if (current.right != null) {

                    if (current.right.val == x) {
                        foundX = true;
                    }

                    if (current.right.val == y) {
                        foundY = true;
                    }

                    queue.offer(current.right);
                }
            }

            // Both found at the same level
            if (foundX && foundY) {
                return true;
            }

            // Only one found at this level
            if (foundX || foundY) {
                return false;
            }
        }

        return false;
    }
}