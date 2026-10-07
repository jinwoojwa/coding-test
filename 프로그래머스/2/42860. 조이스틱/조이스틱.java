class Solution {
    
    static int answer = Integer.MAX_VALUE;
    static int len;
    
    public int solution(String name) {
        len = name.length();
        boolean[] isFinish = new boolean[len];
        for (int i = 0 ; i < len; ++i) {
            if (name.charAt(i) == 'A') isFinish[i] = true;
        }
        
        dfs(name, 0, 0, isFinish);
        
        return answer;
    }
    
    private void dfs(String name, int cur, int ctl, boolean[] isFinish) {
        ctl += changeAlphabet(name.charAt(cur));
        
        isFinish[cur] = true;
        
        if (isAllFinish(isFinish)) {
            answer = Math.min(answer, ctl);
            isFinish[cur] = false;
            return;
        }
        
        int[] left = findNearest(cur, 0, isFinish);
        int[] right = findNearest(cur, 1, isFinish);
        
        dfs(name, left[0], ctl + left[1], isFinish);
        dfs(name, right[0], ctl + right[1], isFinish);
        
        isFinish[cur] = false;
    }

    private int[] findNearest(int cur, int dir, boolean[] isFinish) {
        int cnt = 0;
        if (dir == 0) { // 왼쪽
            do {
                cur = (cur - 1 + len) % len;
                cnt++;
            }
            while (isFinish[cur]);
        }
        else {
            do {
                cur = (cur + 1) % len;
                cnt++;
            }
            while (isFinish[cur]);
        }
        return new int[]{cur, cnt};
    }
    
    private int changeAlphabet(char c) {
        return Math.min(c - 'A', 'Z' - c + 1);
    }
    
    private boolean isAllFinish(boolean[] isFinish) {
        for (boolean b : isFinish) {
            if (!b) return false;
        }
        return true;
    }
}