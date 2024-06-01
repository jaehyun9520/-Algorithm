package programmers.level2;
import java.util.Stack;

public class 택배상자 {

    public static void main(String[] args) {

        System.out.println(solution(new int[]{5, 4, 3, 2, 1}));
    }

    public static int solution(int[] order) {

        int answer = 0;
        // 임시컨테이너
        Stack<Integer> s = new Stack<>();

        int number = 1; //기존 컨테이너 가장 앞에 있는 상자 번호


        for (int i = 0; i < order.length; i++) {

            boolean flag = false;
            // 임시 컨테이너가 비어있지 않으면서 현재 원하는 상자가 맨 앞에 있다면?
            if (!s.isEmpty() && s.peek() == order[i]) {

                flag = true;
                s.pop();
            }

            // 현재 원하는 번호를 컨테이너에서 꺼낼 수 있는 경우
            else if (number <= order[i]) {

                while (number != order[i]) {
                    s.add(number);
                    number++;
                }
                number++;
                flag = true;
            }

            if (flag) {
                answer++;
            } else {
                break;
            }
        }
        return answer;
    }
}
