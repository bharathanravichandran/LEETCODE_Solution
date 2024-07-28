class Solution {
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        // Initialize graph representation using adjacency lists
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++)
            graph[i] = new ArrayList<>();
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        
        // Initialize deque for BFS
        Deque<int[]> deque = new LinkedList<>();
        // Array to count the number of times each node is visited
        int[] vn = new int[n + 1];
        // Array to keep track of the arrival times at each node
        int[] ta = new int[n + 1];
        Arrays.fill(ta, -1);
        
        // Start BFS from node 1 at time 0
        deque.offerLast(new int[] {1, 0});
        ta[0] = 0;
        
        while (!deque.isEmpty()) {
            int cs = deque.size();
            for (int i = 0; i < cs; i++) {
                int[] cur = deque.pollFirst();
                int nextTime;
                int curLight = cur[1] / change;
                
                // Determine the next possible time to move considering the traffic light
                if (curLight % 2 == 0) {
                    nextTime = cur[1] + time;
                } else {
                    nextTime = (curLight + 1) * change + time;
                }
                
                // Explore all neighbors of the current node
                for (int nn : graph[cur[0]]) {
                    // Proceed if the node has been visited less than twice and the arrival time is less than the next time
                    if (vn[nn] < 2 && ta[nn] < nextTime) {
                        deque.offerLast(new int[] {nn, nextTime});
                        vn[nn]++;
                        ta[nn] = nextTime;
                        // Return the time when node n is visited for the second time
                        if (nn == n && vn[nn] == 2) return nextTime;
                    }
                }
            }
        }
        return -1;
    }
}