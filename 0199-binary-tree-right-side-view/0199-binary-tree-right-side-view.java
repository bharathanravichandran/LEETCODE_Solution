class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        // we can do this by level order traversal
        
        List<Integer> result = new ArrayList<Integer>();
        if(root==null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int n = queue.size();
        while(queue.size()>0)
        {
            // hack it size
            n=queue.size();
           for(int i =0;i<n;i++)
           {
              TreeNode temp =  queue.poll();
               if(i==n-1) result.add(temp.val);
               if(temp.left!=null) queue.offer(temp.left);
               if(temp.right!=null) queue.offer(temp.right);
           }
            
            
            
            
        }
        return result;
    }
}