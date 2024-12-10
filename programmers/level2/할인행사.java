package programmers.level2;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;


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

    Map<String, Integer> wantMap = new HashMap<>();
    Map<String, Integer> discountMap = new HashMap<>();
    int s = 0;
    int e = 9;
    for (int i = 0; i < want.length; i++) {
      wantMap.put(want[i], number[i]);
    }

    for (int i = 0; i < 10; i++) {
      discountMap.put(discount[i], discountMap.getOrDefault(discount[i], 0) + 1);
    }
    if (validateMap(wantMap, discountMap)) {
      answer++;
    }

    while (e < discount.length - 1) {

      discountMap.put(discount[s], discountMap.get(discount[s]) - 1);
      s++;
      e++;

      discountMap.put(discount[e], discountMap.getOrDefault(discount[e], 0) + 1);

      if (validateMap(wantMap, discountMap)) {
        answer++;
      }
    }

    return answer;
  }


  private static boolean validateMap(Map<String, Integer> wantMap,
      Map<String, Integer> discountMap) {

    Set<String> keySet = wantMap.keySet();

    for (String key : keySet) {
      if (!discountMap.containsKey(key) || discountMap.get(key) != wantMap.get(key)) {
        return false;
      }
    }
    return true;
  }
}
