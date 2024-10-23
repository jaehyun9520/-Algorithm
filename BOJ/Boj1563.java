package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
O를 출석, L을 지각, A를 결석        dp[i 일자] [j 여태까지 한 지각 횟수 0~1] [ k 오늘을 포함해서 연속해서 한 결석 횟수 0~2]
*/
public class Boj1563 {

  public static void main(String[] args) throws Exception {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(in.readLine());

    long[][][] dp = new long[n + 1][2][3];

    //첫번째 날 초기 세팅   (O를 출석, L을 지각, A를 결석)
    dp[1][0][0] = 1; //출석
    dp[1][1][0] = 1; //오늘 지각
    dp[1][0][1] = 1; // 오늘 결석

    // 1,000,000 로 나눈 나머지들만 가지고 있어야 한다
    for (int i = 2; i <= n; i++) {

      // (1) 지각을 한번도 안함

      //여태 지각을 한번도 안했고 이번에 결석도 안함
      dp[i][0][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % 1000000;

      //여태 지각을 한번도 안했고 이번에 결석을 한번함
      dp[i][0][1] = dp[i - 1][0][0];

      //여태 지각을 한번도 안했고 이번에 결석을 해서 연속 결속을 두번함
      dp[i][0][2] = dp[i - 1][0][1];

      // (2) 지각을 이번에 or 이전에 한번 함

      /*
      오늘을 포함해서 지각을 한번 했고 이번에 결석 안함

      dp[i-1][1][0] + dp[i-1][1][1] + dp[i-1][1][2] 이전에 이미 지각을 한번 했고 오늘은 안함
             dp[i - 1][0][1] + dp[i - 1][0][2] 이전까지 지각을 한번도 안했고 오늘 함
       */
      dp[i][1][0] =
          (dp[i - 1][1][0] + dp[i - 1][1][1] + dp[i - 1][1][2] + dp[i - 1][0][0] + dp[i - 1][0][1]
              + dp[i - 1][0][2]) % 1000000;

      // 어제까지 해서 지각을 한번 했고 이번에 연속해서 한번 결석함
      dp[i][1][1] = dp[i - 1][1][0];

      //어제까지 해서 지각을 한번 했고 이번에 연속해서 결석을 두번함
      dp[i][1][2] = dp[i - 1][1][1];

    }

    long ans = 0;
    for (int i = 0; i <= 1; i++) {
      for (int j = 0; j <= 2; j++) {

        ans = (ans + dp[n][i][j]) % 1000000;
      }
    }
    System.out.println(ans);
  }

}
