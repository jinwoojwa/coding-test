import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            adj.add(new ArrayList<>());
        }
        
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (computers[i][j] == 0) continue;
                adj.get(i).add(j);
            }
        }
        
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        
        for (int i = 0; i < n; ++i) {
            if (visited[i]) continue;
            
            answer++;
            q.offer(i);
            visited[i] = true;
            
            while(!q.isEmpty()) {
                int cur = q.poll();
                
                for (int next : adj.get(cur)) {
                    if (visited[next]) continue;
                    
                    q.offer(next);
                    visited[next] = true;
                }
            }
        }
        
        return answer;
    }
}