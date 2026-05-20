class Solution {
    
    int maxSheep;
    boolean[] visited;
    
    public int solution(int[] info, int[][] edges) {
        visited = new boolean[info.length];
        visited[0] = true;
        
        dfs(info, edges, 1, 0);
        
        return maxSheep;
    }
    
    private void dfs(int[] info, int[][] edges, int sheep, int wolf) {
        if (wolf >= sheep) return;
        
        maxSheep = Math.max(maxSheep, sheep);
        
        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];
            
            if (visited[parent] && !visited[child]) {
                visited[child] = true;
                if (info[child] == 0) dfs(info, edges, sheep + 1, wolf);
                else dfs(info, edges, sheep, wolf + 1);
                visited[child] = false;
            }
        }
        
        
    }
}