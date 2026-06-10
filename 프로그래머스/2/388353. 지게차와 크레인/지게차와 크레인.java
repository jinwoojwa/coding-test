import java.util.*;

class Solution {
    int h, w;
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    
    public int solution(String[] storage, String[] requests) {
        h = storage.length + 2;
        w = storage[0].length() + 2;
        
        char[][] board = initBoard(storage);
        
        for (String request : requests) {
            char alphabet = request.charAt(0);
            
            if (request.length() == 2) {
                crane(board, alphabet);
            } else {
                forklift(board, alphabet);
            }      
        }
        return countRemain(board);
    }
    
    private char[][] initBoard(String[] storage) {
        char[][] board = new char[h][w];

        for (int r = 0; r < h; r++) Arrays.fill(board[r], '.');

        for (int r = 0; r < storage.length; r++) {
            for (int c = 0; c < storage[0].length(); c++) {
                board[r + 1][c + 1] = storage[r].charAt(c);
            }
        }
        return board;
    }
    
    private void crane(char[][] board, char alphabet) {
        for (int r = 1; r < h - 1; r++) {
            for (int c = 1; c < w - 1; c++) {
                if (board[r][c] == alphabet) {
                    board[r][c] = '.';
                }
            }
        }
    }
    
    private void forklift(char[][] board, char alphabet) {
        boolean[][] outside = findPath(board);
        List<int[]> removeList = new ArrayList<>();

        for (int r = 1; r < h - 1; r++) {
            for (int c = 1; c < w - 1; c++) {
                if (board[r][c] != alphabet) continue;

                for (int d = 0; d < 4; d++) {
                    int nr = r + dx[d];
                    int nc = c + dy[d];

                    if (outside[nr][nc]) {
                        removeList.add(new int[]{r, c});
                        break;
                    }
                }
            }
        }
        for (int[] pos : removeList) {
            board[pos[0]][pos[1]] = '.';
        }
    }
    
    private boolean[][] findPath(char[][] board) {
        boolean[][] visited = new boolean[h][w];

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int x = cur[0];
            int y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
                if (visited[nx][ny]) continue;
                if (board[nx][ny] != '.') continue;

                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }
        return visited;
    }

    private int countRemain(char[][] board) {
        int count = 0;

        for (int r = 1; r < h - 1; r++) {
            for (int c = 1; c < w - 1; c++) {
                if (board[r][c] != '.') count++;
            }
        }
        return count;
    }
}