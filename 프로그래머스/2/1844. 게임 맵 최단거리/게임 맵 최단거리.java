import java.util.*;

class Solution {
    
    static int n, m;
    static int[][] dist;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    
    static void bfs(int[][] maps) {
        Queue<int[]> q = new ArrayDeque<>();
        
        q.offer(new int[]{0, 0});
        dist[0][0] = 1;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            
            for (int dir = 0; dir < 4; ++dir) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];
                
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (dist[nx][ny] > -1 || maps[nx][ny] == 0) continue;
                
                q.offer(new int[]{nx, ny});
                dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
            }
        }
    }
    
    public int solution(int[][] maps) {
        
        if (maps[0][0] == 0) return -1;
        
        n = maps.length;
        m = maps[0].length;
        
        dist = new int[n][m];
        for (int[] d : dist) {
            Arrays.fill(d, -1);
        }
        
        bfs(maps);
        
        return dist[n - 1][m - 1];
    }
}