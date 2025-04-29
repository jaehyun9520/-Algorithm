package programmers;


public class JadenCase문자열만들기 {


  public static void main(String[] args) {

    System.out.println(solution("for  the last week"));
  }

  public static String solution(String s) {
    StringBuilder sb = new StringBuilder();
    boolean flag = true;

    for (int i = 0; i < s.length(); i++) {
      char c = Character.toLowerCase(s.charAt(i));

      if (c == ' ') {
        flag = true;
      } else {
        if (flag) {
          flag = false;
          if ('a' <= c && c <= 'z') {
            c = Character.toUpperCase(c);
          }
        }
      }
      sb.append(c);
    }

    return sb.toString();
  }
}
