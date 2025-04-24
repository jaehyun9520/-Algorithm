package programmers;


import java.util.Arrays;
import java.util.Comparator;


public class Pgm_테이블해시함수 {

  public static void main(String[] args) {

    int[][] data = {{2, 2, 6}, {1, 5, 10}, {4, 2, 9}, {3, 8, 3}};

    solution(data, 2, 2, 3);
  }

  public static int solution(int[][] data, int col, int row_begin, int row_end) {
    int answer = 0;
    // 1. 테이블 정렬
    Arrays.sort(data, new Comparator<int[]>() {
      @Override
      public int compare(int[] ints, int[] t1) {
        if (ints[col - 1] == t1[col - 1]) {
          return t1[0] - ints[0];
        }
        return ints[col - 1] - t1[col - 1];
      }
    });

    for (int i = row_begin - 1; i <= row_end - 1; i++) {

      int sum = 0;

      for (int j = 0; j < data[0].length; j++) {
        sum += (data[i][j] % (i + 1));
      }
      answer = (answer ^ sum);
    }

    return answer;
  }
}
