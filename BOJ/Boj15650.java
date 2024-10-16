package boj.n과m;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
n과 m (2)



*/
public class Boj15650 {

  static int n;
  static int m;

  static StringBuilder sb = new StringBuilder();
  static int[] selected;

  public static void main(String[] args) throws Exception {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(in.readLine(), " ");
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    selected = new int[m];
    comb(1, 0);
    System.out.println(sb.toString());
  }

  static void comb(int number, int cnt) {
    if (cnt == m) {
      for (int i = 0; i < m; i++) {
        sb.append(selected[i] + " ");
      }
      sb.append("\n");
    } else {

      for (int i = number; i <= n; i++) {

        selected[cnt] = i;
        comb(i + 1, cnt + 1);


      }

    }

  }
}
