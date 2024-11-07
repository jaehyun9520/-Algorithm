package programmers.level2;


import java.util.HashMap;
import java.util.Map;

public class 방문길이 {

  public static void main(String[] args) {

    System.out.println(solution("LULLLLLLU"));

  }


  public static int solution(String dirs) {

    int answer = 0;

    // U D R L
    int dx[] = {-1, 1, 0, 0};
    int dy[] = {0, 0, 1, -1};

    Map<Character, Integer> map = new HashMap<>();
    map.put('U', 0);
    map.put('D', 1);
    map.put('R', 2);
    map.put('L', 3);

    boolean[][][][] visited = new boolean[11][11][11][11]; // 0~10까지의 이동좌표와 어떤 방향으로 방문됐는지

    int x = 5;
    int y = 5;

    for (int i = 0; i < dirs.length(); i++) {

      int dirNum = map.get(dirs.charAt(i));
      int nx = x + dx[dirNum];
      int ny = y + dy[dirNum];

      if (0 <= nx && nx <= 10 && 0 <= ny && ny <= 10) {

        if (!visited[x][y][nx][ny]) {
          visited[x][y][nx][ny] = true;
          visited[nx][ny][x][y] = true;
          answer++;
        }
        x = nx;
        y = ny;
      }
    }
    return answer;
  }
}
