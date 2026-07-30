import java.util.*;

class Solution {
    public int solution(int[] cards) {
        List<Integer> group = new ArrayList<>();
        
        boolean[] visited = new boolean[cards.length];
        for (int i = 0; i < cards.length; ++i) {
            if (visited[i]) continue;
            
            int cnt = 0;
            int cur = i;

            while (!visited[cur]) {
                visited[cur] = true;
                cnt++;
                cur = cards[cur] - 1;
            }
            group.add(cnt);
        }
        group.sort(Collections.reverseOrder());
        
        if (group.size() == 1) return 0;
        return group.get(0) * group.get(1);
    }
}