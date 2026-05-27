import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        
        Map<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i < participant.length; ++i) {
            map.merge(participant[i], 1, Integer::sum);
        }
        for (int i = 0; i < completion.length; ++i) {
            map.merge(completion[i], -1, Integer::sum);
        }
        
        String answer = "";
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) answer = entry.getKey();
        }
             
        return answer;
    }
}