package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj3987 {


  // 위 오른쪽 아래 왼쪽  (0,1,2,3)
  static int dx[] = {-1, 0, 1, 0};
  static int dy[] = {0, 1, 0, -1};

  static String[] dirChar = {"U", "R", "D", "L"};
  static char[][] board;
  static boolean[][][] visited;
  static int n, m, cnt;
  static int[][] dirChange = {{1, 0, 3, 2}, {3, 2, 1, 0}};  // [0][0~3] 일때는  /   [1][0~3] 일때는 \

  static String ans = "";

  public static void main(String[] args) throws Exception {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(in.readLine(), " ");
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    board = new char[n + 1][m + 1];

    for (int i = 1; i <= n; i++) {

      String input = in.readLine();

      for (int j = 1; j <= m; j++) {
        board[i][j] = input.charAt(j - 1);
      }

    }

    st = new StringTokenizer(in.readLine(), " ");
    int sx = Integer.parseInt(st.nextToken());
    int sy = Integer.parseInt(st.nextToken());

    int max = 0;
    // 위 오른쪽 왼쪽 아래순으로 이동
    for (int i = 0; i <= 3; i++) {
      cnt = 0;
      visited = new boolean[4][n + 1][m + 1];
      visited[i][sx][sy] = true;
      dfs(sx, sy, i, 0);

      if (max < cnt) {
        max = cnt;
        ans = dirChar[i];
      }
    }

    System.out.println(ans);

    if (max == Integer.MAX_VALUE) {
      System.out.println("Voyager");
    } else {
      System.out.println(max);
    }

  }

  static void dfs(int x, int y, int dir, int moveCnt) {

    int nx = x + dx[dir];
    int ny = y + dy[dir];

    if (nx < 1 || nx > n || ny < 1 || ny > m || board[nx][ny] == 'C') {
      cnt = moveCnt + 1;
      return;
    }

    if (board[nx][ny] == '/') {
      dir = dirChange[0][dir];
    } else if (board[nx][ny] == '\\') {
      dir = dirChange[1][dir];
    }

    if (visited[dir][nx][ny] == true) {
      cnt = Integer.MAX_VALUE; // 무한히 전파 가능
      return;
    }

    visited[dir][nx][ny] = true;
    dfs(nx, ny, dir, moveCnt + 1);


  }
}