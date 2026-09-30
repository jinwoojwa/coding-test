class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[][] matrix = new int[rows][columns];

        int value = 1;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                matrix[r][c] = value++;
            }
        }

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            answer[i] = rotate(matrix, queries[i]);
        }

        return answer;
    }

    private int rotate(int[][] matrix, int[] query) {
        int x1 = query[0] - 1;
        int y1 = query[1] - 1;
        int x2 = query[2] - 1;
        int y2 = query[3] - 1;

        int min = Integer.MAX_VALUE;

        // 왼쪽 위부터 시작
        int prev = matrix[x1][y1];

        // 오른쪽 이동
        for (int c = y1 + 1; c <= y2; c++) {
            int temp = matrix[x1][c];
            matrix[x1][c] = prev;
            prev = temp;

            min = Math.min(min, matrix[x1][c]);
        }

        // 아래 이동
        for (int r = x1 + 1; r <= x2; r++) {
            int temp = matrix[r][y2];
            matrix[r][y2] = prev;
            prev = temp;

            min = Math.min(min, matrix[r][y2]);
        }

        // 왼쪽 이동
        for (int c = y2 - 1; c >= y1; c--) {
            int temp = matrix[x2][c];
            matrix[x2][c] = prev;
            prev = temp;

            min = Math.min(min, matrix[x2][c]);
        }

        // 위쪽 이동
        for (int r = x2 - 1; r >= x1; r--) {
            int temp = matrix[r][y1];
            matrix[r][y1] = prev;
            prev = temp;

            min = Math.min(min, matrix[r][y1]);
        }

        return min;
    }
}