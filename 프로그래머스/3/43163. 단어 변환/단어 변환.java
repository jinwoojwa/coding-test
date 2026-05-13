import java.util.*;

class Solution {
    
    static boolean isAdj(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); ++i) {
            if (a.charAt(i) != b.charAt(i)) diff++;
        }
        return diff == 1;
    }
     
    static int getIndex(String word, String[] words) {
        for (int i = 0; i < words.length; ++i) {
            if (words[i].equals(word)) {
                return i;
            }
        }
        return -1;
    }
    
    static int bfs(String begin, String target, String[] words) {
        Queue<String> q = new ArrayDeque<>();
        int[] dist = new int[words.length];
        Arrays.fill(dist, -1);
        
        q.offer(begin);
        
        while (!q.isEmpty()) {
            String cur = q.poll();
            
            for (int i = 0; i < words.length; ++i) {
                if (dist[i] != -1) continue;
                if (!isAdj(cur, words[i])) continue;
                
                if (cur.equals(begin)) {
                    dist[i] = 1;
                } else {
                    dist[i] = dist[getIndex(cur, words)] + 1;
                }

                q.offer(words[i]);
            }
        }
        
        for (int i = 0; i < words.length; ++i) {
            if (words[i].equals(target)) {
                return dist[i] == -1 ? 0 : dist[i];
            }
        }
        return 0;
    }
    
    public int solution(String begin, String target, String[] words) {
        return bfs(begin, target, words);
    }
}