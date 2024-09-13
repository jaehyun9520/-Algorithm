package programmers.level3;

import java.util.Arrays;

public class 자물쇠와열쇠 {

  public static void main(String[] args) {

    int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
    int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
    boolean solution = solution(key, lock);
    System.out.println(solution);
  }

  public static boolean solution(int[][] key, int[][] lock) {
    boolean answer = false;

    // 자물쇠를 편하게 놓아볼 맵
    int[][] map = new int[key.length + lock.length * 2 - 2][key.length + lock.length * 2 - 2];

    // map을 3으로 채워준다
    for (int i = 0; i < map.length; i++) {
      Arrays.fill(map[i], 3);
    }

    // 자물쇠 홈의 총 개수
    int totalCnt = 0;
    // map 중간에 자물쇠를 위치시킨다

    for (int i = key.length - 1; i < key.length - 1 + lock.length; i++) {
      for (int j = key.length - 1; j < key.length - 1 + lock.length; j++) {
        map[i][j] = lock[i - (key.length - 1)][j - (key.length - 1)];
        // 홈 개수 카운트
        if (map[i][j] == 0) {
          totalCnt++;
        }
      }
    }
    // 총 4번 확인하고 그 중에 3번을 회전해서 확인
    loop:
    for (int cnt = 1; cnt <= 4; cnt++) {

      // 열쇠를 이동시키며 자물쇠의 홈을 다 채울 수 있는지 확인

      for (int i = 0; i < map.length; i++) {
        for (int j = 0; j < map.length; j++) {

          if (validationKeyCheck(map, key, i, j, totalCnt)) {
            answer = true;
            break loop;
          }

        }
      }
      // 불가능하다면 열쇠를 90도 회전 (90도, 180도, 270도)
      if (cnt <= 3) {
        key = rotateLock(key);
      }
    }

    return answer;
  }

  private static boolean validationKeyCheck(int[][] map, int[][] key, int si, int sj,
      int totalCnt) {

    int cnt = 0;

    // 열쇠와 자물쇠의 돌기가 만나면 안된다 ,  자물쇠의 홈은 다 열쇠의 돌기로 채워야 한다
    for (int i = si; i < map.length && i < si + key.length; i++) {
      for (int j = sj; j < map.length && j < sj + key.length; j++) {

        // 홈이라면?
        if (map[i][j] == 0 && key[i - si][j - sj] == 1) {
          cnt++;
        } else if (map[i][j] == 1 && key[i - si][j - sj] == 1) {
          return false;
        }
      }
    }

    if (cnt == totalCnt) {
      return true;
    } else {
      return false;
    }

  }

  private static int[][] rotateLock(int[][] key) {

    int[][] nextState = new int[key.length][key.length];

    for (int i = 0; i < key.length; i++) {
      for (int j = 0; j < key.length; j++) {

        nextState[j][key.length - 1 - i] = key[i][j];
      }
    }
    return nextState;
  }
}
