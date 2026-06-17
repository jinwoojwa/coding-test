class Solution {
    public int solution(int[] cookie) {
        int maxCookieCnt = 0;
        
        for (int m = 0; m < cookie.length - 1; ++m) {
            int l = m;
            int r = m + 1;
            
            int leftSum = cookie[l];
            int rightSum = cookie[r];
            
            while (true) {
                if (leftSum == rightSum) maxCookieCnt = Math.max(maxCookieCnt, leftSum);
                
                if (l == 0 && r == cookie.length - 1) break;
                
                // 왼쪽 과자 수가 더 많은 경우
                if (leftSum >= rightSum) {
                    if (r == cookie.length - 1) break;
                    r++;
                    rightSum += cookie[r];
                }
                else {
                    if (l == 0) break;
                    l--;
                    leftSum += cookie[l];
                }
            }
        }
        return maxCookieCnt;
    }
}