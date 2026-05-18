import java.util.*;

class Solution {
    
    int cardinality;
    int degree;
    int candidateKeyCnt;
    String[][] relation;
    
    public int solution(String[][] relation) {
        
        cardinality = relation.length;
        degree = relation[0].length;
        this.relation = relation;
        
        List<Integer> candidateKeys = new ArrayList<>();
        
        for (int mask = 1; mask < (1 << degree); ++mask) {
            if (!isMinimal(candidateKeys, mask)) continue;
            
            if (isCandidate(mask)) {
                candidateKeys.add(mask);
                candidateKeyCnt++;
            }
        }
        
        return candidateKeyCnt;
    }
    
    private boolean isMinimal(List<Integer> candidateKeys, int mask) {
        for (int key : candidateKeys) {
            if ((key & mask) == key) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isCandidate(int mask) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < cardinality; ++i) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < degree; ++j) {
                if ((mask & (1 << j)) != 0) {
                    sb.append(relation[i][j]).append(",");
                }
            }
            set.add(sb.toString());
        }
        return set.size() == cardinality;
    }
}