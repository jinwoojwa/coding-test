import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        Map<String, Integer> map = new HashMap<>();

        for (int[] route : routes) {
            int time = 0;

            int r = points[route[0] - 1][0];
            int c = points[route[0] - 1][1];

            addPositionCount(map, time, r, c);

            for (int i = 1; i < route.length; i++) {
                int[] next = points[route[i] - 1];
                int nr = next[0];
                int nc = next[1];

                // r 먼저 이동
                while (r != nr) {
                    time++;
                    r += (nr > r) ? 1 : -1;
                    addPositionCount(map, time, r, c);
                }

                // c 이동
                while (c != nc) {
                    time++;
                    c += (nc > c) ? 1 : -1;
                    addPositionCount(map, time, r, c);
                }
            }
        }
        int answer = 0;
        for (int cnt : map.values()) {
            if (cnt >= 2) answer++;
        }

        return answer;
    }

    private void addPositionCount(Map<String, Integer> map, int t, int r, int c) {
        String key = t + "," + r + "," + c;
        map.put(key, map.getOrDefault(key, 0) + 1);
}
}