class IndexTracker {
    int currentIndex = 0;
}

public class preOrderToBST {
    IndexTracker indexTracker = new IndexTracker();

    // Main function to construct the tree
    TreeNode constructTree(int[] preOrder, int size) {
        return constructTreeHelper(preOrder, indexTracker, preOrder[0], Integer.MIN_VALUE, Integer.MAX_VALUE, size);
    }

    // Helper function to construct the tree
    TreeNode constructTreeHelper(int[] preOrder, IndexTracker indexTracker, int key, int min, int max, int size) {
        // If we've used up all elements, return null
        if (indexTracker.currentIndex >= size) {
            return null;
        }

        TreeNode node = null;

        // If the current element can be used to create a new node (i.e., it is within
        // the current range)
        if (key > min && key < max) {
            // Create a new node
            node = new TreeNode(key);
            // Move to the next element in preOrder
            indexTracker.currentIndex = indexTracker.currentIndex + 1;

            // If there are still unused elements
            if (indexTracker.currentIndex < size) {
                // Construct the left and right subtrees
                node.left = constructTreeHelper(preOrder, indexTracker, preOrder[indexTracker.currentIndex], min, key,
                        size);
                node.right = constructTreeHelper(preOrder, indexTracker, preOrder[indexTracker.currentIndex], key, max,
                        size);
            }
        }

        return node;
    }

    // Given node, prints pre-order traversal of tree
    public static void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public static void main(String[] args) {
        // Pre-order traversal array
        int[] preOrder = new int[] { 41, 20, 11, 15, 32, 65, 50, 58, 93 };

        // Create an instance of PreOrderToBST
        preOrderToBST treeBuilder = new preOrderToBST();

        // Construct the tree and get the root
        TreeNode root = treeBuilder.constructTree(preOrder, preOrder.length);

        System.out.println("Constructed tree!");

        // Print pre-order traversal of the constructed tree
        System.out.println("Pre-order traversal of the constructed tree:");
        preOrder(root);
    }
}