package programmers.level2;

import java.util.Stack;

public class 괄호변환 {

  public static void main(String[] args) {

    System.out.println(solution("()))((()"));
  }

  public static String solution(String p) {
    String answer = process(p);
    return answer;
  }

  public static String process(String p) {

    if (p.equals("")) {
      return p;
    }

    String u = "";
    String v = "";

    // 열리는 괄호의 개수
    int openCnt = 0;
    int closeCnt = 0;

    // u와 v로 분리
    for (int i = 0; i < p.length(); i++) {

      char c = p.charAt(i);

      if (c == '(') {
        openCnt++;
      } else {
        closeCnt++;
      }

      if (openCnt == closeCnt) {
        u = p.substring(0, i + 1);
        v = p.substring(i + 1, p.length());
        break;
      }
    }

    // u가 올바른 괄호 문자열인지 확인
    if (isValid(u)) {
      v = process(v);
      return u + v;
    }

    // 올바른 괄호 문자열이 아니라면?
    else {
      String newS = "(" + process(v) + ")";
      for (int i = 1; i < u.length() - 1; i++) {
        char c = u.charAt(i);
        newS += (c == '(' ? ')' : '(');
      }
      return newS;
    }
  }


  // 올바른 괄호 문자열인지 확인
  private static boolean isValid(String u) {

    Stack<Character> s = new Stack<>();
    // ( 면 넣어주고  ) 를 만나면 빼준다

    for (int i = 0; i < u.length(); i++) {

      char c = u.charAt(i);

      if (c == '(') {
        s.add(c);
      } else {
        if (s.size() == 0) {
          return false;
        }
        s.pop();
      }
    }
    if (s.size() != 0) {
      return false;
    }
    return true;
  }

}
