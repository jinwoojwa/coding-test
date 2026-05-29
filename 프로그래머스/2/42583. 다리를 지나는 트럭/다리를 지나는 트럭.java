import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<int[]> q = new ArrayDeque<>(); // {트럭 무게, 다리에 진입한 시간}
        
        int curTime = 1;
        int idx = 0;
        int curWeight = 0;
        while (true) {
            // 트럭이 다리를 지날 수 있는가?
            if (!q.isEmpty() && curTime - q.peek()[1] == bridge_length) {
                int[] curTruck = q.poll();
                
                curWeight -= curTruck[0];
            }
            
            // 트럭이 다리에 올라갈 수 있는가?
            if (q.size() < bridge_length && truck_weights[idx] + curWeight <= weight) {
                curWeight += truck_weights[idx];
                q.offer(new int[]{truck_weights[idx++], curTime});
                if (idx >= truck_weights.length) break;
            }
            curTime++;
        }
        
        return curTime + bridge_length;
    }
}