import java.util.*;

class Solution {
    
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    
    public int[] solution(String[][] places) {
        List<Integer> answer = new ArrayList<>();
        
        for (String[] place : places) {
            int res = bfs(place);
            answer.add(res);
        }
        
        return answer.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
    
    static int bfs(String[] place) {
        List<int[]> pList = getAllPerson(place);
        
        for (int[] p : pList) {
            Queue<int[]> q = new ArrayDeque<>();
            int[][] dist = new int[5][5];
            for (int[] d : dist) {
                Arrays.fill(d, -1);
            }
            
            q.offer(p);
            dist[p[0]][p[1]] = 0;
            
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                
                for (int dir = 0; dir < 4; ++dir) {
                    int nx = cur[0] + dx[dir];
                    int ny = cur[1] + dy[dir];
                    
                    if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;
                    if (place[nx].charAt(ny) == 'X' || dist[nx][ny] >= 0) continue;
                    
                    q.offer(new int[]{nx, ny});
                    dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
                    
                    if (place[nx].charAt(ny) == 'P' && dist[nx][ny] <= 2) return 0;
                }
            }
            
        }
        return 1;
    }
    
    static List<int[]> getAllPerson(String[] place) {
        List<int[]> pList = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                if (place[i].charAt(j) == 'P') {
                    pList.add(new int[]{i, j});
                }
            }
        }
        return pList;
    }
}