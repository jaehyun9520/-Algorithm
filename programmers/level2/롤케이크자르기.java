package programmers.level2;

import java.util.HashSet;
import java.util.Set;


public class 롤케이크자르기 {
    public static void main(String[] args) {

        System.out.println(solution(new int[]{1, 2, 3, 1, 4}));

    }

    public static int solution(int[] topping) {
        int answer = 0;
        int[] front = new int[topping.length];
        int[] back = new int[topping.length];

        Set<Integer> frontSet = new HashSet<>();
        Set<Integer> backSet = new HashSet<>();

        for (int i = 0; i < topping.length; i++) {

            int number = topping[i];

            if (!frontSet.contains(number)) {
                frontSet.add(number);
            }
            front[i] = frontSet.size();
        }

        for (int i = topping.length - 1; i >= 0; i--) {
            int number = topping[i];

            if (!backSet.contains(number)) {
                backSet.add(number);
            }
            back[i] = backSet.size();
        }


        for (int i = 0; i < topping.length - 1; i++) {

            if (front[i] == back[i + 1]) {
                answer++;
            }

        }

        return answer;
    }

}
