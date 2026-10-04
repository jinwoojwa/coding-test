import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        long cur1 = sequence[0];
        long max1 = sequence[0];

        long cur2 = -sequence[0];
        long max2 = -sequence[0];

        for (int i = 1; i < sequence.length; i++) {
            long val1 = sequence[i] * (i % 2 == 0 ? 1 : -1);
            long val2 = sequence[i] * (i % 2 == 0 ? -1 : 1);

            cur1 = Math.max(val1, cur1 + val1);
            max1 = Math.max(max1, cur1);

            cur2 = Math.max(val2, cur2 + val2);
            max2 = Math.max(max2, cur2);
        }

        return Math.max(max1, max2);
    }
}

// 각 원소(n)들은 연속 펄스 부분 수열을 만들었을 때, (n * 1) or (n * -1)
// 시작 인덱스 기준 원소들은 자신의 순서(홀짝)에 따라 달라짐