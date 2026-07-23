import java.util.*;

class Solution {
    
    static final int MAX_VALUE = 10_000_000;
    
    public int[] solution(long begin, long end) {
        
        List<Integer> numList = new ArrayList<>();
        for (int n = (int)begin; n <= (int)end; ++n) {
            int res = check(n);
            
            if (res == 0) numList.add(0);
            else if (res == -1) numList.add(1);
            else numList.add(res);
        }
        
        return numList.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
    
    private int check(int num) {
        if (num == 1) return 0;
        
        int maxVal = -1;
        for (int i = 2; i * i <= num; ++i) {
            if (num % i == 0) {
                int d = num / i;
                
                if (d <= MAX_VALUE) return d;
                
                maxVal = Math.max(maxVal, i);
            }
        }
        return maxVal;
    }
}

// 소수는 1임
// 짝수 n은 n / 2임
// 어떤 수의 가장 큰 약수 (자신을 뺀)
// ex) 21의 약수 -> 1, 3, 7, 21 -> 자신을 뺀 가장 큰 약수 = 7

// 최대로 구해야 하는 수의 개수 = 5000개