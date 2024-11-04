package boj.n과m;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
n과 m(8)
 */
public class Boj15657 {

  static int n;
  static int m;
  static StringBuilder sb = new StringBuilder();

  static int[] input;
  static int[] selected;

  public static void main(String[] args) throws Exception {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(in.readLine());

    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    input = new int[n];

    st = new StringTokenizer(in.readLine());
    for (int i = 0; i < n; i++) {
      input[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(input);
    selected = new int[m];

    permutation(0, 0);

    System.out.println(sb.toString());
  }


  static void permutation(int cnt, int index) {

    if (cnt == m) {
      for (int i = 0; i < m; i++) {

        sb.append(selected[i] + " ");
      }
      sb.append("\n");
    } else {

      for (int i = index; i < n; i++) {

        selected[cnt] = input[i];
        permutation(cnt + 1, i);

      }
    }
  }
}
