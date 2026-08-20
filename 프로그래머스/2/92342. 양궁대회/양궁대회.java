class Solution {
    
    private static int maxGap = 0;
    private static int[] answer = {-1};
    
    public int[] solution(int n, int[] info) {
        int[] lion = new int[11];
        
        shoot(0, n, info, lion);
        
        return answer;
    }
    
    private void shoot(int idx, int arrow, int[] info, int[] lion) {
        if (idx == 10) {
            
            lion[10] = arrow;
            updateAnswer(info, lion);
            lion[10] = 0;
            
            return;
        }
    
        int score = 10 - idx;    
        int need = info[idx] + 1;
        
        // 화살을 쏜 경우
        if (arrow >= need) {
            lion[idx] = need;
            shoot(idx + 1, arrow - need, info, lion);
            lion[idx] = 0;
        }
        
        // 화살을 안 쏜 경우
        lion[idx] = 0;
        shoot(idx + 1, arrow, info, lion);
    }
    
    private void updateAnswer(int[] info, int[] lion) {
        int apeachScore = 0;
        int lionScore = 0;
        
        for (int i = 0; i < 11; ++i) {
            int score = 10 - i;
            
            if (info[i] == 0 && lion[i] == 0) continue;
            if (info[i] >= lion[i]) apeachScore += score;
            else lionScore += score;
        }
        int gap = lionScore - apeachScore;
        
        if (gap <= 0) return;
        
        if (gap > maxGap) {
            maxGap = gap;
            answer = lion.clone();
            return;
        }
        
        if (gap == maxGap && isGood(lion, answer)) {
            answer = lion.clone();
        }
    }
    
    private boolean isGood(int[] lion, int[] cur) {
        for (int i = 10; i >= 0; --i) {
            if (lion[i] > cur[i]) return true;
            if (lion[i] < cur[i]) return false;
        }
        return false;
    }
}
