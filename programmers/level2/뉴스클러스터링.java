package programmers;

import java.util.HashMap;
import java.util.Map;


public class 뉴스클러스터링 {

    public static void main(String[] args) {

        System.out.println(solution("FRANCE", "french"));
    }

    public static int solution(String str1, String str2) {


        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();
        Map<String, Integer> map = new HashMap<>();

        int number = 1;
        for (int i = 'A'; i <= 'Z'; i++) {
            for (int j = 'A'; j <= 'Z'; j++) {
                map.put("" + (char) i + (char) j, number++);
            }
        }

        int[] str1Info = new int[number];
        int[] str2Info = new int[number];


        // str1 두글자씩 끊어서 원소 생성
        createElement(str1Info, str1, map);
        createElement(str2Info, str2, map);

        // 교집합
        int val1 = 0;

        //  합집합
        int val2 = 0;

        for (int i = 1; i < number; i++) {

            // 둘 다 있으면 교집합으로 더 작은거
            if (str1Info[i] != 0 && str2Info[i] != 0) {

                val1 += (Integer.min(str1Info[i], str2Info[i]));
                val2 += (Integer.max(str1Info[i], str2Info[i]));
            }

            // 둘 중 하나만 있다면?
            else {
                val2 += (Integer.max(str1Info[i], str2Info[i]));
            }
        }
        int answer = 65536;
        if (val2 != 0)
            answer = val1 * 65536 / val2;

        return answer;
    }

    static void createElement(int[] strInfo, String str, Map<String, Integer> map) {


        for (int i = 0; i < str.length() - 1; i++) {

            String subString = str.charAt(i) + "" + str.charAt(i + 1);
            char first = subString.charAt(0);
            char second = subString.charAt(1);
            if (('A' <= first && first <= 'Z') && ('A' <= second && second <= 'Z')) {
                strInfo[map.get(subString)]++;
            }
        }
    }
}
