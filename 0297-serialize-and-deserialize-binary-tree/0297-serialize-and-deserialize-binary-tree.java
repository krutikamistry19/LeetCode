/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 public class Codec {

    // Serialize
    public String serialize(TreeNode root) {

        StringBuilder sb = new StringBuilder();

        serializeTree(root, sb);

        return sb.toString();
    }

    private void serializeTree(TreeNode root, StringBuilder sb) {

        if (root == null) {
            sb.append("#,");
            return;
        }

        // Root
        sb.append(root.val).append(",");

        // Left
        serializeTree(root.left, sb);

        // Right
        serializeTree(root.right, sb);
    }


    // Deserialize
    public TreeNode deserialize(String data) {

        String[] values = data.split(",");

        Queue<String> queue = new LinkedList<>();

        for (String value : values) {
            queue.offer(value);
        }

        return buildTree(queue);
    }

    private TreeNode buildTree(Queue<String> queue) {

        String value = queue.poll();

        // null node
        if (value.equals("#")) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(value));

        // Build left subtree
        root.left = buildTree(queue);

        // Build right subtree
        root.right = buildTree(queue);

        return root;
    }
}