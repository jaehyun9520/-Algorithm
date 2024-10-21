package boj.n과m;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
n과m(4)

 */
public class Boj15652 {

  static int n;
  static int m;
  static StringBuilder sb = new StringBuilder();
  static int[] selected;

  public static void main(String[] args) throws Exception {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(in.readLine());

    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    selected = new int[m];

    comb(0, 1);
    System.out.println(sb.toString());
  }

  static void comb(int cnt, int number) {

    if (cnt == m) {
      for (int i = 0; i < m; i++) {
        sb.append(selected[i] + " ");
      }
      sb.append("\n");
    } else {
      for (int i = number; i <= n; i++) {
        selected[cnt] = i;
        comb(cnt + 1, i);
      }

    }

  }

}
