import java.util.*;

class Solution {
    
    private static final int DIV = 1_000_000_007;
    
    public int solution(int n, int[] money) {
        int[] ways = new int[n + 1]; // ways[m] = m원을 거슬러 줄 방법의 수
        
        ways[0] = 1;

        for (int m : money) {
            for (int i = m; i <= n; i++) {
                ways[i] += ways[i - m];
                ways[i] %= DIV;
            }
        }
        return ways[n];
    }
}