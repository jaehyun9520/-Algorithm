package programmers.level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 */
public class 표현가능한이진트리 {


  public static void main(String[] args) {

    long[] numbers = {
        2147516555L};
    // 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1
    System.out.println(Arrays.toString(solution(numbers)));

  }

  static Set<Integer> validLength = new HashSet<>();
  static boolean result = true;


  public static int[] solution(long[] numbers) {
    setInit();
    int[] answer = new int[numbers.length];
    for (int i = 0; i < numbers.length; i++) {

      String binaryNum = toBinary(numbers[i]);
      result = true;

      // 리프노드 중에 더미노드가 아닌 노드가 하나라도 있는지?
      int length2 = binaryNum.length();
      if (!validLength.contains(binaryNum.length())) {

        for (int len : validLength) {

          if (binaryNum.length() < len) {
            int length = binaryNum.length();
            for (int k = 1; k <= len - length; k++) {
              binaryNum = "0" + binaryNum;
            }
            break;
          }

        }
      }
      isValidBinaryTree(binaryNum);
      if (result) {
        answer[i] = 1;
      } else {
        answer[i] = 0;
      }

    }

    return answer;


  }

  private static void isValidBinaryTree(String binaryNum) {

    if (binaryNum.length() == 1) {

      return;
    } else {

      int now = binaryNum.charAt(binaryNum.length() / 2) - 48;
      String left = binaryNum.substring(0, binaryNum.length() / 2);
      String right = binaryNum.substring(binaryNum.length() / 2 + 1, binaryNum.length());
      if (now == 0 && (left.contains("1") || right.contains("1"))) {
        result = false;
      }
      isValidBinaryTree(left);
      isValidBinaryTree(right);
    }

  }

  static void setInit() {
    long val = (long) Math.pow(10, 15);
    for (int i = 2; i - 1 <= 64; i *= 2) {
      validLength.add(i - 1);
    }
  }

  static String toBinary(Long number) {
    return Long.toBinaryString(number);
  }
}
