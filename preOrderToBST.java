public class preOrderToBST {
    // Index to keep track of the current position in pre-order array
    private int index = 0;

    public TreeNode bstFromPreOrder(int[] preOrder) {
        return bstFromPreOrderUtil(preOrder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode bstFromPreOrderUtil(int[] preOrder, int lower, int upper) {
        // Base case: if all elements are covered or the next element is out of current
        // subtree's range
        if (index == preOrder.length || preOrder[index] < lower || preOrder[index] > upper) {
            return null;
        }

        // The next value in preOrder array is the root of subtree
        int value = preOrder[index++];
        TreeNode root = new TreeNode(value);

        // All next keys that are smaller than value form the left subtree
        root.left = bstFromPreOrderUtil(preOrder, lower, value);

        // All next keys that are greater than value form the right subtree
        root.right = bstFromPreOrderUtil(preOrder, value, upper);

        return root;
    }

    // A utility function to print in-order traversal of the BST
    public void printInOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        printInOrder(node.left);
        System.out.print(node.value + " ");
        printInOrder(node.right);
    }

    public static void main(String[] args) {
        preOrderToBST converter = new preOrderToBST();
        int[] preOrder = { 8, 5, 1, 7, 10, 12 };
        TreeNode root = converter.bstFromPreOrder(preOrder);

        System.out.println("In-order traversal of constructed BST:");
        converter.printInOrder(root);
    }
}