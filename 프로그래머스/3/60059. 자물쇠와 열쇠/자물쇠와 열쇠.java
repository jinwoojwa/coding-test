class Solution {

    int n, m, size;
    int[][] board;

    private int[][] rotate90(int[][] key) {
        int[][] rotated = new int[m][m];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < m; c++) {
                rotated[c][m - 1 - r] = key[r][c];
            }
        }
        return rotated;
    }

    public boolean solution(int[][] key, int[][] lock) {
        n = lock.length;
        m = key.length;
        size = n + 2 * m;

        board = new int[size][size];

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                board[m + r][m + c] = lock[r][c];
            }
        }

        // 0도, 90도, 180도, 270도
        for (int rotation = 0; rotation < 4; rotation++) {
            for (int r = 0; r <= size - m; r++) {
                for (int c = 0; c <= size - m; c++) {

                    // key 끼우기
                    for (int kr = 0; kr < m; kr++) {
                        for (int kc = 0; kc < m; kc++) {
                            board[r + kr][c + kc] += key[kr][kc];
                        }
                    }

                    // lock 영역이 전부 1인지 검사
                    if (isUnlock()) return true;

                    // key 빼기 (원상복구)
                    for (int kr = 0; kr < m; kr++) {
                        for (int kc = 0; kc < m; kc++) {
                            board[r + kr][c + kc] -= key[kr][kc];
                        }
                    }
                }
            }
            key = rotate90(key);
        }
        return false;
    }

    private boolean isUnlock() {
        for (int r = m; r < m + n; r++) {
            for (int c = m; c < m + n; c++) {
                if (board[r][c] != 1) {
                    return false;
                }
            }
        }
        return true;
    }
}