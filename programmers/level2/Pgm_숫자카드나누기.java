package programmers;


public class Pgm_숫자카드나누기 {


  public static void main(String[] args) {

    int[] arrayA = {10, 20};
    int[] arrayB = {5, 17};

    System.out.println(solution(arrayA, arrayB));
  }

  public static int solution(int[] arrayA, int[] arrayB) {

    int answer = 0;

    int valA = arrayA[0];
    for (int i = 1; i < arrayA.length; i++) {
      valA = getGCD(valA, arrayA[i]);
    }

    int valB = arrayB[0];
    for (int i = 1; i < arrayB.length; i++) {
      valB = getGCD(valB, arrayB[i]);
    }
    for (int i = 0; i < arrayA.length; i++) {
      if (valA != 0 && arrayB[i] % valA == 0) {
        valA = 0;
      }
      if (valB != 0 && arrayA[i] % valB == 0) {
        valB = 0;
      }
    }

    return Integer.max(valA, valB);
  }


  private static int getGCD(int a, int b) {
    while (a != 0 && b != 0) {
      if (a >= b) {
        a = a % b;
      } else {
        b = b % a;
      }
    }
    return a > b ? a : b;
  }
}
