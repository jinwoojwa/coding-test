class Solution {
    public int solution(int[][] board) {
        int h = board.length;
        int w = board[0].length;
        
        int[][] dp = new int[h + 1][w + 1];
        
        int answer = 0;
        for (int row = 1; row <= h; ++row) {
            for (int col = 1; col <= w; ++col) {
                if (board[row - 1][col - 1] == 0) continue;
                
                dp[row][col] = Math.min(
                    dp[row - 1][col - 1],
                    Math.min(dp[row][col - 1], dp[row - 1][col])
                ) + 1;
                
                answer = Math.max(answer, dp[row][col]);
            }
        }
        return answer * answer;
    }
}

