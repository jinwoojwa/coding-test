
import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        Arrays.sort(timetable);

        int idx = 0;
        int answer = 0;

        for (int i = 0; i < n; ++i) {
            int busTime = 540 + i * t; // 이번 버스 출발 시간 (분)

            int cnt = 0; // 이번 버스에 탄 크루 수

            // 버스 출발 시간까지 도착한 크루를 최대 m명 태움
            while (idx < timetable.length
                    && toTime(timetable[idx]) <= busTime
                    && cnt < m) {
                idx++;
                cnt++;
            }

            if (i == n - 1) { // 마지막 버스
                if (cnt < m) {
                    // 자리가 남으면 -> 버스 출발 시간에 도착해도 됨
                    answer = busTime;
                } else {
                    // 만석이면 -> 마지막 탑승자보다 1분 일찍 도착해야 함
                    answer = toTime(timetable[idx - 1]) - 1;
                }
            }
        }

        return String.format("%02d:%02d", answer / 60, answer % 60);
    }

    private int toTime(String time) {
        String[] t = time.split(":");

        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }
}
