class Solution {
    
    static int n;
    static int res;
    
    public void dfs(int stage, int[] ticket, int curCost, int[][] cost, int[][] hint) {
        if (stage == n + 1) {
            res = Math.min(res, curCost);
            return;
        }
        
        int ticketCnt = ticket[stage];
        ticketCnt = Math.min(ticketCnt, cost[stage - 1].length - 1);
        int baseCost = curCost + cost[stage - 1][ticketCnt];
        
        if (stage == n) {
            dfs(stage + 1, ticket, baseCost, cost, hint);
            return;
        }
        
        // i번 스테이지에서 판매하는 힌트 번들을 사지 않는 경우
        dfs(stage + 1, ticket, baseCost, cost, hint);
        
        // 힌트 번들을 사는 경우
        int buyCost = baseCost + hint[stage - 1][0];
        for (int t = 1; t < hint[stage - 1].length; ++t) {
            int hintNum = hint[stage - 1][t];
            ticket[hintNum]++;
        }
        dfs(stage + 1, ticket, buyCost, cost, hint);
        
        for (int t = 1; t < hint[stage - 1].length; ++t) {
            int hintNum = hint[stage - 1][t];
            ticket[hintNum]--;
        }
    }
    
    public int solution(int[][] cost, int[][] hint) {
        res = Integer.MAX_VALUE;
        n = cost.length;
        
        int[] ticket = new int[n + 1];
        
        dfs(1, ticket, 0, cost, hint);
        
        return res;
    }
}