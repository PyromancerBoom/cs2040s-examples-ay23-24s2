import java.util.Collections;
import java.util.HashMap;

class Node {
    HashMap<Character, Node> children;
    boolean isWordComplete;
    int countMale; // count of male names
    int countFemale; // count of female names

    public Node() {
        children = new HashMap<>();
        isWordComplete = false;
        countMale = 0;
        countFemale = 0;
    }
}

public class Names {
    private Node root;

    public Names() {
        this.root = new Node();
    }

    // Same as normal trie, but we also
    // keep track of the gender
    public void insert(String name, String gender, int count) {
        if (name == null || name.isEmpty()) {
            return;
        }
        Node node = root;
        for (char c : name.toCharArray()) {
            node.children.putIfAbsent(c, new Node());
            node = node.children.get(c);
            // increment the count of prefix strings
            if (gender.equals("male")) {
                node.countMale += count;
            } else if (gender.equals("female")) {
                node.countFemale += count;
            }
        }
        // mark as complete word
        node.isWordComplete = true;
    }

    public int countName(String name, String gender) {
        Node node = root;
        for (char c : name.toCharArray()) {
            node = node.children.get(c);
            if (node == null) {
                return 0;
            }
        }
        return gender.equals("male") ? node.countMale : node.countFemale;
    }

    public int countPrefix(String prefix, String gender) {
        Node node = root;
        for (char c : prefix.toCharArray()) {
            node = node.children.get(c);
            if (node == null) {
                return 0;
            }
        }
        return gender.equals("female") ? node.countFemale : node.countMale;
    }

    public static void main(String[] args) {
        Names trie = new Names();
        // String[] names = { "john", "jane", "james", "jessica", "jack", "jill", "word"
        // };
        // sort the names
        // Collections.sort(java.util.Arrays.asList(names));

        trie.insert("john", "male", 100);
        trie.insert("jane", "female", 200);
        trie.insert("james", "male", 150);
        trie.insert("jessica", "female", 250);
        trie.insert("jack", "male", 50);
        trie.insert("jill", "female", 75);

        // System.out.println("Names: " + java.util.Arrays.toString(names));

        System.out.println("Count of 'john' (male): " + trie.countName("john", "male"));
        System.out.println("Count of 'jane' (female): " + trie.countName("jane", "female"));
        System.out.println("Count of 'ja' prefix (male): " + trie.countPrefix("ja", "male"));
        System.out.println("Count of 'je' prefix (female): " + trie.countPrefix("je", "female"));
        System.out.println("Count of 'j' prefix (male): " + trie.countPrefix("j", "male"));
    }
}