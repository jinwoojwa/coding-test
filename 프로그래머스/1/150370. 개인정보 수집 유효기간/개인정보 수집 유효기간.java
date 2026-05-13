import java.util.*;

class Solution {
    
    static Map<String, Integer> termsMap = new HashMap<>();
    
    public static int changeDateFormat(String date) {
        String[] splitedDate = date.split("\\.");
        
        int year = Integer.parseInt(splitedDate[0].substring(2));
        int month = Integer.parseInt(splitedDate[1]);
        int day = Integer.parseInt(splitedDate[2]);
        
        return (year * 12 * 28) + (month * 28) + day;
    }
    
    public static int calcEndDate(String privacy) {
        String[] splitedPrivacy = privacy.split(" ");
        
        int privacyDate = changeDateFormat(splitedPrivacy[0]);
        String term = splitedPrivacy[1];
        
        return privacyDate + termsMap.get(term);
    }
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        
        for (String term : terms) {
            String[] splitedTerm = term.split(" ");
            
            termsMap.put(splitedTerm[0], Integer.parseInt(splitedTerm[1]) * 28);
        }
        
        for (int i = 0; i < privacies.length; ++i) {
            int endDate = calcEndDate(privacies[i]);
            
            if (changeDateFormat(today) >= endDate) answer.add(i + 1);
        }
        
        return answer.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}