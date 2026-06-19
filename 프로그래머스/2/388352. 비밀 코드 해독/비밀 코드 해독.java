import java.util.*;

class Solution {
    int n, answer;
    int[][] q;
    int[] ans;
    Set<Integer> selected = new HashSet<>();
    public int solution(int n, int[][] q, int[] ans) {
        this.n = n;
        this.q = q;
        this.ans = ans;
        
        dfs(1, 0);
        
        return answer;
    }
    
    private void dfs(int start, int cnt) {
        if (cnt == 5) {
            if (isPossible()) answer++;
            return;
        }
        
        for (int i = start; i <= n; ++i) {
            selected.add(i);
            dfs(i  + 1, cnt + 1);
            selected.remove(i);
        }
    }
    
    private boolean isPossible() {
        for (int i = 0; i < q.length; ++i) {
            int count = 0;
            for (int n : q[i]) {
                if (selected.contains(n)) count++;
            }
            if (count != ans[i]) return false;
        }
        return true;
    }
}

// (1,2,3,4,5) ~ (?,?,?,?,n) 까지 모든 경우를 q의 코드 순서대로 대조 30C5의 경우의 수
// 마지막 q의 ans까지 맞다면 가능한 비밀 코드임