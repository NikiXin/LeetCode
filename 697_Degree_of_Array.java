class Solution {
    public int findShortestSubArray(int[] nums) {
        // use count to record num and frequence
        // use first to record num and first index
        Map<Integer, Integer> count = new HashMap<>();
        Map<Integer, Integer> first = new HashMap<>();
        
        int deg = 0;
        int ans = 0;
        for (int i=0; i<nums.length; i++){
            first.putIfAbsent(nums[i],i);
            count.put(nums[i], count.getOrDefault(nums[i], 1)+1);
            if(count.get(nums[i]) > deg){
                deg = count.get(nums[i]);
                ans =  i-first.get(nums[i])+1;
            }
            else if (count.get(nums[i]) == deg){
                ans = Math.min(ans, i-first.get(nums[i])+1);
            }
        }
        
        return ans;
    }
}