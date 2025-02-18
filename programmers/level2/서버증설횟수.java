

import java.util.ArrayList;
import java.util.List;

public class 서버증설횟수 {

    static class Server {

        int startTime;
        int endTime;
        int cnt;

        Server(int startTime, int endTime, int cnt) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.cnt = cnt;
        }

    }


    public static void main(String[] args) {

        int[] players = {0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 5, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1};
        int m = 1;
        int k = 1;

        System.out.println(solution(players, m, k));

    }

    public static int solution(int[] players, int m, int k) {

        int answer = 0;
        List<Server> serverList = new ArrayList<>();

        for (int i = 0; i < players.length; i++) {

            int startTime = i;
            int endTime = i + 1;

            // n개의 서버가 필요
            int n = players[i] / m;

            // 현재 몇대의 서버가 증설되어 있는지 확인
            int cnt = 0;
            for (int j = 0; j < serverList.size(); j++) {

                Server server = serverList.get(j);
                if (startTime < server.endTime) {
                    cnt += server.cnt;
                }
            }

            // 서버 증설이 필요하다면 서버리스트에 증설 후 몇개가 증설되었는지 횟수 추가
            if (cnt < n) {

                serverList.add(new Server(startTime, startTime + k, n - cnt));
                answer += (n - cnt);
            }
        }

        return answer;
    }
}