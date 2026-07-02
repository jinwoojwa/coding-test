class Solution {

    int n, m;

    public int solution(int[][] board, int[][] skill) {
        n = board.length;
        m = board[0].length;

        int[][] diff = new int[n + 1][m + 1];

        applySkills(diff, skill);
        accumulate(diff);

        return countUndestroyed(board, diff);
    }

    private void applySkills(int[][] diff, int[][] skills) {
        for (int[] skill : skills) {
            int type = skill[0];
            int r1 = skill[1];
            int c1 = skill[2];
            int r2 = skill[3];
            int c2 = skill[4];
            int degree = skill[5];

            int value = (type == 1) ? -degree : degree;

            diff[r1][c1] += value;
            diff[r1][c2 + 1] -= value;
            diff[r2 + 1][c1] -= value;
            diff[r2 + 1][c2 + 1] += value;
        }
    }

    private void accumulate(int[][] diff) {
        for (int r = 0; r <= n; r++) {
            for (int c = 1; c <= m; c++) {
                diff[r][c] += diff[r][c - 1];
            }
        }

        for (int c = 0; c <= m; c++) {
            for (int r = 1; r <= n; r++) {
                diff[r][c] += diff[r - 1][c];
            }
        }
    }

    private int countUndestroyed(int[][] board, int[][] diff) {
        int count = 0;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (board[r][c] + diff[r][c] > 0) {
                    count++;
                }
            }
        }

        return count;
    }
}