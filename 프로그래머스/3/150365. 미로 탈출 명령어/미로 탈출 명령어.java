import java.util.*;
import java.util.stream.*;

class Solution {
    
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};
    static int n, m, r, c, k;
    
    static boolean found = false;
    static String answer = "impossible";
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n; this.m = m; this.r = r; this.c = c; this.k = k;
        
        List<String> list = new ArrayList<>();
        
        dfs(x, y, 0, list);
        
        return answer;
    }
    
    static void dfs(int x, int y, int cnt, List<String> str) {
        if (found) return;
        
        int remainDistance = Math.abs(r - x) + Math.abs(c - y);
        if (k - cnt < remainDistance) return;
        if ((k - cnt - remainDistance) % 2 != 0) return;
        
        if (cnt == k && x == r && y == c) {
            found = true;
            answer = str.stream().collect(Collectors.joining());
        }
        
        for (int dir = 0; dir < 4; ++dir) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            
            if (nx <= 0 || ny <= 0 || nx > n || ny > m) continue;
            
            str.add(getDir(dir));
            dfs(nx, ny, cnt + 1, str);
            str.remove(str.size() - 1);
        }
    }
    
    static String getDir(int dir) {
        String d = switch (dir) {
            case 0 -> "d";
            case 1 -> "l";
            case 2 -> "r";
            case 3 -> "u";
            default -> "x"; 
        }; 
        return d; 
    }
}