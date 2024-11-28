package programmers.level2;


import java.util.HashMap;
import java.util.Map;

public class 할인행사 {

  public static void main(String[] args) {

    String[] want = {"banana", "apple", "rice", "pork", "pot"};
    int[] number = {3, 2, 2, 2, 1};
    String[] discount = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana",
        "pork", "rice", "pot", "banana", "apple", "banana"};

    int solution = solution(want, number, discount);
    System.out.println(solution);


  }


  public static int solution(String[] want, int[] number, String[] discount) {
    int answer = 0;
    Map<String, Integer> map = new HashMap<>();

    int[] count = new int[number.length]; // 실제 담기는 개수

    for (int i = 0; i < number.length; i++) {
      map.put(want[i], i);
    }

    // 맨 처음 시작점 10개를 담고 확인한다

    int s = 0;
    int e = 9;

    for (int i = 0; i <= 9; i++) {
      calculation(count, map, discount[i], 1);
    }
    if (checkQuantity(count, number)) {
      answer++;
    }

    while (e < discount.length - 1) {

      calculation(count, map, discount[s], 0);
      s++;
      e++;
      calculation(count, map, discount[e], 1);
      if (checkQuantity(count, number)) {
        answer++;
      }
    }

    //check

    return answer;
  }

  static void calculation(int[] count, Map<String, Integer> map, String s, int type) {

    if (map.containsKey(s)) {
      int loc = map.get(s);

      if (type == 0) {
        count[loc]--;
      } else if (type == 1) {
        count[loc]++;
      }
    }


  }

  static boolean checkQuantity(int[] count, int[] number) {

    for (int i = 0; i < count.length; i++) {

      if (count[i] != number[i]) {
        return false;
      }
    }

    return true;
  }
}
