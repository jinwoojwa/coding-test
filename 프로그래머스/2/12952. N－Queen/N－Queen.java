import java.util.*;

class Solution {
    int n;
    int answer = 0;
    
    public int solution(int n) {
        this.n = n;
        
        int[] queen = new int[n];
        Arrays.fill(queen, -1);
        
        dfs(0, queen);
        
        return answer;
    }
    
    private void dfs(int row, int[] queen) {
        if (row == n) {
            answer++;
            return;
        }
        
        for (int i = 0; i < n; ++i) {
            boolean possible = true;
            for (int j = 0; j < row; ++j) {
                if (queen[j] == i) {
                    possible = false;
                    break;
                }
                if (row - j == Math.abs(i - queen[j])) {
                    possible = false;
                    break;
                }
            }
            
            if (!possible) continue;
            
            queen[row] = i;
            dfs(row + 1, queen);
            queen[row] = -1;
        }
    }
}