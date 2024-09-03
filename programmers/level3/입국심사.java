package programmers.level3;


public class 입국심사 {


  public static void main(String[] args) {
    //6	[7, 10]	28
    int n = 6;
    int[] times = {7, 10};

    System.out.println(solution(n, times));

  }


  public static long solution(int n, int[] times) {
    long answer = 0;

    long low = 1;
    long high = 0;
    long mid = 0;
    // 이분탐색의 최대범위를 지정해준다 (심사가 가장 오래 걸리는 심사대를 찾고 * n명)
    for (int i = 0; i < times.length; i++) {
      if (high < times[i] * n) {
        high = (long) times[i] * n;
      }
    }

    while (low <= high) {

      mid = (low + high) / 2;

      // 현재 확인한 시간(mid)으로 n명을 모두 다 입국심사 받을 수 있다
      if (simulation(mid, n, times)) {
        answer = mid;
        high = mid - 1;
      } else {

        low = mid + 1;
      }
    }
    return answer;
  }

  private static boolean simulation(long mid, int n, int[] times) {

    long cnt = 0;

    for (int i = 0; i < times.length; i++) {
      cnt += (mid / times[i]);
      if (cnt >= n) {
        return true;
      }
    }

    return false;


  }
}
