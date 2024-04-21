import java.util.*;
import java.io.*;

class onaveragetheyrepurple {
    public static void main(String[] args) {
        // I/O Stuff
        FastScanner flash = new FastScanner();
        PrintWriter writer = new PrintWriter(System.out);
        int N = flash.nextInt(); // nodes
        int M = flash.nextInt(); // edges

        // Adjacency List for our graph
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>(N);

        // visited array
        boolean[] visited = new boolean[N + 1];

        // building graph, some more I/O stuff
        for (int i = 1; i <= M; i++) {
            int nodeA = flash.nextInt();
            int nodeB = flash.nextInt();

            if (!graph.containsKey(nodeA)) {
                graph.put(nodeA, new ArrayList<>());
            }

            if (!graph.containsKey(nodeB)) {
                graph.put(nodeB, new ArrayList<>());
            }

            // bidirectional
            graph.get(nodeA).add(nodeB);
            graph.get(nodeB).add(nodeA);
        }

        // max color change
        int maxColorChange = 0;

        // shortest path 1 to N, BFS
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        // node levels
        HashMap<Integer, Integer> level = new HashMap<>();
        // first node is level 0
        level.put(1, 0);

        // bfs
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int neighbor : graph.get(curr)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    level.put(neighbor, level.get(curr) + 1); // also setting neighbour node level
                }
            }
        }

        // last node depth
        maxColorChange = level.get(N) - 1;

        // Output
        writer.println(maxColorChange);
        writer.flush();
    }
}

// I/O Stuff
class FastScanner {
    // This is a modified version of Timothy Wan Kai Yang's FastScanner class
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer("");

    String next() {
        while (!st.hasMoreTokens())
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        return st.nextToken();
    }

    String readLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    int[] readIntArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = nextInt();
        return a;
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    long[] readLongArray(int n) {
        long[] a = new long[n];
        for (int i = 0; i < n; i++)
            a[i] = nextLong();
        return a;
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }
}
