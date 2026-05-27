import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> clothType = new HashMap<>();
        for (String[] cloth : clothes) {
            clothType.merge(cloth[1], 1, Integer::sum);
        }
        
        int answer = 1;
        for (Integer type : clothType.values()) {
            answer *= (type + 1);
        }
        answer--;
        
        return answer;
    }
}