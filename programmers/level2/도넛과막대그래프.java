import java.util.*;

public class 도넛과막대그래프 {

    static List<Integer>[] list = new List[1000001];

    static int[] visited = new int[1000001];
    static int nodeCnt = 0;
    static int edgeCnt = 0;

    class Solution {

        public int[] solution(int[][] edges) {
            int startNode = 0;

            int[] cnt = new int[1000001];
            int[] answer = new int[4];

            for (int i = 1; i <= 1000000; i++) {

                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < edges.length; i++) {

                int a = edges[i][0];
                int b = edges[i][1];

                list[a].add(b);
                cnt[b]++;

            }

            for (int i = 1; i <= 1000001; i++) {
                if (list[i].size() >= 2 && cnt[i] == 0) {
                    startNode = i;
                    break;
                }
            }


            // 각각의 그래프가 무슨 모양인지 확인
            for (int i = 0; i < list[startNode].size(); i++) {


                // 초기값
                nodeCnt = 1;
                edgeCnt = 0;

                int next = list[startNode].get(i);

                visited[next] = 1;


                dfs(next);

                // 막대모양
                if (nodeCnt == edgeCnt + 1) {
                    answer[2]++;
                }
                // 도넛모양
                else if (nodeCnt == edgeCnt) {
                    answer[1]++;
                }
                // 8자모양
                else {
                    answer[3]++;
                }


            }

            answer[0] = startNode;

            return answer;
        }

        void dfs(int now) {


            if (visited[now] == 2) {
                return;
            }


            for (int i = 0; i < list[now].size(); i++) {

                int next = list[now].get(i);
                edgeCnt++;

                if (visited[next] == 0) nodeCnt++;


                visited[next]++;

                dfs(next);


            }


        }
    }
}
