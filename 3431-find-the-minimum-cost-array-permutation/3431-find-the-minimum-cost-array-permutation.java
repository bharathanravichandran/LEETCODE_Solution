class Solution {
    //memo[i][j] = min score using rest of indicies if bit mask i is already used, and last num used was j
    int[][] memo;
    //val[i][j] = next num used that gave min of memo[i][j], only used to build answer at the end
    int[][] val;
    int n;
    public int[] findPermutation(int[] nums) {
        n = nums.length;
        memo = new int[1<<n][n];
        val = new int[1<<n][n];
        for(int i = 0; i < (1<<n); i++) {
            for(int j = 0; j < n; j++) {
                memo[i][j] = Integer.MAX_VALUE;
            }
        }
        //rotating perm doesn't change the score
        //so we always start perm with 0 to get lexicographically smallest perm
        //bitmask is 1 and prev is 0
        dfs(1, 0, nums);

        //start building answer
        int[] ans = new int[n];
        int mask = 1;
        int prev = 0;
        for(int i = 1; i < n; i++) {
            prev = val[mask][prev];
            ans[i] = prev;
            mask += (1<<prev);
        }
        return ans;
    }

    public int dfs(int mask, int prev, int[] nums) {
        if(Integer.bitCount(mask)==n) { //perm is set, return |perm[n - 1] - nums[perm[0]]|
            return Math.abs(prev-nums[0]);
        }
        if(memo[mask][prev]!=Integer.MAX_VALUE) { //if seen current state before, return it
            return memo[mask][prev];
        }
        for(int i = 0; i < n; i++) {
            if((mask&(1<<i)) == 0) { //index i is unused, so we try to use i as next element
                //Math.abs(prev-nums[i]) is added to score, and recursion for minimum value of next state
                int res = Math.abs(prev-nums[i]) + dfs(mask+(1<<i), i, nums); 
                if(res < memo[mask][prev]) { //found new minimum, update memo and val
                    memo[mask][prev] = res;
                    val[mask][prev] = i;
                }
            }
        }
        return memo[mask][prev];
    }
}