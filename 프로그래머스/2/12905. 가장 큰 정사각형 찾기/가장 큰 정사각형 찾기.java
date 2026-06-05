class Solution {
    public int solution(int[][] board) {
        int h = board.length;
        int w = board[0].length;
        
        int[][] dp = new int[h][w];
        
        for (int row = 0; row < h; ++row) {
            if (board[row][0] == 0) continue;
            dp[row][0] = 1;
        }
        
        for (int col = 0; col < w; ++col) {
            if (board[0][col] == 0) continue;
            dp[0][col] = 1;
        }
        
        for (int row = 1; row < h; ++row) {
            for (int col = 1; col < w; ++col) {
                if (board[row][col] == 0) continue;
                int value = Math.min(
                    dp[row - 1][col - 1],
                    Math.min(dp[row][col - 1], dp[row - 1][col]));
                
                dp[row][col] = value + 1;
            }
        }
        
        int answer = 0;
        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                answer = Math.max(answer, dp[i][j]);
            }
        }
        

        return answer * answer;
    }
}

