import java.util.*;

class Solution {
    record Score(int point, int singleOrBull) {}
    
    public int[] solution(int target) {
        int[][] dp = new int[target + 1][2];
        
        for (int i = 1; i <= target; ++i) {
            dp[i][0] = 100001;
        }
        
        List<Score> scores = getScores();
        
        for (int i = 1; i <= target; ++i) {
            for (Score score : scores) {
                if (i < score.point) continue;
                
                int throwCnt = dp[i - score.point][0] + 1;
                int singleOrBullCnt = dp[i - score.point][1] + score.singleOrBull;
                
                if (throwCnt < dp[i][0]) {
                    dp[i][0] = throwCnt;
                    dp[i][1] = singleOrBullCnt;
                }
                else if (throwCnt == dp[i][0] && singleOrBullCnt > dp[i][1]) {
                    dp[i][1] = singleOrBullCnt;
                }
            }
        }
        return new int[]{dp[target][0], dp[target][1]};
    }
    
    private List<Score> getScores() {
        List<Score> scores = new ArrayList<>();
        
        for (int i = 1; i <= 20; ++i) {
            // 싱글
            scores.add(new Score(i, 1));
            
            // 더블
            scores.add(new Score(i * 2, 0));
            
            // 트리플
            scores.add(new Score(i * 3, 0));
        }
        // 불
        scores.add(new Score(50, 1));
        
        return scores;
    }
}