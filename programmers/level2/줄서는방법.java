package programmers.level2;

import java.util.Arrays;


public class 줄서는방법 {

  static boolean[] used;
  static int[] ansList;


  public static void main(String[] args) {

    System.out.println(Arrays.toString(solution(20, 2432902008176640000L)));

  }

  public static int[] solution(int n, long k) {
    int[] answer = {};

    used = new boolean[n + 1];
    ansList = new int[n + 1];

    // 전체 개수, k번째 수 , 현재 위치, 선택해야 하는 자리수
    simulation(n, k, 1, 1);
    return answer = Arrays.copyOfRange(ansList, 1, ansList.length);

  }


  // 전체 사람수 , n명을 나열하는 k번째수 , 현재 위치, 선택해야 하는 자리수
  static void simulation(int n, long k, long loc, int number) {

    // 마지막 자리수만 구하면 된다면? 현재 남은 번호를 추가해준다
    if (number == n) {

      for (int i = 1; i <= n; i++) {
        if (!used[i]) {
          ansList[n] = i;
          used[i] = true;
        }
      }

      return;
    }

    // 팩토리얼 구하기
    long sum = 1;
    for (int i = 1; i <= n - number; i++) {
      sum *= i;
    }

    long start = loc;

    // 1번째 자리의 숫자를 구한다면  (n-1)! 만큼  시작점에서 증가시켜주면서 k가 속하는 범위를 확인해준다
    loop:
    for (int i = 1; i <= n; i++) {

      // 이전에 사용된 숫자를 사용하면 안됨
      if (!used[i]) {
        // k가 해당 범위에 속한다면
        if (start <= k && k <= start + sum - 1) {
          used[i] = true;
          ansList[number] = i;
          simulation(n, k, start, number + 1);
          break loop;
        } else {
          start += sum;

        }
      }
    }
  }
}
