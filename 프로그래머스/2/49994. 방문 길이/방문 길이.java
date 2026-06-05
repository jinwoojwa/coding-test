class Solution {
    
    int[] dx = {0, 1, 0, -1}; // R, D, L, U
    int[] dy = {1, 0, -1, 0};
    
    public int solution(String dirs) {
        int answer = 0;
        
        boolean[][][] visited = new boolean[11][11][4]; // 시작 좌표: (5, 5)
        int x = 5, y = 5;
        
        for (int i = 0; i < dirs.length(); ++i) {
            int moveDir = getDir(dirs.charAt(i));
            int reverseDir = (moveDir + 2) % 4;
            
            int nx = x + dx[moveDir];
            int ny = y + dy[moveDir];
            
            if (nx < 0 || ny < 0 || nx > 10 || ny > 10) continue;
            
            // 아직 방문 X
            if (!visited[nx][ny][moveDir]) answer++;
            
            visited[nx][ny][moveDir] = true;
            visited[x][y][reverseDir] = true;
            x = nx; y = ny;
        }
        return answer;
    }
    
    private int getDir(char c) {
        return switch (c) {
                case 'R' -> 0;
                case 'D' -> 1;
                case 'L' -> 2;
                case 'U' -> 3;
                default -> -1;
        };
    }
}

// 같은 지점에 방문했더라도 어느 방향에서 방문했는지에 따라 길이 다름 (방문 표시에 방향 정보도 넣어야 함)