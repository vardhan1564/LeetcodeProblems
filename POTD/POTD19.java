class POTD19 {
    private static boolean isWithinBounds(int row, int col, int numRows, int numCols) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols;
    }

    public int trapRainWater(int[][] heightMap) {
        int numRows = heightMap.length;
        int numCols = heightMap[0].length;
        if (numRows < 3 || numCols < 3) // Need minimum 3x3 matrix to hold water
            return 0;

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        boolean[][] visited = new boolean[numRows][numCols];

        // Step-1: Push all boundary elements as starting points
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (row == 0 || row == numRows - 1 || col == 0 || col == numCols - 1) {
                    minHeap.offer(new int[] { heightMap[row][col], row, col });
                    visited[row][col] = true;
                }
            }
        }

        // Step-2: Apply BFS similar to level-order traversal
        int currentWaterLevel = 0;
        int totalTrappedWater = 0;
        int[] directions = { -1, 0, 1, 0, -1 }; // Up, Right, Down, Left

        while (!minHeap.isEmpty()) {
            int[] currentCell = minHeap.poll();
            int cellHeight = currentCell[0];
            int currentRow = currentCell[1];
            int currentCol = currentCell[2];
            currentWaterLevel = Math.max(currentWaterLevel, cellHeight);

            // 4-directional traversal
            for (int i = 0; i < 4; i++) {
                int newRow = currentRow + directions[i];
                int newCol = currentCol + directions[i + 1];
                if (isWithinBounds(newRow, newCol, numRows, numCols) && !visited[newRow][newCol]) {
                    visited[newRow][newCol] = true;
                    minHeap.offer(new int[] { heightMap[newRow][newCol], newRow, newCol });
                    if (heightMap[newRow][newCol] < currentWaterLevel) {
                        totalTrappedWater += currentWaterLevel - heightMap[newRow][newCol];
                    }
                }
            }
        }
        return totalTrappedWater;
    }
}