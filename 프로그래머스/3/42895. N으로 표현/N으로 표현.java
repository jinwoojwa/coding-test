import java.util.*;

class Solution {
    
    Set<Integer>[] dp = new HashSet[9];
    
    public int solution(int N, int number) {    
        for (int i = 0; i <= 8; ++i) {
            dp[i] = new HashSet<>();
        }
        
        // N을 이어붙여 만들 수 있는 수
        for (int i = 1; i <= 8; ++i) {
            int num = Integer.parseInt(String.valueOf(N).repeat(i));
            dp[i].add(num);
        }
        
        // 사칙연산을 통해 만들 수 있는 수
        for (int i = 2; i <= 8; ++i) {
            for (int j = 1; j <= i; ++j) {
                calc(i, j);
            }
        }
        
        for (int i = 1; i <= 8; ++i) {
            if (dp[i].contains(number)) return i;
        }
        
        return -1;
    }
    
    private void calc(int i, int j) {
        for (int n1 : dp[j]) {
            for (int n2 : dp[i - j]) {
                dp[i].add(n1 + n2);
                dp[i].add(n1 - n2);
                dp[i].add(n1 * n2);
                if (n2 != 0) dp[i].add(n1 / n2);
            }
        } 
    }
}

/*
dp[i] = 숫자 N을 i번 사용해서 만들 수 있는 숫자들의 집합

5 55 555 5555 55555 555555 5555555 55555555

5

5+5, 5-5, 5*5, 5/5
 |
(5+5)+5, (5+5)-5, (5+5)*5, (5+5)/5

*/