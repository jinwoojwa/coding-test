class Solution {
    public int solution(int[][] signals) {
        
        int maxTimes = 1;
        int[] eachTotalSignal = new int[signals.length];
        for (int i = 0; i < signals.length; ++i) {
            int totalTime = signals[i][0] + signals[i][1] + signals[i][2];
            eachTotalSignal[i] = totalTime;
            maxTimes *= totalTime;
        }
        
        for (int time = 1; time <= maxTimes; ++time) {
            boolean flag = true;
            for (int i = 0; i < signals.length; ++i) {
                int signalState = time % eachTotalSignal[i];
                if (!(signals[i][0] < signalState 
                      && signalState < signals[i][0] + signals[i][1] + 1)) {
                    flag = false;
                    break;
                }
            }
            if (flag) return time;
        }
        
        return -1;
    }
}

// 현재 시간을 각 신호등마다 전체 주기로 나눈 나머지 = 각 신호등 별 신호