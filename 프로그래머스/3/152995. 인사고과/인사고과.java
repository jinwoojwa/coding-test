import java.util.*;

class Solution {
    
    class Employee {
        int idx;
        int attitude;
        int peer;
        int sum;
        
        Employee(int idx, int attitude, int peer) {
            this.idx = idx;
            this.attitude = attitude;
            this.peer = peer;
            this.sum = attitude + peer;
        }
    }
    
    public int solution(int[][] scores) {
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < scores.length; ++i) {
            employees.add(new Employee(
                i, scores[i][0], scores[i][1]
            ));
        }
        
        employees.sort(Comparator.comparingInt((Employee e) -> e.sum).reversed());
        
        int rank = 0;
        for (int i = 0; i < employees.size(); ++i) {
            Employee e = employees.get(i);
            boolean flag = true;
            for (int j = 0; j < i; ++j) {
                if (e.sum == employees.get(j).sum) continue;
                
                if (e.attitude < employees.get(j).attitude &&
                    e.peer < employees.get(j).peer
                   ) {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                if (e.idx == 0) return -1;
                continue;
            }
            rank++;
            if (e.idx == 0) break;
        }
        return rank;
    }
}

// 인센티브를 받지 못하는 사람을 어떻게 걸러낼 것인가?
