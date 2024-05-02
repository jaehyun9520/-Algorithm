import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class 귤고르기 {
    public static int solution(int k, int[] tangerine) {
        int answer = 0;


        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < tangerine.length; i++) {

            int val = tangerine[i];

            if (!map.containsKey(val)) {
                map.put(val, 1);
            } else {
                map.put(val, map.get(val) + 1);
            }

        }

        ArrayList<Integer> list = new ArrayList<>(map.values());
        Collections.sort(list);

        int sum = 0;
        for (int i = list.size() - 1; i >= 0; i--) {

            sum += list.get(i);
            answer++;

            if (sum >= k) {
                break;
            }


        }
        return answer;
    }
}
