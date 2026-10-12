import java.util.*;

class Solution {
    
    class Job {
        int num;
        int startTime;
        int duration;
        
        public Job(int num, int startTime, int duration) {
            this.num = num;
            this.startTime = startTime;
            this.duration = duration;
        }
    }
    
    public int solution(int[][] jobs) {
        List<Job> jobList = new ArrayList<>();
        for (int i = 0; i < jobs.length; ++i) {
            jobList.add(new Job(i, jobs[i][0], jobs[i][1]));
        }
        
        Collections.sort(jobList, Comparator.comparingInt((Job j) -> j.startTime)
                        .thenComparing(j -> j.num));
        
        // 소요시간 -> 요청 시각 -> 작업 번호
        PriorityQueue<Job> pq = new PriorityQueue<>(
            Comparator.comparingInt((Job j) -> j.duration)
                      .thenComparing(j -> j.startTime)
                      .thenComparing(j -> j.num)
        );
        
        int time = 0, total = 0, idx = 0, size = jobs.length;
        
        while (idx < size || !pq.isEmpty()) {
            while (idx < size && jobList.get(idx).startTime <= time) {
                pq.offer(jobList.get(idx++));
            }
            
            if (!pq.isEmpty()) {
                Job job = pq.poll();
                time += job.duration;
                total += time - job.startTime;
            } else {
                time = jobList.get(idx).startTime;
            }
        }
        return total / size;
    }
}