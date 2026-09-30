import java.util.*;

class Solution {

    class Edge {
        int next;
        int cost;

        Edge(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }
    }

    List<Edge>[] graph;

    public int solution(int n, int s, int a, int b, int[][] fares) {

        graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] fare : fares) {
            int c = fare[0];
            int d = fare[1];
            int f = fare[2];

            graph[c].add(new Edge(d, f));
            graph[d].add(new Edge(c, f));
        }

        int[] distS = dijkstra(s, n);
        int[] distA = dijkstra(a, n);
        int[] distB = dijkstra(b, n);

        int answer = Integer.MAX_VALUE;

        for (int k = 1; k <= n; k++) {
            int fare = distS[k] + distA[k] + distB[k];
            answer = Math.min(answer, fare);
        }

        return answer;
    }

    private int[] dijkstra(int start, int n) {

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> a[1]));
        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (cur[1] > dist[cur[0]]) continue;

            for (Edge edge : graph[cur[0]]) {
                int next = edge.next;
                int nextCost = cur[1] + edge.cost;

                if (nextCost < dist[next]) {
                    dist[next] = nextCost;
                    pq.offer(new int[]{next, nextCost});
                }
            }
        }
        return dist;
    }
}