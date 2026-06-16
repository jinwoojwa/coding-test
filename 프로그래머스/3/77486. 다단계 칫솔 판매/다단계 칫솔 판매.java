import java.util.*;

class Solution {    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, String> parentMap = new HashMap<>();
        for (int i = 0; i < enroll.length; ++i) {
            String child = enroll[i];
            String parent = referral[i];
            
            parentMap.put(child, parent);
        }
        
        Map<String, Long> profitMap = new HashMap<>();
        for (int i = 0; i < seller.length; ++i) {
            String cur = seller[i];
            long money = (long) amount[i] * 100;
            
            while (!cur.equals("-")) {
                long distributedMoney = money / 10;
                long myMoney = money - distributedMoney;
                
                profitMap.merge(cur, myMoney, Long::sum);
                
                if (distributedMoney == 0) break;
                
                cur = parentMap.getOrDefault(cur, "-");
                money = distributedMoney;
            }
        }
        
        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; ++i) {
            answer[i] = profitMap.getOrDefault(enroll[i], 0L).intValue();
        }
        return answer;
    }
}