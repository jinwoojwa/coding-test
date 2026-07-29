import java.util.*;

class Solution {
    static int result = 100_000_000;
    
    public int solution(int[] picks, String[] minerals) {
        dfs(picks, minerals, 0, 0);
        
        return result;
    }
    
    private static void dfs(int[] picks, String[] minerals, int idx, int fatigue) {
        if (idx >= minerals.length || useAllPicks(picks)) {
            result = Math.min(result, fatigue);
            return;
        }
        
        for (int i = 0; i < 3; ++i) {
            if (picks[i] <= 0) continue;
            
            picks[i]--;
            
            int possibleIdx = Math.min(idx + 5, minerals.length) - 1;
            int consumedFatigue = mineMinerals(idx, possibleIdx, minerals, i);
            dfs(picks, minerals, possibleIdx + 1, fatigue + consumedFatigue);
            
            picks[i]++;
        }
    }
    
    private static boolean useAllPicks(int[] picks) {
        for (int i = 0; i < 3; ++i) {
            if (picks[i] > 0) return false;
        }
        return true;
    }
    
    private static int mineMinerals(int st, int end, String[] minerals, int pickNum) {
        int fatigue = 0;
        for (int i = st; i <= end; ++i) {
            fatigue += mining(pickNum, minerals[i]);
        }
        return fatigue;
    }
    
    private static int mining(int pickNum, String mineral) {
        if (pickNum == 0) return 1;
        if (pickNum == 1) {
            if (mineral.equals("diamond")) return 5;
            return 1;
        }
        if (mineral.equals("diamond")) return 25;
        else if (mineral.equals("iron")) return 5;
        return 1;
    }
}