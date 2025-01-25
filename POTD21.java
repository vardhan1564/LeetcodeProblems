class POTD21{
    public long gridGame(int[][] grid) {
        int n = grid[0].length;
        if (n < 2)
            return 0;

        long topSum = 0;
        for (int val : grid[0]) {
            topSum += val;
        }

        long bottomSum = 0;
        long minSum = Long.MAX_VALUE;

        for (int pp = 0; pp < n; ++pp) {
            topSum -= grid[0][pp];
            minSum = Math.min(minSum, Math.max(topSum, bottomSum));
            bottomSum += grid[1][pp];
        }

        return minSum;
    }
}