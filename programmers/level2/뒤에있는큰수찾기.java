package programmers.level2;

import java.util.Arrays;
import java.util.Stack;

public class 뒤에있는큰수찾기 {
    public static void main(String[] args) {


        System.out.println(Arrays.toString(solution(new int[]{9, 1, 5, 3, 6, 2})));


    }
    public static int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < numbers.length; i++) {
            int num = numbers[i];
            while (!s.isEmpty()) {
                if (numbers[s.peek()] < num) {
                    answer[s.pop()] = num;
                } else {
                    break;
                }
            }
            s.add(i);
        }

        while (!s.isEmpty()) {
            answer[s.pop()] = -1;
        }
        return answer;
    }
}
