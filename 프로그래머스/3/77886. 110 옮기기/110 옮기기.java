class Solution {

    public String[] solution(String[] s) {
        String[] answer = new String[s.length];

        for (int i = 0; i < s.length; i++) {
            StringBuilder stack = new StringBuilder();
            int count = 0;

            // 110 제거
            for (char c : s[i].toCharArray()) {
                stack.append(c);

                int len = stack.length();

                if (len >= 3 &&
                        stack.charAt(len - 3) == '1' &&
                        stack.charAt(len - 2) == '1' &&
                        stack.charAt(len - 1) == '0') {

                    stack.delete(len - 3, len);
                    count++;
                }
            }

            // 마지막 0 찾기
            int idx = stack.lastIndexOf("0");

            StringBuilder result = new StringBuilder();

            if (idx == -1) {
                for (int j = 0; j < count; j++) {
                    result.append("110");
                }
                result.append(stack);
            } else {
                result.append(stack.substring(0, idx + 1));

                for (int j = 0; j < count; j++) {
                    result.append("110");
                }
                result.append(stack.substring(idx + 1));
            }
            answer[i] = result.toString();
        }
        return answer;
    }
}