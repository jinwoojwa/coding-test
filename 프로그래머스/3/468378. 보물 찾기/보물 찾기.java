import java.util.function.Function;

class Solution {
    public int solution(int[] depth, int money, Function<Integer, Integer> excavate) {
        int w = depth.length;
        
        // dp[i][j]: i열부터 j열 사이에 보물이 있다고 확신할 때, 보물을 찾기 위한 '최악의 경우 최소 비용'
        int[][] dp = new int[w + 2][w + 2];
        // opt[i][j]: i열부터 j열 사이에서 비용을 최소화하기 위해 선택해야 하는 최적의 굴착 열 번호
        int[][] opt = new int[w + 2][w + 2];
        
        for (int len = 1; len <= w; len++) {
            for (int i = 1; i <= w - len + 1; i++) {
                int j = i + len - 1;
                
                dp[i][j] = Integer.MAX_VALUE;
                
                for (int k = i; k <= j; k++) {
                    int cost = depth[k - 1] + Math.max(dp[i][k - 1], dp[k + 1][j]);
                    
                    if (cost < dp[i][j]) {
                        dp[i][j] = cost;
                        opt[i][j] = k;
                    }
                }
            }
        }
        
        int left = 1;
        int right = w;
        
        while (left <= right) {
            int mid = opt[left][right];
            
            int result = excavate.apply(mid);
            
            if (result == 0) {
                return mid; // 보물 발견
            } else if (result == 1) {
                left = mid + 1; // 보물이 오른쪽에 있음
            } else {
                right = mid - 1; // 보물이 왼쪽에 있음
            }
        }
        
        return -1;
    }
}