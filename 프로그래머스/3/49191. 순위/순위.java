import java.util.*;

class Solution {
    
    int n;
    List<Integer>[] winGraph;
    List<Integer>[] loseGraph;
    
    public int solution(int n, int[][] results) {
        this.n = n;
        winGraph = new List[n + 1];
        loseGraph = new List[n + 1];
        
        int[] winCnt = new int[n + 1];
        int[] loseCnt = new int[n + 1];
        
        for (int i = 0; i <= n; ++i) {
            winGraph[i] = new ArrayList<>();
            loseGraph[i] = new ArrayList<>();
        }
        
        for (int[] result : results) {
            int win = result[0];
            int lose = result[1];
            
            winGraph[win].add(lose);
            loseGraph[lose].add(win);
        }
        
        int answer = 0;
        for (int i = 0; i <= n; ++i) {
            // i 번째를 이긴 사람이 몇명인지
            int lose = searchLose(i);
            
            // i 번째에게 진 사람이 몇명인지
            int win = searchWin(i);
            
            if (win + lose == n - 1) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private int searchLose(int st) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        
        q.offer(st);
        
        int cnt = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            
            for (int w : loseGraph[cur]) {
                if (visited[w]) continue;
                
                cnt++;
                visited[w] = true;
                q.offer(w);
            }
        }
        return cnt;
    }
    
    private int searchWin(int st) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        
        q.offer(st);
        
        int cnt = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            
            for (int l : winGraph[cur]) {
                if (visited[l]) continue;
                
                cnt++;
                visited[l] = true;
                q.offer(l);
            }
        }
        return cnt;
    }
}







