package programmers.level3;


public class 등굣길 {

  static long[][] dp;
  static long[][] map;

  public static void main(String[] args) {
    int m = 4;
    int n = 4;
    int[][] puddles = {{3, 2}, {2, 4}};
    System.out.println(solution(m, n, puddles));
  }

  public static int solution(int m, int n, int[][] puddles) {

    dp = new long[n][m];
    map = new long[n][m];
    for (int i = 0; i < puddles.length; i++) {

      if (puddles[i].length > 0) {
        int x = puddles[i][1] - 1;
        int y = puddles[i][0] - 1;

        map[x][y] = 1;
      }

    }
    //시작지점은 1로 시작
    dp[0][0] = 1;

    for (int i = 0; i < n; i++) {

      for (int j = 0; j < m; j++) {

        // 웅덩이가 없으면서
        if (map[i][j] == 0) {

          // 이전에도 웅덩이가 아닌경우
          if (j - 1 >= 0 && map[i][j - 1] == 0) {

            dp[i][j] = (dp[i][j] + dp[i][j - 1]) % 1000000007;
          }

          if (i - 1 >= 0 && map[i - 1][j] == 0) {
            dp[i][j] = (dp[i][j] + dp[i - 1][j]) % 1000000007;
          }
        }
        //System.out.print(dp[i][j]+" ");
      }
      //System.out.println();
    }

    int answer = (int) dp[n - 1][m - 1];
    return answer;
  }
}
