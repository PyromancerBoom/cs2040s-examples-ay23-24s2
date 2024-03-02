import java.util.Arrays;

/*
 * This is a rather adhoc implementation.
 * Could be improved(and shortened) by using Hashmaps!
 * 
 * Complexity: O(n + m), where n is the number of nodes and m is the number of queries.
 */
public class Tree {
    TreeNode root;
    int maxHeight = 0;
    Integer[] nodeHeights = new Integer[20]; // Height of each node
    Integer[] nodeDepths = new Integer[20]; // Depth of each node

    Integer[] maxHeights = new Integer[20]; // Maximum height at each depth
    Integer[] secondMaxHeights = new Integer[20]; // Second maximum height at each depth

    // Depth-First Search to calculate depth of each node
    int dfs_depth(TreeNode node, int depth) {
        if (node == null) {
            return depth - 1;
        }

        int leftDepth = dfs_depth(node.left, depth + 1);
        int rightDepth = dfs_depth(node.right, depth + 1);
        nodeDepths[node.value] = depth;

        // Update maxHeights and secondMaxHeights
        int currentHeight = nodeHeights[node.value];

        // Since all nodes are unique, we can use the value of the node as the index
        // If the current height is greater than the maximum height at this depth,
        // update the maximum and second maximum heights
        if (maxHeights[depth] == null || currentHeight > maxHeights[depth]) {
            secondMaxHeights[depth] = maxHeights[depth];
            maxHeights[depth] = currentHeight;

            // If the current height is not greater than the maximum height but is greater
            // than the second maximum height, update the second maximum height
        } else if (secondMaxHeights[depth] == null || currentHeight > secondMaxHeights[depth]) {
            secondMaxHeights[depth] = currentHeight;
        }

        return Math.max(leftDepth, rightDepth);
    }

    // tree traversal (post-order) and calculate height of each node
    int setNodeHeights(TreeNode node) {
        if (node == null) {
            return -1;
        }

        int leftHeight = setNodeHeights(node.left);
        int rightHeight = setNodeHeights(node.right);
        int currentHeight = Math.max(leftHeight, rightHeight) + 1;

        this.nodeHeights[node.value] = currentHeight;

        return currentHeight;
    }

    // Calculate maximum height after removing a subtree rooted at a given node
    int removeSubtree(int nodeValue) {
        // Get the depth and height of the node to be removed.
        int depth = nodeDepths[nodeValue];
        int height = nodeHeights[nodeValue];

        // Edge case: Only one node at the last level
        if (secondMaxHeights[depth] == null && maxHeights[depth] != null) {
            return depth - 1;
        }

        // If the height of the node to be removed is the maximum height at its depth,
        // use the second maximum height. Otherwise, use the maximum height
        height = height == maxHeights[depth] ? secondMaxHeights[depth] : maxHeights[depth];

        // The maximum height of the tree after removing the subtree is the sum of the
        // depth and height
        return depth + height;
    }

    /* ------------------------ Some extra helper functions -------------------- */

    // Calculate height of each node
    int calculateNodeHeights() {
        return setNodeHeights(root);
    }

    // Calculate depth of each node
    void calculateNodeDepths() {
        dfs_depth(root, 0);
    }

    public static void main(String[] args) {
        Tree tree = new Tree();
        // Level - 1
        tree.root = new TreeNode(1);

        // Level - 2
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);

        // Level - 3
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(5);
        tree.root.right.left = new TreeNode(6);
        tree.root.right.right = new TreeNode(7);

        // Level - 4
        tree.root.left.left.left = new TreeNode(8);
        tree.root.right.right.left = new TreeNode(9);
        tree.root.right.right.right = new TreeNode(10);

        // Level - 5
        tree.root.left.left.left.left = new TreeNode(11);
        tree.root.left.left.left.right = new TreeNode(12);

        // Level - 6 -> This is one of the edge case
        tree.root.left.left.left.right.left = new TreeNode(13);
        // tree.root.left.left.left.right.right = new TreeNode(14);

        tree.calculateNodeHeights();
        tree.calculateNodeDepths();

        System.out.println();
        System.out.println("Tree height: " + Arrays.toString(tree.nodeHeights));
        System.out.println();
        System.out.println("Tree depth: " + Arrays.toString(tree.nodeDepths));
        System.out.println();
        System.out.println("Max heights: " + Arrays.toString(tree.maxHeights));
        System.out.println();
        System.out.println("Second max heights: " + Arrays.toString(tree.secondMaxHeights));

        // Current height
        System.out.println();
        System.out.println("Height of root: " + tree.nodeHeights[1]);

        // remove a sub tree
        System.out.println();
        System.out.println("Height after removing sub tree: " + tree.removeSubtree(13));
    }
}