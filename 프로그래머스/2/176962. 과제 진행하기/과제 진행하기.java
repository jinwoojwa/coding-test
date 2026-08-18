import java.util.*;

class Solution {

    static class Job {
        String name;
        int start;
        int playTime;

        Job(String name, int start, int playTime) {
            this.name = name;
            this.start = start;
            this.playTime = playTime;
        }
    }

    public String[] solution(String[][] plans) {
        // 시작 시간순 정렬
        Arrays.sort(plans, (p1, p2) ->
            Integer.compare(toMinutes(p1[1]), toMinutes(p2[1]))
        );

        Stack<Job> stack = new Stack<>();
        List<String> answer = new ArrayList<>();

        for (int i = 0; i < plans.length; i++) {
            String name = plans[i][0];
            int start = toMinutes(plans[i][1]);
            int playTime = Integer.parseInt(plans[i][2]);

            Job current = new Job(name, start, playTime);

            // 다음 과제가 존재하는 경우
            if (i < plans.length - 1) {
                int nextStart = toMinutes(plans[i + 1][1]);

                int availableTime = nextStart - current.start;

                // 현재 과제가 다음 과제 시작 전에 끝나는 경우
                if (current.playTime <= availableTime) {
                    answer.add(current.name);

                    availableTime -= current.playTime;

                    // 남는 시간 동안 중단된 과제 처리
                    while (availableTime > 0 && !stack.isEmpty()) {
                        Job paused = stack.pop();

                        if (paused.playTime <= availableTime) {
                            availableTime -= paused.playTime;
                            answer.add(paused.name);
                        } else {
                            paused.playTime -= availableTime;
                            availableTime = 0;
                            stack.push(paused);
                        }
                    }
                }
                // 현재 과제가 다음 과제 시작 전에 끝나지 않는 경우
                else {
                    current.playTime -= availableTime;
                    stack.push(current);
                }
            }
            // 마지막 과제
            else {
                answer.add(current.name);
            }
        }
        // 남아 있는 멈춘 과제들 해결
        while (!stack.isEmpty()) answer.add(stack.pop().name);

        return answer.toArray(new String[0]);
    }

    private int toMinutes(String time) {
        String[] parts = time.split(":");

        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);

        return hour * 60 + minute;
    }
}

/*
1. 시작 시간이 빠른 순서로 과제를 정렬
2. 현재 과제를 실행
3. 다음 과제 시작 시간까지 남은 시간이 있다면
   → Stack의 과제를 pop하면서 실행
4. 현재 과제가 다음 과제 시작 시간까지 끝나지 않는다면
   → 남은 시간을 Stack에 push
5. 모든 신규 과제를 처리한 후
   → Stack을 전부 pop
*/
