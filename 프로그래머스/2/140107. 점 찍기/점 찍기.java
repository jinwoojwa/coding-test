class Solution {
    public long solution(int k, int d) {
        long dd = 1L * d * d;
        
        long dotCnt = 0;
        for (int y = 0; y <= d; y += k) {
            long xx = dd - 1L * y * y;
            long maxX = (long) Math.sqrt(xx);
            
            dotCnt += maxX / k + 1;
        }
        return dotCnt;
    }
}

// k가 양의 정수이고, a, b가 0부터 1씩 증가하는 정수이므로 축과 1사분면만 구하면 됨
// y축을 기준으로 잡고(반대도 가능) 해당 y축에서 최대 x 좌표를 구함
// d^2 = x^2 + y^2