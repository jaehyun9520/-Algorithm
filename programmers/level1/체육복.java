package programmers.level1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 체육복 {

    public static void main(String[] args) throws Exception {


        System.out.println(solution(3,new int[] {3},new int[] {1}));

    }

    public static int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int[] student = new int[n + 1];
        Arrays.fill(student, 1);

        for (int i = 0; i < lost.length; i++) {
            student[lost[i]]--;
        }

        for (int i = 0; i < reserve.length; i++) {
            student[reserve[i]]++;
        }

        for (int i = 1; i <= n; i++) {

            if (student[i] == 0) {

                if (i - 1 >= 1 && student[i - 1] == 2) {
                    student[i - 1] = 1;
                    student[i] = 1;
                } else if (i + 1 <= n && student[i + 1] == 2) {
                    student[i + 1] = 1;
                    student[i] = 1;
                }
            }
            if (student[i] >= 1) {
                answer++;
            }
        }
        return answer;
    }
}
