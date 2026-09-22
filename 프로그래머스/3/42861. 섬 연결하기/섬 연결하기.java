import java.util.*;

class Solution {
    
    static class Bridge {
        int island;
        int cost;

        Bridge(int island, int cost) {
            this.island = island;
            this.cost = cost;
        }
    }
    
    public int solution(int n, int[][] costs) {
        int answer = 0;

        List<Bridge>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] cost : costs) {
            int a = cost[0];
            int b = cost[1];
            int price = cost[2];

            graph[a].add(new Bridge(b, price));
            graph[b].add(new Bridge(a, price));
        }

        boolean[] visited = new boolean[n];

        PriorityQueue<Bridge> pq =
                new PriorityQueue<>(Comparator.comparingInt(bridge -> bridge.cost));

        pq.offer(new Bridge(0, 0));

        while (!pq.isEmpty()) {
            Bridge current = pq.poll();

            if (visited[current.island]) continue;

            visited[current.island] = true;
            answer += current.cost;

            for (Bridge next : graph[current.island]) {
                if (!visited[next.island]) pq.offer(next);
            }
        }
        return answer;
    }
}