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

    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }

    private int height(TreeNode node) {

        // Empty tree has height 0
        if (node == null) {
            return 0;
        }

        // Height of left subtree
        int leftHeight = height(node.left);

        // If left subtree is unbalanced
        if (leftHeight == -1) {
            return -1;
        }

        // Height of right subtree
        int rightHeight = height(node.right);

        // If right subtree is unbalanced
        if (rightHeight == -1) {
            return -1;
        }

        // If current node is unbalanced
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        // Return height of current subtree
        return Math.max(leftHeight, rightHeight) + 1;
    }
}