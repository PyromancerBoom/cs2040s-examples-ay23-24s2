import java.util.*;

class Dijkstra {
    public static Map<Integer, Integer> shortestPath(int n, List<List<Integer>> edges, int src) {
        /**
         * Hashmap to store graph
         * Key: node
         * Value: list of neighbours and their weights
         */
        HashMap<Integer, ArrayList<int[]>> nodeMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            nodeMap.put(i, new ArrayList<>());
        }
        for (List<Integer> edge : edges) {
            int node = edge.get(0);
            int neighbour = edge.get(1);
            int weight = edge.get(2);
            int[] arr = new int[] { neighbour, weight };
            nodeMap.get(node).add(arr);
        }

        // print input graph
        // printNodeMap(nodeMap);

        /**
         * Hashmap to store shortest distances
         * Key: node
         * Value: shortest distance from src to node
         */
        HashMap<Integer, Integer> shortestDists = new HashMap<>();
        shortestDists.put(src, 0);

        /**
         * Priority queue to store nodes and their distances from src
         * int[] arr = {node, distance}
         */
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[1] == b[1]) {
                    return a[0] - b[0];
                }
                return a[1] - b[1];
            }
        });
        pq.add(new int[] { src, 0 });

        /**
         * Dijkstra's algorithm
         */
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currNode = curr[0];
            int currDist = curr[1];

            if (!shortestDists.containsKey(currNode)) {
                shortestDists.put(currNode, currDist);
            }

            // Relax edges
            for (int[] neighbour : nodeMap.get(currNode)) {
                int neighbourNode = neighbour[0];
                int weight = neighbour[1];

                int newDist = currDist + weight;
                if (!shortestDists.containsKey(neighbourNode) || newDist < shortestDists.get(neighbourNode)) {
                    shortestDists.put(neighbourNode, newDist);
                    pq.add(new int[] { neighbourNode, newDist });
                }
            }
        }

        // Add -1 for nodes that are unreachable
        // can also be Integer.MAX_VALUE
        for (int i = 0; i < n; i++) {
            if (!shortestDists.containsKey(i)) {
                shortestDists.put(i, -1);
            }
        }
        return shortestDists;
    }

    public static void main(String[] args) {
        Map<Integer, Integer> shortestPaths = new HashMap<Integer, Integer>();
        List<List<Integer>> edges = new ArrayList<List<Integer>>();
        int n;
        int src;

        // Test Case 1
        // n = 5;
        // edges.add(Arrays.asList(0, 1, 10));
        // edges.add(Arrays.asList(0, 2, 3));
        // edges.add(Arrays.asList(1, 3, 2));
        // edges.add(Arrays.asList(2, 1, 4));
        // edges.add(Arrays.asList(2, 3, 8));
        // edges.add(Arrays.asList(2, 4, 2));
        // edges.add(Arrays.asList(3, 4, 5));
        // src = 0;
        // shortestPaths = shortestPath(n, edges, src);

        // Test Case 2
        // n = 4;
        // edges = new ArrayList<List<Integer>>();
        // edges.add(Arrays.asList(0, 1, 5));
        // edges.add(Arrays.asList(0, 2, 7));
        // edges.add(Arrays.asList(1, 2, 2));
        // edges.add(Arrays.asList(1, 3, 6));
        // edges.add(Arrays.asList(2, 3, 4));
        // src = 1;
        // shortestPaths = shortestPath(n, edges, src);

        // Test Case 3
        n = 3;
        edges = new ArrayList<List<Integer>>();
        edges.add(Arrays.asList(0, 1, 3));
        edges.add(Arrays.asList(1, 2, 1));
        edges.add(Arrays.asList(2, 0, 4));
        src = 0;
        shortestPaths = shortestPath(n, edges, src);

        // Print the shortest paths
        for (Map.Entry<Integer, Integer> entry : shortestPaths.entrySet()) {
            System.out.println("{" + entry.getKey() + ":" + entry.getValue() + "}");
        }
    }
}
