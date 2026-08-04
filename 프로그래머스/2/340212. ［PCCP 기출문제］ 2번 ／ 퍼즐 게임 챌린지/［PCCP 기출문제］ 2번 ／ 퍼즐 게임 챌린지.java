class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int lvLeft = 1;
        int lvRight = 100_000;
        int lvMid = 0;
        
        while (lvLeft <= lvRight) {
            lvMid = (lvLeft + lvRight) / 2;
            
            if (!isPossibleToSolvePuzzles(diffs, times, lvMid, limit)) lvLeft = lvMid + 1;
            else lvRight = lvMid - 1;
        }
        return lvLeft;
    }
    
    private boolean isPossibleToSolvePuzzles(int[] diffs, int[] times, int level, long limit) {
        long solveTime = 0;
        for (int i = 0; i < diffs.length; ++i) {
            if (diffs[i] <= level) solveTime += times[i];
            else solveTime += (times[i] + times[i - 1]) * (diffs[i] - level) + times[i];
        }
        return (solveTime <= limit) ? true : false;
    }
}

// 퍼즐 난이도 <= 숙련도 이면 time_cur 만큼의 시간으로 풀림
// 퍼즐 난이도 > 숙련도 이면 (time_cur + time_prev) * (퍼즐 난이도 - 숙련도) + time_cur 만큼의 시간 사용
// 퍼즐의 개수는 최대 30만, 숙련도는 1 ~ 10의 15승 -> 모든 숙련도를 전부 적용해볼 수 없음.
// ==> 이분 탐색