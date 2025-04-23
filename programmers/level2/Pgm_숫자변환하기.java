package programmers;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import javax.swing.text.SimpleAttributeSet;

public class Pgm_숫자변환하기 {

  public static void main(String[] args) {

    System.out.println(solution(10, 40, 5));
  }

  static class State {

    int value;
    int cnt;

    State(int value, int cnt) {
      this.value = value;
      this.cnt = cnt;
    }
  }

  public static int solution(int x, int y, int n) {
    int answer = -1;

    if (x == y) {
      answer = 0;
    } else {
      int[] operations = {n, 2, 3};

      // 방문확인용
      boolean[] visited = new boolean[1_000_001];

      Queue<State> q = new LinkedList<>();

      q.add(new State(x, 0));
      boolean success = false;

      while (!q.isEmpty() && !success) {

        State s = q.poll();

        for (int i = 0; i <= 2; i++) {

          int nextValue = s.value;

          if (i < 1) {
            nextValue = s.value + operations[i];
          } else {
            nextValue = s.value * operations[i];
          }
          if (nextValue <= 1000000 && !visited[nextValue]) {
            visited[nextValue] = true;

            if (nextValue == y) {
              answer = s.cnt + 1;
              success = true;
            }
            q.add(new State(nextValue, s.cnt + 1));

          }
        }
      }
    }

    return answer;
  }
}
