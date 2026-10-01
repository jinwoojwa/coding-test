class Solution {
    public int solution(int[][] info, int n, int m) {
        int len = info.length;

        // i개의 물건을 처리했을 때, A의 흔적이 a, B의 흔적이 b가 되는 경우가 존재함?
        boolean[][][] dp = new boolean[len + 1][n][m]; // i, a, b

        dp[0][0][0] = true;

        for (int i = 0; i < len; i++) {
            int a = info[i][0];
            int b = info[i][1];

            for (int traceA = 0; traceA < n; ++traceA) {
                for (int traceB = 0; traceB < m; ++traceB) {
                    if (!dp[i][traceA][traceB]) continue;

                    // A가 훔침
                    if (traceA + a < n) {
                        dp[i + 1][traceA + a][traceB] = true;
                    }

                    // B가 훔침
                    if (traceB + b < m) {
                        dp[i + 1][traceA][traceB + b] = true;
                    }
                }
            }
        }

        for (int traceA = 0; traceA < n; ++traceA) {
            for (int traceB = 0; traceB < m; ++traceB) {
                if (dp[len][traceA][traceB]) {
                    return traceA;
                }
            }
        }
        return -1;
    }
}