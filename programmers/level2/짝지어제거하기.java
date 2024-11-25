package programmers.level2;

import java.util.Stack;


public class 짝지어제거하기 {

  public static void main(String[] args) {

    System.out.println(solution("cdcd"));

  }

  public static int solution(String s) {
    int answer = 0;

    Stack<Character> stack = new Stack<>();

    for (int i = 0; i < s.length(); i++) {

      // 비어있으면 넣어준다
      if (stack.empty()) {
        stack.add(s.charAt(i));
      } else {

        // 둘이 같으면 제거해준다
        if (stack.peek() == s.charAt(i)) {
          stack.pop();
        }
        //둘이 다르면 i번째 단어를 스택에 넣어준다
        else {
          stack.add(s.charAt(i));
        }
      }
    }

    if (stack.empty()) {
      answer = 1;
    }

    return answer;
  }

}
