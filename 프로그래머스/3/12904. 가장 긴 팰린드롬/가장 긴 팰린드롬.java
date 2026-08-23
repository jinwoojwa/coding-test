class Solution {
    static String s = "";
    
    public int solution(String s) {
        this.s = s;
        int answer = 0;
        
        for (int mid = 0; mid < s.length(); ++mid) {
            int odd = findLength(mid, mid);
            int even = findLength(mid, mid + 1);
            answer = Math.max(answer, Math.max(odd, even));
        }
        return answer;
    }
    
    private int findLength(int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}