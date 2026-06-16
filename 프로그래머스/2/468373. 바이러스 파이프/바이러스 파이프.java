import java.util.*;

class Solution {
    
    int n;
    int k;
    int answer = 0;
    List<List<Node>> graph = new ArrayList<>();
    
    public record Node(int num, int type) {}
    
    public int solution(int n, int infection, int[][] edges, int k) {
        this.n = n;
        this.k = k;
        
        for (int i = 0; i <= n; ++i) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            int type = edge[2];
            
            graph.get(x).add(new Node(y, type));
            graph.get(y).add(new Node(x, type));
        }
        
        boolean[] infected = new boolean[n + 1];
        infected[infection] = true;

        dfs(0, infected);

        return answer;
    }
    
    private void dfs(int depth, boolean[] infected) {
        answer = Math.max(answer, count(infected));

        if (depth == k) return;

        for (int type = 1; type <= 3; type++) {
            boolean[] next = infected.clone();

            bfs(next, type);

            dfs(depth + 1, next);
        }
    }
    
    private void bfs(boolean[] infected, int type) {
        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            if (infected[i]) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Node next : graph.get(cur)) {
                if (next.type() != type) continue;
                if (infected[next.num()]) continue;

                infected[next.num()] = true;
                q.offer(next.num());
            }
        }
    }
    
    private int count(boolean[] infected) {
        int cnt = 0;

        for (int i = 1; i <= n; i++) {
            if (infected[i]) cnt++;
        }
        return cnt;
    }
}