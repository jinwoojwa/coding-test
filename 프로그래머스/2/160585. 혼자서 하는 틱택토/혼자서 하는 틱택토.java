class Solution {
    public int solution(String[] board) {
        int oCnt = 0, xCnt = 0;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (board[i].charAt(j) == 'O') oCnt++;
                else if (board[i].charAt(j) == 'X') xCnt++;
            }
        }
        
        if (xCnt > oCnt) return 0;
        if (oCnt - xCnt > 1) return 0;
        if (oCnt > xCnt && isWin(board, 'X')) return 0;
        if (xCnt == oCnt) {
            if (isWin(board, 'O')) return 0;
        }
        
        return 1;
    }
    
    private boolean isWin(String[] board, char c) {
        // 가로
        for (int row = 0; row < 3; ++row) {
            if (board[row].charAt(0) == c && 
                board[row].charAt(1) == c && 
                board[row].charAt(2) == c
            ) {
                return true;
            }
        }
        // 세로
        for (int col = 0; col < 3; ++col) {
            if (board[0].charAt(col) == c &&
                board[1].charAt(col) == c &&
                board[2].charAt(col) == c
            ) {
                return true;
            }
        }
        // 대각선
        if (board[0].charAt(0) == c &&
            board[1].charAt(1) == c &&
            board[2].charAt(2) == c
        ) {
            return true;
        }
        if (board[0].charAt(2) == c &&
            board[1].charAt(1) == c &&
            board[2].charAt(0) == c
        ) {
            return true;
        }
        return false;
    }
}

/*
O, X 개수 확인
-> 다름 -> O가 많아야 함 (같거나 1차이)
-> 같음 -> 둘 다 빙고이면 안됨
*/