package programmers.level3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 보석쇼핑 {

  public static void main(String[] args) {

    String[] gems = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};
    System.out.println(Arrays.toString(solution(gems)));
  }


  public static int[] solution(String[] gems) {

    int[] answer = new int[2];
    int answerLength = Integer.MAX_VALUE;
    Map<String, Integer> gemType = new HashMap<>();

    for (int i = 0; i < gems.length; i++) {
      gemType.put(gems[i], 0);
    }

    // 전체 개수
    int totalCount = 1;
    int left = 0, right = 0;

    gemType.put(gems[0], 1);

    while (true) {

      // 아직 모든 종류를 가지지 못했다면??
      if (totalCount < gemType.size()) {
        right++;

        // 범위를 벗어나면 중단
        if (right == gems.length) {
          break;
        }

        int count = gemType.get(gems[right]);

        if (count == 0) {
          totalCount++;
        }
        gemType.put(gems[right], count + 1);
      }

      // 모든 종류를 가졌다면?
      else if (totalCount == gemType.size()) {

        if (answerLength > right - left + 1) {

          answerLength = right - left + 1;
          answer = new int[]{left + 1, right + 1};
        }

        int count = gemType.get(gems[left]);

        if (count - 1 == 0) {
          totalCount--;

        }
        gemType.put(gems[left], count - 1);
        left++;


      }

    }

    return answer;
  }

}
