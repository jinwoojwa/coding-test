import java.util.*;

class Solution {

    static class Edge {
        int to;
        int time;

        Edge(int to, int time) {
            this.to = to;
            this.time = time;
        }
    }

    int n;
    List<Edge>[] graph;
    boolean[] isGate;
    boolean[] isSummit;
    
    private void init(int[][] paths, int[] gates, int[] summits) {
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        isGate = new boolean[n + 1];
        isSummit = new boolean[n + 1];

        for (int g : gates) isGate[g] = true;
        for (int s : summits) isSummit[s] = true;

        for (int[] path : paths) {
            int i = path[0];
            int j = path[1];
            int w = path[2];

            graph[i].add(new Edge(j, w));
            graph[j].add(new Edge(i, w));
        }
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        this.n = n;
        init(paths, gates, summits);

        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq =
                new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        // 모든 gate에서 동시에 출발
        for (int g : gates) {
            intensity[g] = 0;
            pq.offer(new int[]{g, 0});
        }

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int node = cur[0];
            int curIntensity = cur[1];

            // 이미 더 좋은 경로가 있으면 무시
            if (curIntensity > intensity[node]) continue;

            // 정상에 도착하면 다음 탐색 안해도 됨
            if (isSummit[node]) continue;

            for (Edge e : graph[node]) {
                int next = e.to;

                // 다른 출입구 방문하면 안됨
                if (isGate[next]) continue;

                int nextIntensity = Math.max(curIntensity, e.time);

                if (nextIntensity < intensity[next]) {
                    intensity[next] = nextIntensity;
                    pq.offer(new int[]{next, nextIntensity});
                }
            }
        }

        int bestSummit = Integer.MAX_VALUE;
        int bestIntensity = Integer.MAX_VALUE;

        for (int summit : summits) {
            if (intensity[summit] < bestIntensity) {
                bestIntensity = intensity[summit];
                bestSummit = summit;
            } else if (intensity[summit] == bestIntensity
                    && summit < bestSummit) {
                bestSummit = summit;
            }
        }
        return new int[]{bestSummit, bestIntensity};
    }
}