class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;

        long r1Sq = 1L * r1 * r1;
        long r2Sq = 1L * r2 * r2;

        for (long y = 1; y <= r2; y++) {
            long maxX = (long) Math.sqrt(r2Sq - y * y);

            long minX;
            if (y >= r1) {
                minX = 0;
            } else {
                minX = (long) Math.ceil(
                        Math.sqrt(r1Sq - y * y)
                );
            }

            answer += maxX - minX + 1;
        }

        return answer * 4;
    }
}