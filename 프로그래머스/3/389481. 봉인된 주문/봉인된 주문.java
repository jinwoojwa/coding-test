import java.util.*;

class Solution {
    
    private final int ALPHABET_CNT = 26;
    
    public String solution(long n, String[] bans) {
        long[] banned = new long[bans.length];

        for (int i = 0; i < bans.length; i++) {
            banned[i] = toNumber(bans[i]);
        }

        Arrays.sort(banned);

        long left = 1;
        long right = Long.MAX_VALUE / 4;
        
        while (left < right) {
            long mid = left + (right - left) / 2;

            long removed = upperBound(banned, mid);
            long remain = mid - removed;

            if (remain >= n) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return toString(left);
    }
    
    private long toNumber(String s) {
        long num = 0;

        for (int i = 0; i < s.length(); ++i) {
            num = num * ALPHABET_CNT + (s.charAt(i) - 'a' + 1);
        }

        return num;
    }
    
    private String toString(long num) {
        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            num -= 1;
            sb.append((char) ('a' + (num % ALPHABET_CNT)));
            num /= ALPHABET_CNT;
        }

        return sb.reverse().toString();
    }
    
    private int upperBound(long[] arr, long target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
