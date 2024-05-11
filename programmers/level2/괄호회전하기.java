import java.util.Stack;

public class 괄호회전하기 {
    public  int solution(String s) {
        int answer = 0;

        // 초기상태가 유효하면 ? answer을 1로 초기화
        if (isValidParentheses(s)) {
            answer = 1;
        }

        // 왼쪽으로 한칸씩 회전
        for (int i = 1; i < s.length(); i++) {

            s = s.substring(1, s.length()) + s.charAt(0);
            if ( isValidParentheses(s)) {

                answer++;
            }
        }
        return answer;
    }

    static boolean isValidParentheses(String s) {


        boolean result = true;

        Stack<Character> stack = new Stack<>();


        for (int i = 0; i < s.length(); i++) {

            char val = s.charAt(i);
            // 여는 괄호
            if (val == '[' || val == '{' || val == '(') {
                stack.add(val);
            }
            // 닫는 괄호
            else {
                // 닫는 괄호가 나왔는데 앞에 여는 괄호가 없다면 중단
                if (stack.isEmpty()) {
                    result = false;
                    break;
                } else {

                    if ((val == ']' && stack.peek() == '[') || (val == '}' && stack.peek() == '{') || val == ')' && stack.peek() == '(') {
                        stack.pop();
                    } else {

                        result = false;
                        break;
                    }
                }
            }
        }

        if (!stack.isEmpty()) result = false;

        return result;
    }
}
