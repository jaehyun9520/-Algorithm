import java.util.*;
public class 메뉴리뉴얼 {
    public static String[] solution(String[] orders, int[] course) {
        String[] answer = {};

        // 길이별로 만들어지는 조합과 나온갯수
        Map<Integer, Map<String, Integer>> map = new HashMap<>();

        for (int i = 2; i <= 10; i++) {
            map.put(i, new HashMap<>());
        }

        char[] list = new char[10];
        // 각  손님의 주문을 가지고 조합을 만들어준다
        for (int i = 0; i < orders.length; i++) {
            char[] chars = orders[i].toCharArray();
            Arrays.sort(chars);
            orders[i] = new String(chars);
            for (int j = 0; j < course.length; j++) {
                comb(0, course[j], 0, orders[i], map, list);
            }
        }

        List<String> answerList = new ArrayList<>();

        for (int i = 2; i <= 10; i++) {
            int maxCnt = 0;
            List<String> lengthList = new ArrayList<>();
            Map<String, Integer> lengthMap = map.get(i);
            for (String key : lengthMap.keySet()) {
                int cnt = lengthMap.get(key);

                if (cnt >= 2) {

                    if (cnt > maxCnt) {
                        lengthList.clear();
                        lengthList.add(key);
                        maxCnt = cnt;
                    } else if (cnt == maxCnt) {
                        lengthList.add(key);
                    }
                }
            }
            for (String comb : lengthList) {
                answerList.add(comb);
            }
        }

        Collections.sort(answerList);

        answer = new String[answerList.size()];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }

    static void comb(int cnt, int goalCnt, int idx, String order,
                     Map<Integer, Map<String, Integer>> map,
                     char[] list) {

        // 원하는 개수를 다 뽑았으면?
        if (cnt == goalCnt) {

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < goalCnt; i++) {
                sb.append(list[i]);
            }

            String comb = sb.toString();

            //길이별 맵
            Map<String, Integer> lengthMap = map.get(comb.length());

            if (lengthMap.containsKey(comb)) {
                int val = lengthMap.get(comb);
                lengthMap.put(comb, val + 1);
            } else {
                lengthMap.put(comb, 1);
            }


        } else {
            for (int i = idx; i < order.length(); i++) {
                list[cnt] = order.charAt(i);
                comb(cnt + 1, goalCnt, i + 1, order, map, list);

            }
        }


    }

}
