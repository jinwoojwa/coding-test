import java.util.*;

class Solution {
    public String solution(String p) {
        if (p.isBlank()) return p;
        
        String[] result = splitString(p);
        String u = result[0];
        String v = result[1];
        
        // u가 올바른 괄호 문자열인 경우
        if (isCorrect(u)) {
            return u + solution(v);
        }
        
        // u가 올바른 괄호 문자열이 아닌 경우
        StringBuilder sb = new StringBuilder();

        sb.append("(");
        sb.append(solution(v));
        sb.append(")");
        
        // u의 첫 번째와 마지막 문자를 제거하고 나머지 괄호 방향 뒤집기
        for (int i = 1; i < u.length() - 1; i++) {
            if (u.charAt(i) == '(') sb.append(')');
            else sb.append('(');
        }

        return sb.toString();
        
    }
    
    private boolean isCorrect(String str) {
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') count++;
            else count--;

            if (count < 0) return false;
        }
        return count == 0;
    }
    
    private String[] splitString(String str) {
        int open = 0, close = 0;

        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) == '(') open++;
            else close++;

            if (open == close) {
                return new String[]{
                    str.substring(0, i + 1),
                    str.substring(i + 1)
                };
            }
        }
        return new String[]{str, ""};
    }
}