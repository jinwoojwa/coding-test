import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        
        Queue<Integer> serverQ = new ArrayDeque<>();
        int runningServers = 1;
        int expansionCnt = 0;
        
        for (int time = 0; time <= 23; ++time) {
            int users = players[time];
            
            // 현재 시간대에 끝나는 서버 제거하기
            while (!serverQ.isEmpty() && serverQ.peek() <= time) {
                serverQ.poll();
                runningServers--;
            }
            
            if (users < m) continue;
            
            // 현재 서버로 유저 감당 가능한 지 체크
            while (runningServers * m <= users) {
                runningServers++;
                serverQ.offer(time + k);
                expansionCnt++;
            }
        }
        return expansionCnt;
    }
}