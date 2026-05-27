import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int halfN = nums.length / 2;
        
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.merge(n, 1, Integer::sum);
        }
        
        int mapSize = map.size();
        
        if (halfN >= mapSize) return mapSize;
        else return halfN;
    }
}