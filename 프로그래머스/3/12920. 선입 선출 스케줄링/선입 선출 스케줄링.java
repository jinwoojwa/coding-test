class Solution {
    public int solution(int n, int[] cores) {
        if (n <= cores.length) return n;
        
        long left = 1L;
        long right = 500_000_001L;
        
        while (left < right) {
            long mid = left + (right - left) / 2;
            long processCnt = cores.length;
            
            for (int core : cores) {
                processCnt += mid / core;
            }
            
            if (processCnt >= n) right = mid;
            else left = mid + 1;
        }
        
        // 현재 left는 n개의 작업을 처리할 수 있는 최소 시간
        // 따라서 left - 1 시간에 처리할 수 있는 작업의 수를 구하고,
        // left초에 수행 가능한 코어를 줄 세워서 구하면 될 듯?
        
        long processCnt = cores.length;
        for (int core : cores) {
            processCnt += (left - 1) / core;
        }
        
        for (int i = 0; i < cores.length; ++i) {
            if (left % cores[i] != 0) continue;
            
            processCnt++;
            if (processCnt == n) return i + 1;
        }
        return -1;
    }
}

/*
- 처리해야 하는 일의 개수는 최대 5만개
- 코어의 수는 최대 1만개
- 작업을 처리하는 시간은 10000 이하
-> 시간을 늘려가면서 하나하나 처리하면 무조건 시간 제한에 걸림 (시간을 1씩 늘린다고 치면 최대 5만 x 1만 시간의 반복문 돌려야함)

시간 제한 줄이는 풀이 -> dp, 이분 탐색

누가 마지막 작업을 처리할 것인지? -> 코어가 작업을 수행하는 시간이 중요
마지막 작업은 언제 시작함? -> 시간으로 하는게 맞나?

dp로 풀 수 있나? -> 안될 것 같음. 애초에 선택지가 있는게 아님. 걍 처리 순서는 정해져 있음
이분탐색??? -> 어케 적용함...
다른 풀이?

10s:
1 -> 11
2 -> 6
3 -> 4
=> 10초에 22개 처리 가능

5s:
1 -> 6
2 -> 3
3 -> 2
=> 5초에 11개 처리 가능

2s:
1 -> 3
2 -> 2
3 -> 1
=> 2초에 6개 처리 가능
*/