package programmers.level2;

import java.util.PriorityQueue;

/**
 * 우선순위큐를 통해서 해결
 */
public class 디펜스게임 {
    public static void main(String[] args) {

        System.out.println(solution(2, 4, new int[]{3, 3, 3, 3}));
    }

    public static int solution(int n, int k, int[] enemy) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // k개만큼 담을 수 있다


        for (int i = 0; i < enemy.length; i++) {

            if (pq.size() < k) {
                pq.add(enemy[i]);
            } else if (pq.size() == k) {

                pq.add(enemy[i]);

                n -= pq.poll();

                if (n < 0) {
                    break;
                }
            }
            answer = i + 1;
        }
        return answer;
    }

}
