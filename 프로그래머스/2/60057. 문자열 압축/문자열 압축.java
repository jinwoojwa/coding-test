class Solution {
    int answer = 1001;
    
    public int solution(String s) {
        
        if (s.length() == 1) return 1;
        
        for (int sliceSize = 1; sliceSize <= s.length() / 2; ++sliceSize) {
            compressStr(s, sliceSize);
        }
        
        return answer;
    }
    
    private void compressStr(String s, int sliceSize) {
        int lenOfStr = 0;
        
        String prev = "";
        int prevCnt = 1;
        
        for (int idx = 0; idx < s.length(); idx += sliceSize) {
            int endIdx = Math.min(idx + sliceSize, s.length());
            
            String cur = s.substring(idx, endIdx);
            
            if (prev.equals(cur)) {
                prevCnt++;
            }
            else {
                if (prevCnt != 1) lenOfStr += String.valueOf(prevCnt).length();
                lenOfStr += prev.length();
                
                prev = cur;
                prevCnt = 1;
            }
        }
        
        if (prevCnt != 1) {
            lenOfStr += String.valueOf(prevCnt).length();
        }

        lenOfStr += prev.length();
        
        answer = Math.min(answer, lenOfStr);
    }
}