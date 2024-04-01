// https://open.kattis.com/problems/countingstars

import java.io.*;
import java.util.*;

public class CountingStars {
    // Directions to move in the grid: up, right, down, left
    private static final int[] dRow = { -1, 0, 1, 0 };
    private static final int[] dColumn = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        // Input Output stuff, testcase stuff
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder outputBuilder = new StringBuilder();
        String currentLine;
        int testCaseNumber = 1;

        while ((currentLine = inputReader.readLine()) != null) {
            // parse input
            String[] dimensions = currentLine.split(" ");
            int rows = Integer.parseInt(dimensions[0]);
            int cols = Integer.parseInt(dimensions[1]);

            // Initialize the sky map and visited array
            char[][] skyMap = new char[rows][cols];
            boolean[][] visited = new boolean[rows][cols]; // this'll track whether a cell has been visited

            // reading the map of the sky, some more I/O stuff
            for (int row = 0; row < rows; row++) {
                skyMap[row] = inputReader.readLine().toCharArray();
            }

            // Count of stars in the current sky map,
            // basically the number of connected
            // components
            int starsCount = 0;
            // now lets go through the sky map
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    // if the cell is part of a star and not visited, do a search
                    if (skyMap[row][col] == '-' && !visited[row][col]) {
                        starsCount++;
                        search(skyMap, visited, row, col, rows, cols);
                    }
                }
            }

            // some more I/O stuff
            outputBuilder.append("Case ").append(testCaseNumber++).append(": ").append(starsCount).append("\n");
        }

        // Output the result
        System.out.print(outputBuilder.toString());
        inputReader.close();
    }

    // BFS
    private static void search(char[][] skyMap, boolean[][] visited, int startRow, int startCol, int rows,
            int cols) {

        // typical BFS stuff
        // initialisation
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { startRow, startCol });
        visited[startRow][startCol] = true; // Mark the starting cell as visited

        while (!queue.isEmpty()) {
            int[] currentCell = queue.poll();

            // Fetch current coordinates
            int currentRow = currentCell[0];
            int currentCol = currentCell[1];

            // Explore all 4 directions
            for (int i = 0; i < 4; i++) {
                int newRow = currentRow + dRow[i];
                int newCol = currentCol + dColumn[i];

                // Check if the new cell is within bounds
                boolean isWithinBounds = newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols;
                // check if its a star
                // We can't use this statement directly, because it will throw an out of bounds
                // error if the above condition is false
                // boolean isStar = skyMap[newRow][newCol] == '-';
                // check if its not visited
                // boolean isNotVisited = !visited[newRow][newCol];

                if (isWithinBounds && skyMap[newRow][newCol] == '-' && !visited[newRow][newCol]) {
                    queue.add(new int[] { newRow, newCol });
                    visited[newRow][newCol] = true; // Mark the new cell as visited
                }
            }
        }
    }
}
