package programmers.level2;

import java.util.HashSet;
import java.util.Set;

public class 연속부분수열합의개수 {

  public static void main(String[] args) {

    int[] elements = {7, 9, 1, 1, 4};
    System.out.println(solution(elements));

  }

  public static int solution(int[] elements) {
    int answer = 0;

    Set<Integer> set = new HashSet<>();

    //시작지점
    for (int i = 0; i < elements.length; i++) {
      int sum = 0;
      for (int j = 0; j < elements.length; j++) {
        sum += elements[(i + j) % elements.length];

        set.add(sum);
      }
    }
    return set.size();
  }
}
