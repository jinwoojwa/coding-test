class Solution {
    
    int res = 0;
    int n, target;
    int[] numbers;
    
    public int solution(int[] numbers, int target) {
        n = numbers.length;
        this.target = target;
        this.numbers = numbers;
        
        dfs(0, 0, 0);
        
        return res;
    }
    
    private void dfs(int idx, int sum, int cnt) {
        if (cnt == n) {
            if (sum == target) res++;
            return;
        }
        
        for (int i = idx; i < n; ++i) {
            dfs(i + 1, sum + numbers[i], cnt + 1);
            dfs(i + 1, sum - numbers[i], cnt + 1);
        }
    }
    
}