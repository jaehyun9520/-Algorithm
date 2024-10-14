package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2249 {

  public static void main(String[] args) throws Exception {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(in.readLine(), " ");

    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());

    int[] coinList = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      coinList[i] = Integer.parseInt(in.readLine());
    }
    int[][] dp = new int[n + 1][k + 1];
    for (int i = 0; i <= n; i++) {
      Arrays.fill(dp[i], Integer.MAX_VALUE);
      dp[i][0] = 0;
    }

    // 1개 2개 3개 4개 5개 6개
    for (int i = 1; i <= n; i++) {
      // 사용할 동전
      int coin = coinList[i];
      for (int j = 1; j <= k; j++) {
        dp[i][j] = dp[i - 1][j];
        if (j >= coin && dp[i][j - coin] != Integer.MAX_VALUE) {
          dp[i][j] = Integer.min(dp[i][j], 1 + dp[i][j - coin]);
        }
      }
    }
    System.out.println(dp[n][k] == Integer.MAX_VALUE ? "-1" : dp[n][k]);
  }

}