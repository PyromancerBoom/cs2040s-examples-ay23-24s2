public class preOrderToBST {
    // to keep track of the current position in pre-order array
    private int index = 0;

    public TreeNode constructTreeFromPreOrder(int[] preOrder) {
        return bstPreOrder(preOrder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // To some extent, this is Wishful thinking
    private TreeNode bstPreOrder(int[] preOrder, int lower, int upper) {
        // If we have reached the end of the array, return null
        // or we check if we cannot fit the value in the current subtree
        // that means we have reached the leaf node
        if (index == preOrder.length || preOrder[index] < lower || preOrder[index] > upper) {
            return null;
        }

        // The next value in preOrder array is the root of subtree
        int value = preOrder[index];
        index++;

        // Can uncomment these lines to see sort of get a feel of how it works
        // System.out.println("index: " + index);
        // System.out.println("value: " + value);
        // System.out.println("lower: " + lower + " upper: " + upper);

        TreeNode root = new TreeNode(value);

        // All next keys that are smaller than root form the left subtree
        root.left = bstPreOrder(preOrder, lower, value);

        // All next keys that are greater than root form the right subtree
        root.right = bstPreOrder(preOrder, value, upper);

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
        TreeNode root = converter.constructTreeFromPreOrder(preOrder);

        System.out.println("In-order traversal of constructed BST:");
        converter.printInOrder(root);
    }
}