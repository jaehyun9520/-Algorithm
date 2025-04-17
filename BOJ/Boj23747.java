package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj23747 {

  static int r;
  static int c;
  static char[][] input;
  static boolean[][] visited;
  static char[][] ans;

  static int[] dx = {1, -1, 0, 0};
  static int[] dy = {0, 0, 1, -1};

  public static void main(String[] args) throws Exception {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(in.readLine(), " ");

    r = Integer.parseInt(st.nextToken());
    c = Integer.parseInt(st.nextToken());

    input = new char[r + 1][c + 1];
    visited = new boolean[r + 1][c + 1];
    ans = new char[r + 1][c + 1];

    for (int i = 1; i <= r; i++) {
      Arrays.fill(ans[i], '#'); // 초기에는 다 #로
    }
    for (int i = 1; i <= r; i++) {
      String line = in.readLine();
      for (int j = 1; j <= c; j++) {
        input[i][j] = line.charAt(j - 1);
      }
    }

    st = new StringTokenizer(in.readLine(), " ");

    int sx = Integer.parseInt(st.nextToken());
    int sy = Integer.parseInt(st.nextToken());

    String commandLine = in.readLine();

    for (int i = 0; i < commandLine.length(); i++) {

      char command = commandLine.charAt(i);
      // U, D, L, R, W
      if (command == 'U') {
        sx -= 1;
      }
      if (command == 'D') {
        sx += 1;
      }
      if (command == 'L') {
        sy -= 1;
      }
      if (command == 'R') {
        sy += 1;
      }

      if (command == 'W') {

        bfs(sx, sy);
      }
    }

    ans[sx][sy] = '.';
    for (int i = 0; i <= 3; i++) {

      int nx = sx + dx[i];
      int ny = sy + dy[i];

      if (nx < 1 || nx > r || ny < 1 || ny > c) {
        continue;
      }

      ans[nx][ny] = '.';

    }

    StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= r; i++) {
      for (int j = 1; j <= c; j++) {
        sb.append(ans[i][j]);
      }
      sb.append("\n");
    }

    System.out.println(sb.toString());


  }


  static void bfs(int sx, int sy) {
    if (!visited[sx][sy]) {

      visited[sx][sy] = true;
      ans[sx][sy] = '.';
      Queue<int[]> q = new LinkedList<>();
      char val = input[sx][sy];

      q.add(new int[]{sx, sy});

      while (!q.isEmpty()) {

        int[] loc = q.poll();

        for (int k = 0; k <= 3; k++) {

          int nx = loc[0] + dx[k];
          int ny = loc[1] + dy[k];

          if (nx < 1 || nx > r || ny < 1 || ny > c || visited[nx][ny] || input[nx][ny] != val) {
            continue;
          }
          visited[nx][ny] = true;
          ans[nx][ny] = '.';
          q.add(new int[]{nx, ny});
        }
      }
    }
  }
}
