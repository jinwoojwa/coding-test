import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        Queue<int[]> q = new ArrayDeque<>(); // loc, priority
        
        for (int i = 0; i < priorities.length; ++i) {
            pq.offer(priorities[i]);
            q.offer(new int[]{i, priorities[i]});
        }
        
        int answer = 0;
        while (!pq.isEmpty()) {
            int[] cur = q.poll();
            
            if (pq.peek() > cur[1]) q.offer(cur);
            else {
                answer++;
                if (cur[0] == location) return answer;
                
                // 프로세스 실행
                pq.poll();
            }
        }
        
        return answer;
    }
}