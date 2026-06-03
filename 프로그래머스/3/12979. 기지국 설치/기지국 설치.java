import java.util.*;

class Solution {
    
    private int dist;
    
    public int solution(int n, int[] stations, int w) {
        dist = 2 * w + 1;
        
        int apt = 1;
        int stApt = 0, enApt = 0;
        int answer = 0;
        
        for (int s : stations) {
            stApt = s - w;
            enApt = s + w;
            
            if (apt < stApt) {
                answer += calNeedStationNum(apt, stApt - 1);
                apt = enApt + 1;
            }
            else apt = enApt + 1;
        }
        
        if (enApt < n) answer += calNeedStationNum(enApt + 1, n);

        return answer;
    }
    
    private int calNeedStationNum(int start, int end) {
        int aptCnt = end - start + 1;
        return (int) Math.ceil((double) aptCnt / dist);
    }
}

/*
- N의 범위가 2억까지 -> O(N) 알고리즘으로 풀어야 함
- 또, 2억 범위니까 전파 전달 체크하려고 2억짜리 배열 쓰는 것도 안됨
- 한 번 설치하면 2w + 1 만큼 전파 전달 가능
- 주어진 기지국을 통해 남은 아파트 구하고, 각각의 (쪼개진 아파트 구역 / (2w + 1) + 1) 을 모두 더하면 정답?
*/