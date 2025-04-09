package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16973 {

  public static void main(String[] args) throws Exception {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(in.readLine(), " ");

    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int[][] map = new int[n + 1][m + 1];
    int[][] dp = new int[n + 1][m + 1];
    boolean[][] visited = new boolean[n + 1][m + 1];
    for (int i = 1; i <= n; i++) {
      st = new StringTokenizer(in.readLine(), " ");
      for (int j = 1; j <= m; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        dp[i][j] = map[i][j] + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
      }
    }
    // H W  SX SY  EX EY
    st = new StringTokenizer(in.readLine(), " ");

    int h = Integer.parseInt(st.nextToken());
    int w = Integer.parseInt(st.nextToken());
    int sx = Integer.parseInt(st.nextToken());
    int sy = Integer.parseInt(st.nextToken());
    int ex = Integer.parseInt(st.nextToken());
    int ey = Integer.parseInt(st.nextToken());
    int ans = -1;
    // x,y,이동횟수

    Queue<int[]> q = new LinkedList<>();
    visited[sx][sy] = true;
    q.add(new int[]{sx, sy, 0});

    loop:
    while (!q.isEmpty()) {

      int[] arr = q.poll();

      for (int i = 0; i <= 3; i++) {

        int nx = arr[0] + dx[i];
        int ny = arr[1] + dy[i];

        int tx = nx + h - 1;
        int ty = ny + w - 1;

        if (nx < 1 || nx > n || ny < 1 || ny > m || tx < 1 || tx > n || ty < 1 || ty > m) {
          continue;
        }

        int cnt = dp[tx][ty] - dp[nx - 1][ty] - dp[tx][ny - 1] + dp[nx - 1][ny - 1];

        if (!visited[nx][ny] && cnt == 0) {
          visited[nx][ny] = true;

          if (nx == ex && ny == ey) {
            ans = arr[2] + 1;
            break loop;
          }
          q.add(new int[]{nx, ny, arr[2] + 1});


        }


      }


    }

    System.out.println(ans);


  }


}
