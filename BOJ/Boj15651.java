package boj.n과m;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
n과 m (3)
 */
public class Boj15651 {

  static int n;
  static int m;

  static int[] selected;
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws Exception {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(in.readLine(), " ");

    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    selected = new int[m];

    permutation(0);

    System.out.println(sb.toString());
  }

  static void permutation(int cnt) {

    if (cnt == m) {

      for (int i = 0; i < m; i++) {
        sb.append(selected[i] + " ");
      }
      sb.append("\n");
    } else {
      for (int i = 1; i <= n; i++) {
        selected[cnt] = i;
        permutation(cnt + 1);
      }
    }
  }
}
