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

    public void flatten(TreeNode root) {

        if (root == null) {
            return;
        }

        // 1. Flatten left subtree
        flatten(root.left);

        // 2. Flatten right subtree
        flatten(root.right);

        // Save the right subtree
        TreeNode rightSubtree = root.right;

        // Move left subtree to right
        root.right = root.left;

        // Left must become null
        root.left = null;

        // Find the last node of the new right subtree
        TreeNode current = root;

        while (current.right != null) {
            current = current.right;
        }

        // Attach original right subtree
        current.right = rightSubtree;
    }
}