import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> q = getRemainQueue(progresses, speeds);
        List<Integer> res = new ArrayList<>();
        
        int cnt = 1;
        int remain = q.poll();
        while (!q.isEmpty()) {
            if (q.peek() <= remain) {
                q.poll();
                cnt++;
            } else {
                res.add(cnt);
                remain = q.poll();
                cnt = 1;
            }
        }
        res.add(cnt);
        
        return res.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
    
    private Queue<Integer> getRemainQueue(int[] progresses, int[] speeds) {
        Queue<Integer> q = new ArrayDeque<>();
        
        for (int i = 0; i < progresses.length; ++i) {
            int remain = (100 - progresses[i] + speeds[i] - 1) / speeds[i]; 
            q.offer(remain);
        }
        return q;
    }
}