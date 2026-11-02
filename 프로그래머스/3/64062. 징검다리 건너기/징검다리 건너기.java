class Solution {
    public int solution(int[] stones, int k) {
        int left = 0, right = 200_000_000;
        
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            
            if (isPossible(mid, stones, k)) left = mid;
            else right = mid - 1;
        }
        
        return left;
    }
    
    private boolean isPossible(int num, int[] stones, int k) {
        int skip = 0;
        for (int stone : stones) {
            if (stone < num) {
                if (++skip >= k) return false;
            }
            else skip = 0;
        }
        return true;
    }
}