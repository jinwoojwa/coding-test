import java.util.*;

class Solution {
    public int solution(int[] a) {
        int answer = 0;
        int n = a.length;
        
        int[] leftMin = new int[n]; // leftMin[i]  = i의 왼쪽 구간의 최솟값
        int[] rightMin = new int[n]; // rightMin[i] = i의 오른쪽 구간의 최솟값
        
        leftMin[0] = a[0];
        for (int i = 1; i < n; ++i) {
            leftMin[i] = Math.min(leftMin[i-1], a[i]);
        }
        
        rightMin[n-1] = a[n-1];
        for (int i = n - 2; i >= 0; --i) {
            rightMin[i] = Math.min(rightMin[i+1], a[i]);
        }
        
        for (int i = 0; i < n; ++i) {
            if (a[i] <= leftMin[i] || a[i] <= rightMin[i]) {
                answer++;
            }
        }
        
        return answer;
    }
}