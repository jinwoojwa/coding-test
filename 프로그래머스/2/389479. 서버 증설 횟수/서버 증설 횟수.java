import java.util.*;

class Solution {
    
    class Server {
        int shutdownTime;
        
        Server(int shutdownTime) {
            this.shutdownTime = shutdownTime;
        }
    }
    
    public int solution(int[] players, int m, int k) {
        
        Queue<Server> serverQ = new ArrayDeque<>();
        int curServerCnt = 1;
        int expansionCnt = 0;
        
        for (int time = 0; time <= 23; ++time) {
            int users = players[time];
            
            System.out.println("TIME: " + time + " ~ " + (time + 1) + " USERS: " + users);
            
            // 현재 시간대에 끝나는 서버 제거하기
            while (!serverQ.isEmpty() && serverQ.peek().shutdownTime <= time) {
                serverQ.poll();
                curServerCnt--;
            }
            
            System.out.println("[SYSTEM]SERVER: " + curServerCnt);
            
            if (users < m) {
                System.out.println();
                continue;
            }
            
            // 현재 서버로 유저 감당 가능한 지 체크
            while (curServerCnt * m <= users) {
                curServerCnt++;
                serverQ.offer(new Server(time + k));
                expansionCnt++;
                System.out.println("SERVER: " + curServerCnt + " EXPANSION: " + expansionCnt);
            }
            System.out.println();
        }
        return expansionCnt;
    }
}