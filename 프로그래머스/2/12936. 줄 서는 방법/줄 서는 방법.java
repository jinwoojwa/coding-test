import java.util.*;

class Solution {
    public int[] solution(int n, long k) {
        // n명의 사람을 줄 세우는 방법의 가짓수 = n!
        // k가 주어졌을 때, 각 자릿수를 구할 수 있음
        
        long[] factorials = makeFactorial(n);
        
        List<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= n; ++i) {
            numbers.add(i);
        }

        int[] answer = new int[n];
        k--;

        for (int i = 0; i < n; ++i) {
            long remain = factorials[n - 1 - i];

            int index = (int) (k / remain);
            answer[i] = numbers.get(index);
            numbers.remove(index);

            k %= remain;
        }

        return answer;        
    }
    
    private long[] makeFactorial(int n) {
        long[] factorials = new long[n + 1];
        
        factorials[0] = 1;
        
        for (int i = 1; i <= n; ++i) {
            factorials[i] = factorials[i - 1] * i;
        }
        return factorials;
    }
}