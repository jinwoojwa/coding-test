import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : scoville) {
            pq.offer(i);
        }
        
        while(pq.peek() < K && pq.size() > 1) {
            int newScoville = pq.poll() + (2 * pq.poll());
            pq.offer(newScoville);
            answer++;
        }
        
        if (pq.peek() < K) return -1;
        return answer;
    }
}