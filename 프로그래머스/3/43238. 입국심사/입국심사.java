class Solution {
    public long solution(int n, int[] times) {        
        long start = 0;
        long end = Long.MAX_VALUE;
    
        while (start < end) {
            long mid = start + (end - start) / 2;
            
            if (isFinish(mid, n, times)) end = mid;
            else start = mid + 1;
        }
        
        return start;
    }
    
    private boolean isFinish(long time, int n, int[] times) {
        long cnt = 0;
        for (int immigrationOfficer : times) {
            if (cnt >= n) return true; 
            cnt += time / immigrationOfficer;
        }
        return (cnt >= n) ? true : false;
    }
}

// 비어있는 심사대가 있어도 조금 기다렸다가 받는 시간이 더 빠르다면 기다렸다가 다른 심사대에서 받아야 함.
// times.length 보다 더 많은 사람이 남았을 때는 그냥 위의 경우는 없음
// 남은 사람이 심사대 길이 이하일 때만 위의 조건을 만족해야 함.