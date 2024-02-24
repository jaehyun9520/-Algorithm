import java.util.*;

public class 프로세스 {


    class Solution {
        public int solution(int[] priorities, int location) {
            int answer = 0;
            // 우선순위 개수
            int[] cnt = new int[10];

            // 실행 순서
            int executionOrder = 0;

            Queue<int[]> q = new LinkedList<>();


            for (int i = 0; i < priorities.length; i++) {

                int process = priorities[i];
                cnt[process]++;
                q.add(new int[]{process, i});
            }


            while (!q.isEmpty()) {

                int[] process = q.poll();

                boolean flag = true;

                for (int i = process[0] + 1; i <= 9; i++) {

                    // 나보다 높은 우선순위를 가진 프로세스가 아직 큐에 존재
                    if (cnt[i] != 0) {
                        flag = false;
                        break;
                    }
                }

                if (!flag) {

                    q.add(process);
                } else {

                    cnt[process[0]]--;
                    executionOrder++;


                    if (process[1] == location) {
                        answer = executionOrder;
                        break;
                    }

                }


            }


            return answer;
        }
    }
}
