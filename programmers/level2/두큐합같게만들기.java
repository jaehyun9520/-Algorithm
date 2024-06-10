package programmers.level2;

import java.util.LinkedList;
import java.util.Queue;


public class 두큐합같게만들기 {

    public static void main(String[] args) throws Exception {

        System.out.println(solution(new int[]{1, 2, 4}, new int[]{3, 2, 4}));
    }

    public static int solution(int[] queue1, int[] queue2) {

        int answer = -1;

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        int max = Integer.MIN_VALUE;
        long total = 0;
        long q1Sum = 0;
        long q2Sum = 0;
        int cnt = 0;
        for (int i = 0; i < queue1.length; i++) {

            q1.add(queue1[i]);
            q2.add(queue2[i]);

            q1Sum += queue1[i];
            q2Sum += queue2[i];

            max = Integer.max(max, Integer.max(queue1[i], queue2[i]));
            total += (queue1[i] + queue2[i]);

        }

        if (total % 2 == 0 && max <= total / 2) {


            int check = 0;
            while (check <= 600000) {


                check++;
                if (q1Sum == q2Sum) {

                    answer = cnt;
                    break;

                }
                cnt++;

                if (q1Sum > total / 2) {

                    int first = q1.poll();
                    q1Sum -= first;
                    q2Sum += first;
                    q2.add(first);
                } else if (q1Sum < total / 2) {

                    int first = q2.poll();
                    q1Sum += first;
                    q2Sum -= first;
                    q1.add(first);
                }


            }

        }


        return answer;
    }
}
