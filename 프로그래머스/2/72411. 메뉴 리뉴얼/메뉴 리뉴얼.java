import java.util.*;

class Solution {
    
    Map<String, Integer> menuMap = new HashMap<>();
    
    public String[] solution(String[] orders, int[] course) {
        for (String order : orders) {

            char[] arr = order.toCharArray();
            Arrays.sort(arr);

            for (int len : course) {
                if (len <= arr.length) {
                    comb(arr, 0, len, new StringBuilder());
                }
            }
        }
        
        List<String> result = new ArrayList<>();
        
        for (int len : course) {
            int maxCnt = 0;

            for (Map.Entry<String, Integer> entry : menuMap.entrySet()) {
                String menu = entry.getKey();
                int cnt = entry.getValue();

                if (menu.length() == len && cnt >= 2) {
                    maxCnt = Math.max(maxCnt, cnt);
                }
            }

            for (Map.Entry<String, Integer> entry : menuMap.entrySet()) {
                String menu = entry.getKey();
                int cnt = entry.getValue();

                if (menu.length() == len && cnt == maxCnt && cnt >= 2) {
                    result.add(menu);
                }
            }
        }
        Collections.sort(result);

        return result.toArray(new String[0]);
    }
    
    private void comb(char[] arr, int start, int targetLen, StringBuilder sb) {
        if (sb.length() == targetLen) {
            String menu = sb.toString();
            menuMap.put(menu, menuMap.merge(menu, 1, Integer::sum));
            return;
        }

        for (int i = start; i < arr.length; i++) {
            sb.append(arr[i]);
            comb(arr, i + 1, targetLen, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}