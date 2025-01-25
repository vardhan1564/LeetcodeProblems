class POTD25 {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
     int n = nums.length;

        //  Find location of each number
        List<int[]> copy = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            copy.add(new int[]{nums[i], i});
        }

        // Get ordered array with their original array index
        copy.sort(Comparator.comparingInt(a -> a[0]));

        //  Find groups and place ordered values
        int left = 0, right = 1;
        while (right < n) {
            
            List<Integer> pos = new ArrayList<>();
            pos.add(copy.get(left)[1]);
            while (right < n && Math.abs(copy.get(right)[0] - copy.get(right - 1)[0]) <= limit) {
                pos.add(copy.get(right)[1]);
                right++;
            }

            // Order indices
            Collections.sort(pos);

            
            for (int i = 0; i < right - left; i++) {
                nums[pos.get(i)] = copy.get(left + i)[0];
            }

            left = right;
            right++;
        }
        return nums;   
    }
}