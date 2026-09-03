import java.util.*;

class Solution {
    public int[][] solution(int n, int[][] build_frame) {
        boolean[][] pillar = new boolean[n + 1][n + 1];
        boolean[][] beam = new boolean[n + 1][n + 1];
        
        for (int[] frame : build_frame) {
            int x = frame[0];
            int y = frame[1];
            int type = frame[2];
            int command = frame[3];

            if (command == 1) build(pillar, beam, x, y, type);
            else remove(pillar, beam, x, y, type, n);
        }
        return makeAnswer(pillar, beam, n);
    }
    
    private void build(boolean[][] pillar, boolean[][] beam,
                       int x, int y, int type
    ) {
        if (type == 0) { // 기둥
            pillar[x][y] = true;

            if (!isPossiblePillar(pillar, beam, x, y)) pillar[x][y] = false;
        }
        else { // 보
            beam[x][y] = true;

            if (!isPossibleBeam(pillar, beam, x, y)) beam[x][y] = false;
        }
    }
    
    private void remove(boolean[][] pillar, boolean[][] beam,
                       int x, int y, int type, int n
    ) {
        if (type == 0) {
            pillar[x][y] = false;

            if (!isPossible(pillar, beam, n)) pillar[x][y] = true;
        }
        else {
            beam[x][y] = false;

            if (!isPossible(pillar, beam, n)) beam[x][y] = true;
        }
    }
    
    private boolean isPossiblePillar(boolean[][] pillar, boolean[][] beam, int x, int y) {
        // 바닥 위에 있는 경우
        if (y == 0) return true;

        // 아래에 기둥이 있는 경우
        if (pillar[x][y - 1]) return true;

        // 왼쪽 끝에 보가 있는 경우
        if (x > 0 && beam[x - 1][y]) return true;

        // 오른쪽 끝에 보가 있는 경우
        if (beam[x][y]) return true;

        return false;
    }
    
    private boolean isPossibleBeam(boolean[][] pillar, boolean[][] beam, int x, int y) {
        // 왼쪽 끝에 기둥이 있는 경우
        if (pillar[x][y - 1]) return true;

        // 오른쪽 끝에 기둥이 있는 경우
        if (pillar[x + 1][y - 1]) return true;

        // 왼쪽과 오른쪽에 보가 연결되어 있는 경우
        if (x > 0 && beam[x - 1][y] && beam[x + 1][y]) return true;

        return false;
    }
    
    private boolean isPossible(boolean[][] pillar, boolean[][] beam, int n) {
        for (int x = 0; x <= n; ++x) {
            for (int y = 0; y <= n; ++y) {
                if (pillar[x][y] && !isPossiblePillar(pillar, beam, x, y)) return false;
                if (beam[x][y] && !isPossibleBeam(pillar, beam, x, y)) return false;
            }
        }
        return true;
    }
    
    private int[][] makeAnswer(boolean[][] pillar, boolean[][] beam, int n) {
        List<int[]> result = new ArrayList<>();

        for (int x = 0; x <= n; ++x) {
            for (int y = 0; y <= n; ++y) {
                if (pillar[x][y]) result.add(new int[]{x, y, 0});
                if (beam[x][y]) result.add(new int[]{x, y, 1});
            }
        }

        // x 오름차순 → y 오름차순 → 기둥(0) → 보(1)
        result.sort((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);

            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);

            return Integer.compare(a[2], b[2]);
        });

        return result.toArray(new int[result.size()][]);
    }
}