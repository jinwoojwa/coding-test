import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; ++i) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] road : roads) {
            int a = road[0];
            int b = road[1];
            
            graph[a].add(b);
            graph[b].add(a);
        }
        
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(destination);
        dist[destination] = 0;
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            
            for (int next : graph[cur]) {
                if (dist[next] >= 0) continue;
                
                dist[next] = dist[cur] + 1;
                q.offer(next);
            }
        }
        
        int[] answer = new int[sources.length];
        for (int i = 0; i < sources.length; ++i) {
            answer[i] = dist[sources[i]];
        }
        
        return answer;
    }
}

// destination에서 각 source까지의 최단 거리를 구하면 됨.
// -> destination을 시작점으로 두고 BFS를 돌려서 각 지역까지의 거리를 저장
// -> 못 가는 지역(복귀 불가능)의 경우 -1