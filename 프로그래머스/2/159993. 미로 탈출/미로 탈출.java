import java.util.*;

class Solution {
    
    static int n, m;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    
    static class Point {
        int x;
        int y;
        int dist;
        
        Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    
    static Point findPoint(String[] maps, char target) {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (maps[i].charAt(j) == target) {
                    return new Point(i, j, 0);
                }
             }
        }
        return null;
    }
    
    public int solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        
        Point start = findPoint(maps, 'S');
        Point lever = findPoint(maps, 'L');
        
        int startToLever = bfs(start, 'L', maps);
        if (startToLever == -1) return -1;
        
        int leverToExit = bfs(lever, 'E', maps);
        if (leverToExit == -1) return -1;
        
        return startToLever + leverToExit;
    }
    
    static int bfs(Point st, char target, String[] maps) {
        boolean[][] visited = new boolean[n][m];
        Queue<Point> q = new ArrayDeque<>();
        
        q.offer(st);
        visited[st.x][st.y] = true;
        
        while (!q.isEmpty()) {
            Point cur = q.poll();
            
            if (maps[cur.x].charAt(cur.y) == target) return cur.dist;
            
            for (int dir = 0; dir < 4; ++dir) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (visited[nx][ny] || maps[nx].charAt(ny) == 'X') continue;
                
                q.offer(new Point(nx, ny, cur.dist + 1));
                visited[nx][ny] = true;
            }
        }
        return -1;
    }
}