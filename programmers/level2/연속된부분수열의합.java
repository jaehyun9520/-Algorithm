package programmers.level2;

import java.util.Arrays;

public class 연속된부분수열의합 {

    public static void main(String[] args) {


        System.out.println(Arrays.toString(solution(new int[] {2, 2, 2, 2, 2},6)));
    }

    public static int[] solution(int[] sequence, int k) {
        int[] answer = {0, 0};

        int ansLength = Integer.MAX_VALUE;
        int s = 0, e = 0;
        int sum = sequence[0];
        while (e < sequence.length) {
            // 현재 부분수열의 합을 확인
            if (sum >= k) {
                if (sum == k) {

                    if (e - s + 1 < ansLength) {
                        ansLength=e-s+1;
                        answer[0] = s;
                        answer[1] = e;
                    }
                }
                sum-=sequence[s];
                    s++;
            }
            else {
                e++;
                if(e<sequence.length)
                 sum+=sequence[e];
            }
        }
        return answer;
    }
}
