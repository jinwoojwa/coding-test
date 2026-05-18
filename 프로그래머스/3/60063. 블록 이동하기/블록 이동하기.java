import java.util.*;

class Solution {
    
    static int n;
    static int[][] rotateX = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int[][] rotateY = {{1, 0}, {0, -1}, {1, 0}, {0, -1}};
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    
    static class Node {
        int x, y;
        int dir; // 0->가로, 1->세로
        int dist;
        
        Node(int x, int y, int dir, int dist) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.dist = dist;
        }
    }
    
    public int solution(int[][] board) {
        n = board.length;
        
        return bfs(board);
    }
    
    private int bfs(int[][] board) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[n][n][2]; // x, y, dir
        
        q.offer(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;
        
        while (!q.isEmpty()) {
            Node cur = q.poll();

            int x = cur.x;
            int y = cur.y;
            int dir = cur.dir;

            if ((dir == 0 && x == n - 1 && y + 1 == n - 1)
                || (dir == 1 && x + 1 == n - 1 && y == n - 1)) {
                return cur.dist;
            }

            for (Node nxt : getNext(cur, board)) {
                if (!visited[nxt.x][nxt.y][nxt.dir]) {
                    visited[nxt.x][nxt.y][nxt.dir] = true;
                    q.offer(nxt);
                }
            }
        }
        return 0;
    }
    
    private List<Node> getNext(Node cur, int[][] board) {
        List<Node> next = new ArrayList<>();

        int x = cur.x, y = cur.y;
        int dir = cur.dir;

        int x2 = x + (dir == 1 ? 1 : 0);
        int y2 = y + (dir == 0 ? 1 : 0);

        // 이동
        for (int d = 0; d < 4; d++) {
            int nx1 = x + dx[d], ny1 = y + dy[d];
            int nx2 = x2 + dx[d], ny2 = y2 + dy[d];

            if (isValid(nx1, ny1) && isValid(nx2, ny2)
                && board[nx1][ny1] == 0
                && board[nx2][ny2] == 0) {

                next.add(new Node(nx1, ny1, dir, cur.dist + 1));
            }
        }

        // 회전
        if (dir == 0) { // 가로
            // 아래 회전
            if (isValid(x + 1, y)
                && isValid(x + 1, y + 1)
                && board[x + 1][y] == 0
                && board[x + 1][y + 1] == 0) {

                next.add(new Node(x, y, 1, cur.dist + 1));
                next.add(new Node(x, y + 1, 1, cur.dist + 1));
            }

            // 위 회전
            if (isValid(x - 1, y)
                && isValid(x - 1, y + 1)
                && board[x - 1][y] == 0
                && board[x - 1][y + 1] == 0) {

                next.add(new Node(x - 1, y, 1, cur.dist + 1));
                next.add(new Node(x - 1, y + 1, 1, cur.dist + 1));
            }

        } else { // 세로

            // 오른쪽 회전
            if (isValid(x, y + 1)
                && isValid(x + 1, y + 1)
                && board[x][y + 1] == 0
                && board[x + 1][y + 1] == 0) {

                next.add(new Node(x, y, 0, cur.dist + 1));
                next.add(new Node(x + 1, y, 0, cur.dist + 1));
            }

            // 왼쪽 회전
            if (isValid(x, y - 1)
                && isValid(x + 1, y - 1)
                && board[x][y - 1] == 0
                && board[x + 1][y - 1] == 0) {

                next.add(new Node(x, y - 1, 0, cur.dist + 1));
                next.add(new Node(x + 1, y - 1, 0, cur.dist + 1));
            }
        }
        return next;
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }
}

/*
로봇이 1초에 할 수 있는 일

dir=0 -> (a, b), (a, b + 1)
dir=1 -> (a, b), (a + 1, b)

1. 회전 (4방향)
    - (a + 1, b + 1), (a, b + 1) : (a + 1, b) != 1
    - (a, b), (a + 1, b) : (a + 1, b + 1) != 1
    - (a - 1, b + 1), (a, b + 1) 
    - (a, b), (a - 1, b)
    
2. 이동 (4방향 - 상하좌우)
    - (a - 1, b), (c - 1, d)
    - (a + 1, b), (c + 1, d)
    - (a, b - 1), (c, d - 1)
    - (a, b + 1), (c, d + 1)
*/