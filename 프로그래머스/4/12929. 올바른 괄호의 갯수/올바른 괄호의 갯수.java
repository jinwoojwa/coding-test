class Solution {
    private int res = 0;

    public int solution(int n) {
        dfs(n, 0, 0);
        return res;
    }

    private void dfs(int n, int open, int close) {
        if (open == n && close == n) {
            res++;
            return;
        }

        if (open < n) {
            dfs(n, open + 1, close);
        }

        if (close < open) {
            dfs(n, open, close + 1);
        }
    }
}