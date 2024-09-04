package programmers.level4;

import java.util.Arrays;

public class 징검다리 {

  public static void main(String[] args) {

    // 25	[2, 14, 11, 21, 17]	2

    int distance = 25;
    int[] rocks = {2, 14, 11, 21, 17};
    int n = 2;

    System.out.println(solution(distance, rocks, n));
  }

  public static int solution(int distance, int[] rocks, int n) {

    int answer = 0;

    int low = 1;
    int high = distance;
    int mid = 0;

    Arrays.sort(rocks);

    while (low <= high) {

      mid = (low + high) / 2;

      // 현재 정한 최솟값보다 다 크거나 같다면 정답 후보로 두고 다음 범위를 확인한다
      if ((check(distance, mid, rocks, n))) {

        answer = mid;
        low = mid + 1;
      } else {
        high = mid - 1;
      }

    }

    return answer;
  }

  private static boolean check(int distance, int mid, int[] rocks, int n) {

    int cnt = 0;
    int pre = 0;
    for (int i = 0; i < rocks.length; i++) {

      int now = rocks[i];
      // 두 지점의 거리가 최솟값 이상이라면?
      if (now - pre >= mid) {
        pre = now;
      }
      // 두 지점의 거리가 최솟값 미만이라면? 해당 바위는 제거해야 한다
      else {
        cnt++;
      }

    }
    // 마지막 바위와 도착지점이 mid 미만이라면? 마지막 바위도 제거
    if (distance - pre < mid) {
      cnt++;
    }

    if (cnt > n) {
      return false;
    }

    return true;


  }


}
