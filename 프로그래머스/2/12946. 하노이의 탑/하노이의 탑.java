import java.util.*;

class Solution {
    List<int[]> answer = new ArrayList<>();

    public int[][] solution(int n) {
        playHanoi(n, 1, 2, 3);

        return answer.toArray(new int[0][]);
    }

    private void playHanoi(int n, int from, int via, int to) {
        if (n == 0) return;

        playHanoi(n - 1, from, to, via);

        answer.add(new int[]{from, to});

        playHanoi(n - 1, via, from, to);
    }
}