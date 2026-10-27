import java.util.*;

class Solution {
    
    int n, m;
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    String[] board;
    
    class Position {
        int x;
        int y;
        
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int solution(String[] board) {
        this.n = board.length;
        this.m = board[0].length();
        this.board = board;
        
        Position robot = null;
        Position destination = null;
        
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                char c = board[i].charAt(j);

                if (c == 'R') robot = new Position(i, j);
                else if (c == 'G') destination = new Position(i, j);
            }
        }
        
        Queue<Position> q = new ArrayDeque<>();
        
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dist[i], -1);
        }
        
        q.offer(robot);
        dist[robot.x][robot.y] = 0;
        
        while (!q.isEmpty()) {
            Position cur = q.poll();
            
            if (cur.x == destination.x && cur.y == destination.y) return dist[cur.x][cur.y];
            
            for (int dir = 0; dir < 4; ++dir) {
                Position next = new Position(cur.x, cur.y);
                
                // 4방향으로 부딪힐 때까지 이동
                while (canMove(next, dir)) {
                    next.x += dx[dir];
                    next.y += dy[dir];
                }
                
                if (dist[next.x][next.y] >= 0) continue;
                
                dist[next.x][next.y] = dist[cur.x][cur.y] + 1;
                q.offer(next);
            }
        }
        
        return -1;
    }
    
    private boolean canMove(Position pos, int dir) {
        int nx = pos.x + dx[dir];
        int ny = pos.y + dy[dir];

        if (nx < 0 || ny < 0 || nx >= n || ny >= m) return false;
        if (board[nx].charAt(ny) == 'D') return false;
        
        return true;
    }
}












