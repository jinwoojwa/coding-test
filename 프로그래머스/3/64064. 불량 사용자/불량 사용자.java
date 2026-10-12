import java.util.*;

class Solution {
    
    int len;
    List<String>[] banList;
    Set<String> answerSet = new HashSet<>();
    Map<String, Boolean> isUsed = new HashMap<>();
    
    public int solution(String[] user_id, String[] banned_id) {
        len = banned_id.length;
        
        banList = new List[banned_id.length];
        for (int i = 0; i < len; i++) {
            banList[i] = new ArrayList<>();
        }
        
        for (String userId : user_id) {
            isUsed.put(userId, false);
        }
        
        for (int i = 0; i < len; ++i) {
            for (String userId : user_id) {
                if (isMatch(banned_id[i], userId)) banList[i].add(userId); 
            }
        }
        
        dfs(0, "");
        
        return answerSet.size();
    }
    
    private void dfs(int idx, String str) {
        if (idx == len) {
            answerSet.add(sortById(str));
            return;
        }
        
        for (String bannedId : banList[idx]) {
            if (isUsed.get(bannedId)) continue;
            
            isUsed.put(bannedId, true);
            
            dfs(idx + 1, str + "+" + bannedId);
            
            isUsed.put(bannedId, false);
        }
    }
    
    private boolean isMatch(String bannedId, String userId) {
        if (bannedId.length() != userId.length()) return false;
        
        for (int i = 0; i < bannedId.length(); ++i) {
            char b = bannedId.charAt(i);
            char u = userId.charAt(i);
            
            if (b != '*' && b != u) return false;
        }
        return true;
    }
    
    private String sortById(String str) {
        String[] temp = str.split("\\+");
        Arrays.sort(temp);
        return String.join("+", temp);
    }
}

// 각 불량 사용자 아이디 목록 하나에 매핑되는 값을 구하고, 곱해서 경우의 수를 구하기
// 근데 banned_id 끼리 겹치는 유저 아이디가 있을 수 있음
// 유저 아이디가 최대 8개까지밖에 없으니까, banned_id에 매칭되는 유저아이디 리스트를 만들고, dfs로 조합을 구해도 될 듯.
// 각 조합의 아이디 목록 내용이 동일하면 같은 것으로 처리해야 하니까 조합을 Set에 넣어서 처리