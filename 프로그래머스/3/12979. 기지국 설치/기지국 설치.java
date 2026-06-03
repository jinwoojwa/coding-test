import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        List<int[]> remainApt = new ArrayList<>();
        int dist = 2 * w + 1;
        
        int apt = 1;
        int stApt = 0, enApt = 0;
        
        for (int s : stations) {
            stApt = s - w;
            enApt = s + w;
            
            if (apt < stApt) {
                remainApt.add(new int[]{apt, stApt - 1});
                apt = enApt + 1;
            }
            else apt = enApt + 1;
        }
        
        if (enApt < n) remainApt.add(new int[]{enApt + 1, n});
        
        int answer = 0;
        for (var remain : remainApt) {
            int aptCnt = remain[1] - remain[0] + 1;
            int need = (int) Math.ceil((double) aptCnt / dist);
            
            answer += need;
        }

        return answer;
    }
}

/*
- N의 범위가 2억까지 -> O(N) 알고리즘으로 풀어야 함
- 또, 2억 범위니까 전파 전달 체크하려고 2억짜리 배열 쓰는 것도 안됨
- 한 번 설치하면 2w + 1 만큼 전파 전달 가능
- 주어진 기지국을 통해 남은 아파트 구하고, 각각의 (쪼개진 아파트 구역 / (2w + 1) + 1) 을 모두 더하면 정답?
*/