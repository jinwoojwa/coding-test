import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        
        long[] count = new long[1001];
        for (int i : weights) count[i]++;
        
        for (int i = 100; i <= 1000; ++i) {
            if (count[i] == 0) continue;
            
            // 1 : 1
            answer += count[i] * (count[i] - 1) / 2;
            
            // 2 : 3
            if (i * 3 / 2 <= 1000 && i * 3 % 2 == 0) {
                answer += count[i] * count[i * 3 / 2];
            }
            
            // 1 : 2
            if (i * 2 <= 1000) {
                answer += count[i] * count[i * 2];
            }
            
            // 3 : 4
            if (i * 4 / 3 <= 1000 && i * 4 % 3 == 0) {
                answer += count[i] * count[i * 4 / 3];
            }
        }
        return answer;
    }
}