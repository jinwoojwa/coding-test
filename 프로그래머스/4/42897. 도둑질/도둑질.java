class Solution {
    public int solution(int[] money) {
        int n = money.length;
        
        int case1 = rob(money, 0, n - 2);
        int case2 = rob(money, 1, n - 1);
        
        return Math.max(case1, case2);
    }
    
    private int rob(int[] money, int st, int end) {
        int prev1 = 0, prev2 = 0;

        for (int i = st; i <= end; i++) {
            int skip = prev1; // i 번째 안 터는 경우
            int take = prev2 + money[i]; // i 번째 터는 경우

            int current = Math.max(skip, take);

            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}

// 아니 근데 경보가 울려도 되는건지(울릴 때까지 돈의 총합) 아니면 아예 경보가 울리면 안되는건지(울리면 0원)
// i번째 집까지 털었을 때의 최대값을 구하려면, i-1번째 집을 턴 경우 vs i-2번째 집을 털고 i번째 집을 터는 경우를 비교해야 함