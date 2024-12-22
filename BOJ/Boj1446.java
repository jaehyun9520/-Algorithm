package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Boj1446 {

// 풀이 방법 1. dp
  public static void main(String[] args) throws Exception {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(in.readLine(), " ");

    int cnt = Integer.parseInt(st.nextToken());
    int highway = Integer.parseInt(st.nextToken());

    // dp[n] => 고속도로 n까지 도달했을때 운전해야 하는 최솟값
    int[] dp = new int[highway + 1];

    // 여기에 정보를 담아두고 쓰면 될듯
    Map<Integer, List<int[]>> shortCutMap = new HashMap<>();

    for (int i = 1; i <= cnt; i++) {

      st = new StringTokenizer(in.readLine(), " ");

      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int length = Integer.parseInt(st.nextToken());

      if (!shortCutMap.containsKey(end)) {
        ArrayList<int[]> list = new ArrayList<>();
        list.add(new int[]{start, length});
        shortCutMap.put(end, list);
      } else {
        List<int[]> list = shortCutMap.get(end);
        list.add(new int[]{start, length});
      }
    }

    for (int i = 1; i <= highway; i++) {

      dp[i] = dp[i - 1] + 1;

      if (shortCutMap.containsKey(i)) {

        List<int[]> list = shortCutMap.get(i);

        for (int j = 0; j < list.size(); j++) {

          int start = list.get(j)[0];
          int length = list.get(j)[1];
          dp[i] = Integer.min(dp[i], dp[start] + length);
        }
      }
    }

    System.out.println(dp[highway]);

  }
}



// 풀이방법 2 완전탐색

public class Boj1446 {

  static int highwayLength;
  static Map<Integer, List<int[]>> shortcutMap;
  static int ans = Integer.MAX_VALUE;

  public static void main(String[] args) throws Exception {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(in.readLine(), " ");

    // 지름길 개수와 고속도로 길이
    int cnt = Integer.parseInt(st.nextToken());
    highwayLength = Integer.parseInt(st.nextToken());

    shortcutMap = new HashMap<>();

    for (int i = 1; i <= cnt; i++) {

      st = new StringTokenizer(in.readLine(), " ");

      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int length = Integer.parseInt(st.nextToken());

      if (!shortcutMap.containsKey(start)) {
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{end, length});
        shortcutMap.put(start, list);
      } else {
        shortcutMap.get(start).add(new int[]{end, length});
      }
    }
    // 완전탐색 시작 ( 현재 고속도로 위치, 목표 고속도로 위치, 실제 운전해야 하는 거리)
    dfs(0, 0);

    System.out.println(ans);
  }

  static void dfs(int loc, int len) {

    if (len > highwayLength) {
      return;
    }

    if (loc == highwayLength) {
      ans = Integer.min(len, ans);
      return;
    }

    dfs(loc + 1, len + 1);

    // 현재 위치에서 갈 수 있는 지름길이 존재한다면?
    if (shortcutMap.containsKey(loc)) {

      List<int[]> list = shortcutMap.get(loc);

      for (int[] info : list) {
        dfs(info[0], len + info[1]);
      }
    }
  }
}

// 풀이방법 3 다익스트라
public class Boj1446 {


  static class State implements Comparable<State> {

    int loc;
    int length;


    State(int loc, int length) {
      this.loc = loc;
      this.length = length;
    }

    @Override
    public int compareTo(State s) {
      return this.length - s.length;
    }
  }


  public static void main(String[] args) throws Exception {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(in.readLine());

    int shortcutCnt = Integer.parseInt(st.nextToken());
    int highwayLen = Integer.parseInt(st.nextToken());

    Map<Integer, List<int[]>> shortcutMap = new HashMap<>();

    for (int i = 1; i <= shortcutCnt; i++) {

      st = new StringTokenizer(in.readLine());

      int s = Integer.parseInt(st.nextToken());
      int e = Integer.parseInt(st.nextToken());
      int len = Integer.parseInt(st.nextToken());

      if (!shortcutMap.containsKey(s)) {

        List<int[]> list = new ArrayList<>();
        list.add(new int[]{e, len});
        shortcutMap.put(s, list);
      } else {
        List<int[]> list = shortcutMap.get(s);
        list.add(new int[]{e, len});
      }
    }

    // distance 다 Integer.MAX_VALUE 로 초기화

    int[] distance = new int[highwayLen + 1];

    Arrays.fill(distance, Integer.MAX_VALUE);

    PriorityQueue<State> pq = new PriorityQueue<>();

    pq.add(new State(0, 0));

    while (!pq.isEmpty()) {

      State s = pq.poll();

      // 아직 고속도로 안이라면?
      if (s.loc <= highwayLen && distance[s.loc] == Integer.MAX_VALUE) {
        distance[s.loc] = s.length;
        pq.add(new State(s.loc + 1, distance[s.loc] + 1));

        // 현재 위치에서 갈 수 있는 지름길이 존재한다면 확인
        if (shortcutMap.containsKey(s.loc)) {

          List<int[]> list = shortcutMap.get(s.loc);

          for (int[] info : list) {
            pq.add(new State(info[0], distance[s.loc] + info[1]));
          }


        }


      }


    }

    System.out.println(distance[highwayLen]);


  }
}


