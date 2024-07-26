class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
 Set<List<Integer>> set=new HashSet<>();
        List<Integer> List=new ArrayList<>();
        f(0,nums.length,nums,List,set);

         List<List<Integer>> myList=new ArrayList<>(set);
        return myList;
    }
    public static void f(int i,int n,int nums[],List<Integer> l1,Set<List<Integer>> set){
        if(i>=n){
            set.add(new ArrayList<>(l1));
            return ;
        }
        l1.add(nums[i]);
        f(i+1,n,nums,l1,set);
        l1.remove(l1.size()-1);
        f(i+1,n,nums,l1,set);
    }
}