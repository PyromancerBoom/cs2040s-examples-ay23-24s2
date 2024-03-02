import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Search {

    // Recursive DFS
    public void dfsRecursive(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.value);

        // call DFS on the left subtree
        dfsRecursive(node.left);

        // call DFS on the right subtree
        dfsRecursive(node.right);
    }

    // Iterative DFS
    public void dfsIterative(TreeNode root) {
        // Base case: if the tree is empty, return
        if (root == null) {
            return;
        }

        // we use this stack for our iterative DFS
        Stack<TreeNode> stack = new Stack<>();

        // push the root node onto the stack
        stack.push(root);

        while (!stack.isEmpty()) {
            // Pop the top node from the stack
            TreeNode currentNode = stack.pop();

            // print the current node's value
            System.out.println(currentNode.value);

            // push the node's non-null children onto the stack
            if (currentNode.right != null) {
                stack.push(currentNode.right);
            }
            if (currentNode.left != null) {
                stack.push(currentNode.left);
            }
        }
    }

    // Iterative BFS - Level Order Traversal
    public void bfsIterative(TreeNode root) {
        // Base case: if the tree is empty, return
        if (root == null) {
            return;
        }

        // Similar to iterative DFS, we use a queue instead to keep track of the nodes
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            // Get the size of the current level
            int levelSize = queue.size();

            // process all nodes of the current level and enqueue their non-null children
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();

                // print the current node's value
                System.out.println(currentNode.value);

                // enqueue the node's non-null children
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
        }
    }

    // Recursive BFS - Level Order Traversal
    public void bfsRecursive(List<TreeNode> levelNodes, int level) {
        if (levelNodes.isEmpty()) {
            return;
        }

        List<TreeNode> nextLevelNodes = new ArrayList<>();

        // Uncomment this line to print the level number
        // System.out.println("Level " + level);

        // Process all nodes of the current level and add their non-null children to the
        // next level
        for (TreeNode node : levelNodes) {
            // Print the current node's value
            System.out.println(node.value);

            // Add the node's non-null children to the next level
            if (node.left != null) {
                nextLevelNodes.add(node.left);
            }
            if (node.right != null) {
                nextLevelNodes.add(node.right);
            }
        }

        // Recursively call bfs on the next level nodes
        bfsRecursive(nextLevelNodes, level + 1);
    }

    public static void main(String[] args) {
        // Pre-order traversal array
        int[] preOrder = new int[] { 41, 20, 11, 15, 32, 65, 50, 58, 93 };

        preOrderToBST treeBuilder = new preOrderToBST();
        TreeNode root = treeBuilder.constructTreeFromPreOrder(preOrder);

        // Let's do the BFS and DFS traversals
        Search bfsIterative = new Search();
        Search bfsRecursive = new Search();
        Search dfsRecursive = new Search();
        Search dfsIterative = new Search();

        // Iterative BFS
        System.out.println("Iterative BFS:");
        TreeNode node = root;
        bfsIterative.bfsIterative(node);

        // Recursive BFS
        System.out.println("Recursive BFS:");
        List<TreeNode> levelNodes = new ArrayList<>();
        levelNodes.add(root);
        bfsRecursive.bfsRecursive(levelNodes, 0);
        System.out.println();

        // Recursive DFS
        System.out.println("Recursive DFS:");
        dfsRecursive.dfsRecursive(root);
        System.out.println();

        // Iterative DFS
        System.out.println("Iterative DFS:");
        dfsIterative.dfsIterative(root);
        System.out.println();
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
}