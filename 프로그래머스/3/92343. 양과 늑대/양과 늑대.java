import java.util.*;

class Solution {
    
    List<Integer>[] graph;
    int[] info;
    int answer = 0;
    
    public int solution(int[] info, int[][] edges) {
        int n = info.length;
        this.info = info;
        
        graph = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < edges.length; ++i) {
            graph[edges[i][0]].add(edges[i][1]);
        }
        
        List<Integer> nextNodes = new ArrayList<>();
        nextNodes.add(0);

        dfs(0, 0, 0, nextNodes);

        return answer;
    }
    
    private void dfs(int now, int sheep, int wolf, List<Integer> nextNodes) {
        if (info[now] == 0) {
            sheep++;
        } else {
            wolf++;
        }

        if (wolf >= sheep) {
            return;
        }

        answer = Math.max(answer, sheep);

        List<Integer> candidates = new ArrayList<>(nextNodes);

        candidates.remove(Integer.valueOf(now));

        for (int child : graph[now]) {
            candidates.add(child);
        }

        for (int next : candidates) {
            dfs(next, sheep, wolf, candidates);
        }
    }
}