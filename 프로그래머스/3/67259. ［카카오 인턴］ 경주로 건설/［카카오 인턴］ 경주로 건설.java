import java.util.*;

class Solution {
    int n;
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    
    static final int STRAIGHT = 100;
    static final int CORNER = 500;
    static final int BIG = Integer.MAX_VALUE;
    
    class Move {
        int d;
        int x;
        int y;
        int cost;
        
        Move(int d, int x, int y, int cost) {
            this.d = d;
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
    
    public int solution(int[][] board) {
        n = board.length;
        
        int[][] costMap = new int[n][n];
        int[][][] cost = new int[n][n][4];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(cost[i][j], BIG);
            }
        }
        
        Queue<Move> q = new ArrayDeque<>();
        q.offer(new Move(-1, 0, 0, 0));
        
        while (!q.isEmpty()) {
            Move cur = q.poll();
            
            for (int dir = 0; dir < 4; ++dir) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if (board[nx][ny] == 1) continue;
                
                int nextCost = cur.cost + STRAIGHT;
                
                // 방향이 바뀌면 코너 비용 추가
                if (cur.d != -1 && cur.d != dir) {
                    nextCost += CORNER;
                }
                
                if (nextCost >= cost[nx][ny][dir]) continue;

                cost[nx][ny][dir] = nextCost;
                q.offer(new Move(dir, nx, ny, nextCost));
            }
        }
        int answer = BIG;

        for (int dir = 0; dir < 4; dir++) {
            answer = Math.min(answer, cost[n - 1][n - 1][dir]);
        }

        return answer;
    }
}