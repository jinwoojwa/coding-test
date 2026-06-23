import java.util.*;

class Solution {
    public int solution(int[] food_times, long k) {
        long total = 0;
        for (int time : food_times) {
            total += time;
        }

        if (total <= k) {
            return -1;
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>( // {time, idx}
            Comparator.comparingInt(arr -> arr[0])
        );
        
        for (int idx = 0; idx < food_times.length; ++idx) {
            pq.offer(new int[]{food_times[idx], idx + 1});
        }
        
        // pq에서 하나씩 뽑아서 현재 남은 k가 뽑은 음식을 다먹을 때까지 걸리는 시간보다
        // 큰 경우   ->  다 먹고 k 갱신
        // 작은 경우  ->  계산
        
        long prev = 0;
        long remain = food_times.length;
        
        while (!pq.isEmpty()) {
            long cur = pq.peek()[0];
            long spend = (cur - prev) * remain;

            if (k >= spend) {
                k -= spend;
                prev = cur;

                while (!pq.isEmpty() && pq.peek()[0] == cur) {
                    pq.poll();
                    remain--;
                }
            } else {
                break;
            }
        }
        
        List<int[]> foods = new ArrayList<>(pq);

        foods.sort(Comparator.comparingInt(a -> a[1]));

        return foods.get((int)(k % remain))[1];
    }
}