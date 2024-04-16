import java.util.*;

public class 캐시 {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        LinkedList<String> list = new LinkedList<>();
        Set<String> set = new HashSet<>();


        for (int i = 0; i < cities.length; i++) {

            String city = cities[i].toLowerCase();


            // 이미 캐시에 존재한다면?  hit
            if (set.contains(city)) {


                answer += 1;
                int delete = 0;
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j).equals(city)) {
                        delete = j;
                        break;
                    }
                }
                list.remove(delete);
                list.addLast(city);
            } else {
                answer += 5;
                if (cacheSize == 0) continue;
                // 캐시가 꽉 찼으면 맨 앞 삭제
                if (list.size() == cacheSize) {
                    set.remove(list.get(0));
                    list.removeFirst();
                }
                set.add(city);
                list.addLast(city);
            }

        }


        return answer;
    }


}
