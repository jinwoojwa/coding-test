import java.util.*;

class Solution {
    
    private long answer = 0;
    
    private final List<String> expr = new ArrayList<>();
    private final List<String> operators = new ArrayList<>();
    
    public long solution(String expression) {
        splitExpression(expression);

        boolean[] visited = new boolean[operators.size()];
        List<String> priority = new ArrayList<>();

        dfs(visited, priority);

        return answer;
    }
    
    private void dfs(boolean[] visited, List<String> priority) {
        if (priority.size() == operators.size()) {
            long result = calculate(priority);
            answer = Math.max(answer, Math.abs(result));
            return;
        }

        for (int i = 0; i < operators.size(); i++) {
            if (visited[i]) continue;

            visited[i] = true;
            priority.add(operators.get(i));

            dfs(visited, priority);

            priority.remove(priority.size() - 1);
            visited[i] = false;
        }
    }
    
    private long calculate(List<String> priority) {
        List<String> temp = new ArrayList<>(expr);

        for (String operator : priority) {
            for (int i = 0; i < temp.size(); i++) {
                if (!temp.get(i).equals(operator)) continue;

                long left = Long.parseLong(temp.get(i - 1));
                long right = Long.parseLong(temp.get(i + 1));

                long result = switch (operator) {
                    case "+" -> left + right;
                    case "-" -> left - right;
                    case "*" -> left * right;
                    default -> throw new IllegalArgumentException();
                };

                temp.remove(i - 1);
                temp.remove(i - 1);
                temp.remove(i - 1);

                temp.add(i - 1, String.valueOf(result));

                i--;
            }
        }

        return Long.parseLong(temp.get(0));
    }
    
    private void splitExpression(String expression) {
        StringBuilder num = new StringBuilder();
        
        for (int i = 0; i < expression.length(); ++i) {
            char c = expression.charAt(i);
            
            // 숫자라면
            if (Character.isDigit(c)) num.append(c);
            else {
                expr.add(num.toString());
                String operator = String.valueOf(c);
                expr.add(operator);

                if (!operators.contains(operator)) {
                    operators.add(operator);
                }
                num.setLength(0);
            }
        }
        expr.add(num.toString());
    }
}

// 연산자는 3개(+, -, *)이므로 연산자 우선순위 조합은 3! = 6개
// expression은 최대 길이가 100 이하인 문자열이므로 6개의 조합에 대해 브루트포스를 돌려도 됨