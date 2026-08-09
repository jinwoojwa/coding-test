import java.util.*;

class Solution {
    public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];
        
        int[] hailstoneSeq = findHailstoneNumber(k);
        
        double[] prefixSum = new double[hailstoneSeq.length];
        for (int i = 1; i < hailstoneSeq.length; ++i) {
            double integral = ((double) hailstoneSeq[i - 1] + hailstoneSeq[i]) / 2;
            prefixSum[i] = prefixSum[i - 1] + integral;
        }
        
        int n = hailstoneSeq.length - 1;
        for (int i = 0; i < ranges.length; ++i) {
            int st = ranges[i][0];
            int end = n + ranges[i][1];
            
            if (st > end) answer[i] = -1;
            else if (st == end) answer[i] = 0;
            else {
                answer[i] = prefixSum[end] - prefixSum[st];
            }
        }
        
        return answer;
    }
    
    private int[] findHailstoneNumber(int k) {
        List<Integer> hailstoneSeq = new ArrayList<>();
        hailstoneSeq.add(k); // (0, k)
        
        while (k != 1) {
            if (k % 2 == 0) k /= 2;
            else k = k * 3 + 1;
            hailstoneSeq.add(k);
        }
        return hailstoneSeq.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}

// 1. 우박수열이 1이 되는 n을 구하기
// 2. 모든 구간에 대해 중복 계산하면 X -> 누적합 배열 구하기
// 3. 각 구간에 대해 구간합 적용