import java.util.*;

class Solution {
    
    static int[] result = new int[]{0, 0};
    
    public int[] solution(int[][] users, int[] emoticons) {
        chooseOptions(users, emoticons, 0, new int[emoticons.length]);
        return result;
    }
    
    private void chooseOptions(int[][] users, int[] emoticons, int idx, int[] choose) {
        if (idx == emoticons.length) {
            calcCost(users, emoticons, choose);
            return;
        }
        
        for (int sale = 1; sale <= 4; ++sale) {
            choose[idx] = sale * 10;
            chooseOptions(users, emoticons, idx + 1, choose);
        }
    }
    
    private void calcCost(int[][] users, int[] emoticons, int[] choose) {
        int serviceSubscriber = 0;
        int priceSum = 0;
        
        for (int[] user : users) {
            int userRate = user[0];
            int userPrice = user[1];
            
            int price = 0;
            for (int i = 0; i < emoticons.length; ++i) {
                int rate = choose[i];
                
                if (userRate > rate) continue;
                
                price += emoticons[i] - (emoticons[i] * rate / 100);  
            }
            if (userPrice <= price) serviceSubscriber++;
            else priceSum += price;
        }
        if (result[0] < serviceSubscriber) {
            result = new int[]{serviceSubscriber, priceSum};
        }
        else if (result[0] == serviceSubscriber) {
            if (result[1] < priceSum) {
                result = new int[]{serviceSubscriber, priceSum};
            }
        }
    }
}

// 이모티콘 별 할인율 -> 10%, 20%, 30%, 40% 4개 중 하나임
// 이모티콘 최대 개수 -> 7개 -> 4^7개밖에 안됨