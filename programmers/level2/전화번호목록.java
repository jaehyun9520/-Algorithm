package programmers.level2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;


public class 전화번호목록 {

    public static void main(String[] args) {

        System.out.println(solution(new String[]{"12", "123", "1235", "567", "88"}));
    }

    public static boolean solution(String[] phone_book) {
        boolean answer = true;

        Arrays.sort(phone_book, new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {

                return s.length() - t1.length();
            }
        });

        Set<String> set = new HashSet<>();

        loop:
        for (int i = 0; i < phone_book.length; i++) {

            set.add(phone_book[i]);
            for (int j = 1; j < phone_book[i].length(); j++) {

                String subString = phone_book[i].substring(0, j);

                if (set.contains(subString)) {
                    answer = false;
                    break loop;
                }


            }
        }


        return answer;
    }
}
