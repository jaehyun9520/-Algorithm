package boj;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj12026 {

  public static void main(String[] args) throws Exception {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(in.readLine());

    char[] map = new char[n + 1];
    int[] dp = new int[n + 1];

    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[1] = 0;

    String input = in.readLine();
    for (int i = 1; i <= n; i++) {
      map[i] = input.charAt(i - 1);
    }

    for (int i = 2; i <= n; i++) {

      char val = map[i];

      for (int j = i - 1; j >= 1; j--) {

        // 애는 도달하지 못했다는거니까 패스
        if (dp[j] == Integer.MAX_VALUE) {
          continue;
        }

        if (val == 'B' && map[j] == 'J') {
          dp[i] = Integer.min(dp[j] + (int) Math.pow(i - j, 2), dp[i]);
        } else if (val == 'O' && map[j] == 'B') {
          dp[i] = Integer.min(dp[j] + (int) Math.pow(i - j, 2), dp[i]);
        } else if (val == 'J' && map[j] == 'O') {
          dp[i] = Integer.min(dp[j] + (int) Math.pow(i - j, 2), dp[i]);
        }
      }


    }

    System.out.println(dp[n] == Integer.MAX_VALUE ? -1 : dp[n]);
  }
}
