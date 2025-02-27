import java.util.*;

public class 최솟값만들기 {
    public static int solution(int[] A, int[] B) {
        int answer = 0;

        Integer[] arr = Arrays.stream(A).boxed().toArray(Integer[]::new);
        Arrays.sort(arr, Collections.reverseOrder());
        Arrays.sort(B);

        for (int i = 0; i < arr.length; i++) {
            answer = answer + (arr[i] * B[i]);
        }
        return  answer;
    }
}
