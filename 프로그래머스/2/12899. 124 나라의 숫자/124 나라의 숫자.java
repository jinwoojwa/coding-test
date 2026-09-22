class Solution {
    public String solution(int n) {
        int digits = 1;
        while (n > Math.pow(3, digits)) {
            n -= Math.pow(3, digits);
            digits++;
        }
        
        n--;
        
        StringBuilder answer = new StringBuilder();
        
        for (int i = 0; i < digits; ++i) {
            int remain = n % 3;
            
            if (remain == 0) answer.append("1");
            else if (remain == 1) answer.append("2");
            else answer.append("4");
            
            n /= 3;
        }
        return answer.reverse().toString();
    }
}