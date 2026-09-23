class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        int deliveryPointer = n - 1;
        int pickupPointer = n - 1;
        
        while (deliveryPointer >= 0 || pickupPointer >= 0) {
            while (deliveryPointer >= 0 && deliveries[deliveryPointer] == 0) deliveryPointer--;
            while (pickupPointer >= 0 && pickups[pickupPointer] == 0) pickupPointer--;
            
            answer += (Math.max(deliveryPointer, pickupPointer) + 1) * 2;
            
            // 택배 배달
            int deliveryCap = cap;
            while (deliveryPointer >= 0 && deliveryCap > 0) {
                if (deliveries[deliveryPointer] > deliveryCap) {
                    deliveries[deliveryPointer] -= deliveryCap;
                    deliveryCap = 0;
                }
                else {
                    deliveryCap -= deliveries[deliveryPointer];
                    deliveries[deliveryPointer] = 0;
                    deliveryPointer--;
                }
            }
            
            // 택배 수거
            int pickupCap = cap;
            while (pickupPointer >= 0 && pickupCap > 0) {
                if (pickups[pickupPointer] > pickupCap) {
                    pickups[pickupPointer] -= pickupCap;
                    pickupCap = 0;
                }
                else {
                    pickupCap -= pickups[pickupPointer];
                    pickups[pickupPointer] = 0;
                    pickupPointer--;
                }
            }
        }
        
        return answer;
    }
}