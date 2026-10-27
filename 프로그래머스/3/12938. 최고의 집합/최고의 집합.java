import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        int eachNum = s / n;
        int remain = s % n;
        
        int[] answer = new int[n];
        for (int i = 0; i < n; ++i) {
            answer[i] = eachNum;
        }
        for (int i = 0; i < remain; ++i) {
            answer[n - 1 - i]++;
        }
        
        if (eachNum < 1) return new int[]{-1};
        return answer;
    }
}