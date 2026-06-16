class Solution {
    public int solution(String s) {
        int answer = 0;
        
        for (int idx = 0; idx < s.length(); ++idx) {
            answer = Math.max(answer, expand(s, idx, idx));     // 홀
            answer = Math.max(answer, expand(s, idx, idx + 1)); // 짝
        }
        return answer;
    }
    
    private int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}

// s를 idx = 0 ~ s.length() - 1까지 idx를 기준으로 가능한 최대 팰린드롬 길이를 idx마다 구한다
// -> 최대 idx = 2500 -> 최대 2500번 수행