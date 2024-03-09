import java.util.HashMap;

class Node {
    // Each node has a map of children nodes
    // and a boolean to indicate if a word
    // children nodes contain the next character of the word
    HashMap<Character, Node> children;
    boolean isWordComplete; // indicates if the current node is the end of a word
    int countOfPrefixStr; // count of prefix strings

    public Node() {
        children = new HashMap<>();
        isWordComplete = false;
        countOfPrefixStr = 0;
    }

    public int size() {
        return children.size();
    }
}

public class Trie {
    private Node root;

    public Trie() {
        this.root = new Node();
    }

    /*
     * Insert a word into the prefix tree
     */
    public void insert(String word) {
        if (word == null || word.isEmpty()) {
            return;
        }
        if (search(word)) {
            // This part is not necessary
            // just a choice to not have duplicate words
            return;
        }
        Node node = root;
        for (char c : word.toCharArray()) {
            // If the current character is not
            // already a child of the node, add a new node
            node.children.putIfAbsent(c, new Node());
            node = node.children.get(c);

            // increment the count of prefix strings
            node.countOfPrefixStr++;
        }
        // all characters are processed, mark the current node as a complete word
        node.isWordComplete = true;
    }

    /*
     * Search a word in the prefix tree
     */
    public boolean search(String word) {
        Node node = root;
        for (char c : word.toCharArray()) {
            node = node.children.get(c);
            // If the child node does not exist, return false
            if (node == null) {
                return false;
            }
        }
        // if all processed then check if end of word
        return node.isWordComplete;
    }

    /*
     * Check if there is any word in the
     * prefix tree that starts with the given prefix
     * 
     * 
     * Similar to search, but we don't need to check if the word is complete
     */
    public boolean startsWith(String prefix) {
        Node node = root;
        for (char c : prefix.toCharArray()) {
            node = node.children.get(c);
            // If the child node does not exist, return false
            if (node == null) {
                return false;
            }
        }
        // If all characters are processed successfully, return true
        return true;
    }

    /*
     * Count the number of words in the prefix
     * tree that starts with the given prefix
     */
    public int countOfPrefixStr(String prefix) {
        Node node = root;
        for (char c : prefix.toCharArray()) {
            node = node.children.get(c);
            // If the child node does not exist, return false
            if (node == null) {
                return 0;
            }
        }
        // return the count of prefix strings
        return node.countOfPrefixStr;
    }

    public static void main(String[] args) {
        Trie obj = new Trie();

        String[] words = { "bruh", "bro", "bye", "a", "new", "newton", "bruh" };

        for (String word : words) {
            obj.insert(word);
        }

        System.out.println(obj.search("bruh"));
        System.out.println(obj.search("newt"));
        System.out.println(obj.startsWith("ne"));
        System.out.println(obj.countOfPrefixStr("b"));
    }
}