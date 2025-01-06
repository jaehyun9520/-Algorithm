package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Boj14395 {
  
  static class State {

    long number;
    String operators;


    State(long number, String operators) {
      this.number = number;
      this.operators = operators;
    }
  }

  static String[] operationArray = {"*", "+", "-", "/"};
  static Set<Long> set = new HashSet<>(); //number 중복여부 체크

  public static void main(String[] args) throws Exception {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(in.readLine());

    int s = Integer.parseInt(st.nextToken());
    int t = Integer.parseInt(st.nextToken());

    System.out.println(bfs(s, t));
  }

  static String bfs(int start, int end) {

    String ans = "-1";

    if (start == end) {
      return "0";
    }

    Queue<State> q = new LinkedList<>();

    q.add(new State(start, ""));

    while (!q.isEmpty()) {

      State state = q.poll();

      for (String operation : operationArray) {

        long next = 0;
        if (operation.equals("+")) {
          next = state.number + state.number;
        } else if (operation.equals("-")) {
          next = state.number - state.number;
        } else if (operation.equals("*")) {
          next = state.number * state.number;
        } else if (state.number != 0) {
          next = state.number / state.number;
        }
        if (!set.contains(next)) {
          set.add(next);
          if (next == end) {
            return state.operators + operation;
          } else {
            q.add(new State(next, state.operators + operation));
          }
        }
      }
    }
    return ans;
  }

}
