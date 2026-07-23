import java.util.*;

class Solution {
    
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    
    public int[] solution(String[] maps) {
        int n = maps.length;
        int m = maps[0].length();
        
        List<Integer> resultList = new ArrayList<>();
        boolean[][] visited = new boolean[n][m];
        
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (maps[i].charAt(j) == 'X') continue;
                if (visited[i][j]) continue;
                
                Queue<int[]> q = new ArrayDeque<>();
                visited[i][j] = true;
                
                q.offer(new int[]{i, j});
                int foodSum = maps[i].charAt(j) - '0';
                
                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    
                    for (int dir = 0; dir < 4; ++dir) {
                        int nx = cur[0] + dx[dir];
                        int ny = cur[1] + dy[dir];
                        
                        if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                        if (visited[nx][ny] || maps[nx].charAt(ny) == 'X') continue;
                        
                        q.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                        foodSum += maps[nx].charAt(ny) - '0';
                    }
                }
                resultList.add(foodSum);
            }
        }
        if (resultList.isEmpty()) return new int[]{-1};
        Collections.sort(resultList);
        return resultList.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}