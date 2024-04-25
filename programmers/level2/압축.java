import java.util.*;

public class 압축 {
    public int[] solution(String msg) {


        List<Integer> ansList = new ArrayList<>();
        int indexNumber = 27;
        Map<String, Integer> map = new HashMap<>();

        for (int i = 65; i <= 90; i++) {
            map.put((char) i + "", i - 64);
        }


        String subString = "";
        int nowIndex = 0;
        for (int i = 0; i < msg.length(); i++) {

            subString += msg.charAt(i);


            // 해당 부분문자열을 이미 사전에서 가지고 있다면?
            if (map.containsKey(subString)) {

                nowIndex = map.get(subString);
            } else {
                //w+c 를 새로 사전에 등록하고 c부터 다시 시작
                ansList.add(nowIndex);
                map.put(subString, indexNumber++);

                subString = "" + msg.charAt(i);
                nowIndex = map.get(subString);
            }

            if (i == msg.length() - 1) ansList.add(nowIndex);

        }

        int[] answer = new int[ansList.size()];

        for (int i = 0; i < ansList.size(); i++) {
            answer[i] = ansList.get(i);
        }

        return answer;
    }
}
