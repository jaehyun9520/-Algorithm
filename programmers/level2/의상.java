import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class 의상 {
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < clothes.length; i++) {

            if (map.containsKey(clothes[i][1])) {
                int count = map.get(clothes[i][1]);
                map.replace(clothes[i][1], count + 1);
            } else {
                map.put(clothes[i][1], 1);
            }
        }

        Set<String> keys = map.keySet();
        Iterator<String> iterator = keys.iterator();

        while (iterator.hasNext()) {
            answer *= (map.get(iterator.next()) + 1);
        }


        return answer - 1;
    }
}
