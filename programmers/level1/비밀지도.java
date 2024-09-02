package programmers.level1;

import java.util.Arrays;

public class 비밀지도 {

  public static void main(String[] args) {
    int decimal = 20;
    String binary = Integer.toBinaryString(decimal);
    System.out.println("10진수 " + decimal + "의 2진수 변환 결과: " + binary);

    int[] arr1 = {46, 33, 33, 22, 31, 50};
    int[] arr2 = {27, 56, 19, 14, 14, 10};
    System.out.println(Arrays.toString(solution(6, arr1, arr2)));
  }

  public static String[] solution(int n, int[] arr1, int[] arr2) {

    String[] answer = new String[n];

    for (int i = 0; i < n; i++) {

      String s1 = Integer.toBinaryString(arr1[i]);
      String s2 = Integer.toBinaryString(arr2[i]);

      while (s1.length() < n) {
        s1 = ("0" + s1);
      }
      while (s2.length() < n) {
        s2 = ("0" + s2);
      }
      // " " 공백 과 # 벽
      String list = "";

      for (int j = 0; j < n; j++) {
        if (s1.charAt(j) == '0' && s2.charAt(j) == '0') {

          list += " ";

        } else {
          list += "#";
        }
      }
      answer[i] = list;


    }

    return answer;


  }
}
