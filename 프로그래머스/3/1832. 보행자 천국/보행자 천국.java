import java.util.*;

class Solution {
    private final int MOD = 20170805;
    
    public int solution(int m, int n, int[][] cityMap) {
        int[][] dpRight = new int[m][n];
        int[][] dpDown = new int[m][n];
        
        dpRight[0][0] = 1;
        
        for (int x = 0; x < m; ++x) {
            for (int y = 0; y < n; ++y) {
                if (x == 0 && y == 0) continue;

                // 자동차 못감
                if (cityMap[x][y] == 1) continue;

                // 왼쪽 -> 오른쪽
                if (y > 0 && cityMap[x][y - 1] != 1) {
                    if (cityMap[x][y - 1] == 2) {
                        dpRight[x][y] = dpRight[x][y - 1];
                    } else {
                        dpRight[x][y] = (dpRight[x][y - 1] + dpDown[x][y - 1]) % MOD;
                    }
                }

                // 위쪽 -> 아래쪽
                if (x > 0 && cityMap[x - 1][y] != 1) {
                    if (cityMap[x - 1][y] == 2) {
                        dpDown[x][y] = dpDown[x - 1][y];
                    } else {
                        dpDown[x][y] = (dpRight[x - 1][y] + dpDown[x - 1][y]) % MOD;
                    }
                }
            }
        }
        return (dpRight[m - 1][n - 1] + dpDown[m - 1][n - 1]) % MOD;
    }
}