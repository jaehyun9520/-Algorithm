package programmers.level1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 공원 {

  public static void main(String[] args) {

    int[] mats = {5, 3, 2};
    String[][] park = {{"A", "A", "-1", "B", "B", "B", "B", "-1"},
        {"A", "A", "-1", "B", "B", "B", "B", "-1"},
        {"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"},
        {"D", "D", "-1", "-1", "-1", "-1", "E", "-1"},
        {"D", "D", "-1", "-1", "-1", "-1", "-1", "F"}, {"D", "D", "-1", "-1", "-1", "-1", "E", "-1"}
    };

    System.out.println(solution(mats, park));
  }

  public static int solution(int[] mats, String[][] park) {
    int answer = -1;

    for (int i = 0; i < mats.length; i++) {

      if (simulation(mats[i], park)) {

        answer = Integer.max(answer, mats[i]);
      }

    }

    return answer;
  }

  private static boolean simulation(int mat, String[][] park) {

    for (int i = 0; i < park.length; i++) {
      for (int j = 0; j < park[0].length; j++) {

        boolean result = true;
        for (int k = i; k < i + mat; k++) {
          for (int z = j; z < j + mat; z++) {

            if (k >= park.length || z >= park[0].length || !park[k][z].equals("-1")) {

              result = false;
            }
          }
        }

        if (result) {
          return result;
        }
      }

    }

    return false;
  }

}
