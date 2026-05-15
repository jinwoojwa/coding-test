import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        
        Set<String> reportSet = new HashSet<>(Arrays.asList(report));
        
        Map<String, Integer> idMap = new HashMap<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        for (int idx = 0; idx < id_list.length; ++idx) {
            idMap.put(id_list[idx], idx);
            map.put(idMap.get(id_list[idx]), new ArrayList<>());
        }
        
        for (String r : reportSet) {
            String[] splitedReport = r.split(" ");
            String from = splitedReport[0];
            String to = splitedReport[1];
            
            map.get(idMap.get(to)).add(idMap.get(from));
        }
        
        for (var entry : map.entrySet()) {
            if (entry.getValue().size() >= k) {
                for (int id : entry.getValue()) {
                    answer[id]++;
                }
            }
        }
        
        return answer;
    }
}