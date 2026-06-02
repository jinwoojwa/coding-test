import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> q = new ArrayDeque<>();
        List<Integer> ans = new ArrayList<>();
        
        for (int i = 0; i < progresses.length; ++i) {
            double remain = (double)(100 - progresses[i]) / speeds[i];
            int date = (int)Math.ceil(remain);
            
            if (!q.isEmpty() && q.peek() < date) {
                ans.add(q.size());
                q.clear();
            }
            
            q.offer(date);
        }
        ans.add(q.size());
        
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}