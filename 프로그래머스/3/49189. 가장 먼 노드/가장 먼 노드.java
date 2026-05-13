import java.util.*;
import java.util.stream.*;

class Solution {
    
    static int maxDistance = -1;
    static List<List<Integer>> adj = new ArrayList<>();
    static int[] dist;
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        for (int i = 0; i < n; ++i) {
            adj.add(new ArrayList<>());
        }
        
        for (int[] e : edge) {
            adj.get(e[0] - 1).add(e[1] - 1);
            adj.get(e[1] - 1).add(e[0] - 1);
        }
        
        dist = new int[n];
        Arrays.fill(dist, -1);
        
        bfs();
        
        return (int) Arrays.stream(dist)
            .filter(d -> d == maxDistance)
            .count();
    }
    
    static void bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        
        q.offer(0);
        dist[0] = 0;
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            
            for (int next : adj.get(cur)) {
                if (dist[next] >= 0) continue;
                
                dist[next] = dist[cur] + 1;
                maxDistance = Math.max(maxDistance, dist[next]);
                
                q.offer(next);
            }
        }
    }
}