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

    public int kthSmallest(TreeNode root, int k) {

        Stack<TreeNode> stack = new Stack<>();

        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {

            // Go to the leftmost node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // Get the smallest remaining node
            current = stack.pop();

            k--;

            // If k becomes 0, this is the kth smallest
            if (k == 0) {
                return current.val;
            }

            // Move to right subtree
            current = current.right;
        }

        return -1;
    }
}