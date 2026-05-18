class Solution {
    
    int res = -1;
    int n;
    
    public int solution(int k, int[][] dungeons) {
        n = dungeons.length;
        boolean[] isClear = new boolean[n];
        
        dfs(k, dungeons, 0, isClear);
        return res;
    }
    
    private void dfs(int curFatigue, int[][] dungeons, int cnt, boolean[] isClear) {
        
        res = Math.max(res, cnt);
        
        for (int i = 0; i < n; ++i) {
            if (isClear[i]) continue;
            if (curFatigue < dungeons[i][0]) continue;
            
            isClear[i] = true;
            dfs(curFatigue - dungeons[i][1], dungeons, cnt + 1, isClear);
            isClear[i] = false;
        }
    }
}