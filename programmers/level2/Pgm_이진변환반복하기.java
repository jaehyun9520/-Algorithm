package programmers;


public class Pgm_이진변환반복하기 {

  public static void main(String[] args) {

    int[] solution = solution("01110");
    System.out.println(solution.toString());
  }

  public static int[] solution(String s) {
    int[] answer = {0, 0};
    while (s.length() != 1) {
      answer[0]++;
      int zeroCnt = s.length() - s.replace("0", "").length();
      answer[1] += zeroCnt;
      s = Integer.toBinaryString(s.length() - zeroCnt);
    }

    return answer;
  }
}
