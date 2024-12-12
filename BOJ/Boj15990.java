package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj15990 {


  public static void main(String[] args) throws IOException {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int t = Integer.parseInt(in.readLine());

    long[][] dp = new long[100001][4];

    dp[1][1] = 1;
    dp[2][2] = 1;
    dp[3][3] = 1;
    dp[3][1] = 1;
    dp[3][2] = 1;

    for (int i = 4; i <= 100000; i++) {
      dp[i][1] = (dp[i - 1][2] + dp[i - 1][3]) % 1000000009;
      dp[i][2] = (dp[i - 2][1] + dp[i - 2][3]) % 1000000009;
      dp[i][3] = (dp[i - 3][1] + dp[i - 3][2]) % 1000000009;
    }

    for (int i = 1; i <= t; i++) {

      int num = Integer.parseInt(in.readLine());

      sb.append((dp[num][1] + dp[num][2] + dp[num][3]) % 1000000009 + "\n");

    }

    System.out.println(sb.toString());
  }
}
