import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> q = getRemainDates(progresses, speeds);
        List<Integer> ans = new ArrayList<>();
        
        int cur = q.poll();
        int cnt = 1;
        while (!q.isEmpty()) {
            if (q.peek() <= cur) {
                cnt++;
                q.poll();
            }
            else {
                ans.add(cnt);
                cur = q.poll();
                cnt = 1;
            }
        }
        ans.add(cnt);
        
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
    
    private Queue<Integer> getRemainDates(int[] progresses, int[] speeds) {
        Queue<Integer> remainDates = new ArrayDeque<>();
        
        for (int i = 0; i < progresses.length; ++i) {
            int remain = 100 - progresses[i];
            int need
                = (remain % speeds[i] == 0) ? remain / speeds[i] : remain / speeds[i] + 1;
            
            remainDates.offer(need);
        }
        return remainDates;
    }
}