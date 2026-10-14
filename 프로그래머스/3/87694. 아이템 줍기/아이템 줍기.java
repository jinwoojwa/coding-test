import java.util.*;

class Solution {
    
    boolean[][] map = new boolean[101][101];
    boolean[][] inside = new boolean[101][101];
    
    int[] dx = {0, 1, 0, -1}; // → ↓ ← ↑
    int[] dy = {1, 0, -1, 0};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        for (int[] r : rectangle) {
            int x1 = r[0] * 2;
            int y1 = r[1] * 2;
            int x2 = r[2] * 2;
            int y2 = r[3] * 2;

            for (int x = x1; x <= x2; ++x) {
                for (int y = y1; y <= y2; ++y) {
                    map[x][y] = true;
                }
            }
            
            for (int x = x1 + 1; x < x2; ++x) {
                for (int y = y1 + 1; y < y2; ++y) {
                    inside[x][y] = true;
                }
            }
        }
        
        int[][] dist = new int[101][101];
        for (int i = 0; i < 101; ++i) Arrays.fill(dist[i], -1);
        
        Queue<int[]> q = new ArrayDeque<>();

        int startX = characterX * 2;
        int startY = characterY * 2;
        
        q.offer(new int[]{startX, startY});
        dist[startX][startY] = 0;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            
            if (x == itemX * 2 && y == itemY * 2) {
                return dist[x][y] / 2;
            }

            for (int dir = 0; dir < 4; ++dir) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx < 0 || ny < 0 || nx > 100 || ny > 100) continue;
                if (dist[nx][ny] != -1 || !map[nx][ny]) continue;
                if (inside[nx][ny]) continue;

                dist[nx][ny] = dist[x][y] + 1;
                q.offer(new int[]{nx, ny});
            }
        }
        
        return -1;
    }
}