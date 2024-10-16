package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class Boj1351 {

  static Map<Long, Long> map = new HashMap<>();

  public static void main(String[] args) throws Exception {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(in.readLine(), " ");

    long n = Long.parseLong(st.nextToken());
    long p = Long.parseLong(st.nextToken());
    long q = Long.parseLong(st.nextToken());

    map.put(0L, 1L); // A0은 1
    System.out.println(topDown(n, p, q));
  }


  static long topDown(long n, long p, long q) {

    if (map.containsKey(n)) {
      return map.get(n);
    } else {
      map.put(n, topDown(n / p, p, q) + topDown(n / q, p, q));
      return map.get(n);
    }


  }

}
