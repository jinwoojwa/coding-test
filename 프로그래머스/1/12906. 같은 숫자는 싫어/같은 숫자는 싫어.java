import java.util.*;

public class Solution {
    public int[] solution(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (int num : arr) {
            if (!stack.isEmpty() && stack.peek() == num) continue;
            
            stack.push(num);
        }
        
        int[] answer = new int[stack.size()];
        int idx = 0;

        while (!stack.isEmpty()) {
            answer[idx++] = stack.pollLast();
        }

        return answer;
    }
}