import java.util.*;

class Solution {

    public int[] solution(int target) {

        int[][] dp = new int[target + 1][2];

        for (int i = 1; i <= target; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }

        List<int[]> scores = new ArrayList<>();

        // 싱글
        for (int i = 1; i <= 20; i++) {
            scores.add(new int[]{i, 1});
        }

        // 더블
        for (int i = 1; i <= 20; i++) {
            scores.add(new int[]{i * 2, 0});
        }

        // 트리플
        for (int i = 1; i <= 20; i++) {
            scores.add(new int[]{i * 3, 0});
        }

        // 불
        scores.add(new int[]{50, 1});

        for (int i = 1; i <= target; i++) {
            for (int[] scoreInfo : scores) {
                int score = scoreInfo[0];
                int singleBull = scoreInfo[1];

                if (i < score) continue;

                int throwCnt = dp[i - score][0] + 1;
                int singleBullCnt = dp[i - score][1] + singleBull;

                // 더 적은 다트 수
                if (throwCnt < dp[i][0]) {
                    dp[i][0] = throwCnt;
                    dp[i][1] = singleBullCnt;
                }
                // 다트 수가 같으면 싱글/불 개수 최대
                else if (throwCnt == dp[i][0] && singleBullCnt > dp[i][1]) {
                    dp[i][1] = singleBullCnt;
                }
            }
        }

        return new int[]{dp[target][0], dp[target][1]};
    }
}