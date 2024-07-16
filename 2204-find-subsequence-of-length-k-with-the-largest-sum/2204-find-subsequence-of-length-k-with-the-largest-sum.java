class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        PriorityQueue<Integer> mins = new PriorityQueue<>((i1, i2) -> nums[i1] - nums[i2]);
        int[] tmp = new int[nums.length];
        for (int i = 0; i < k; i++) {
            mins.add(i);
            tmp[i] = 1;
        }
        for (int i = k; i < nums.length; i++) {
            int j = mins.peek();
            if (nums[j] < nums[i]) {
                mins.poll();
                mins.add(i);
                tmp[j] = 0;
                tmp[i] = 1;
            }
        }
        int[] result = new int[k];
        for (int i = 0, j = 0; i < tmp.length; i++) {
            if (tmp[i] == 1)    result[j++] = nums[i];
        }
        return result;
    }
}