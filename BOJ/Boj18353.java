package boj;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
병사 배치하기
 */
public class Boj18353 {

  public static void main(String[] args) throws IOException {

    int[] array;
    int[] dp;

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(in.readLine());
    array = new int[n];
    dp = new int[n];
    int answer = 0;
    StringTokenizer st = new StringTokenizer(in.readLine());

    for (int i = 0; i < n; i++) {
      array[i] = Integer.parseInt(st.nextToken());
    }

    for (int i = 0; i < n; i++) {

      int max = 0;

      for (int j = i - 1; j >= 0; j--) {

        if (array[i] < array[j]) // 앞의 수가 나보다 크거나 같은경우
        {
          max = Integer.max(max, dp[j]);
        }
      }

      dp[i] = max + 1;

      answer = Integer.max(dp[i], answer);

    }
    System.out.println(n - answer);
  }

}
