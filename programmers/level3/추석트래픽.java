package programmers.level3;

import java.util.*;

public class 추석트래픽 {

    public static void main(String[] args) {

        String[] lines = {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"};

        System.out.println(solution(lines));
    }

    public static int solution(String[] lines) {
        List<int[]> logInfo = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < lines.length; i++) {

            // 종료 시간
            String endTime = lines[i].substring(11, 23).replace(".", ":");


            int eMSecond = 0;

            int[] secondInfo = {3600000, 60000, 1000, 1};
            StringTokenizer st = new StringTokenizer(endTime, ":");
            // 종료시간 밀리초로 변환
            for (int j = 0; j <= 3; j++) {
                eMSecond += Integer.parseInt(st.nextToken()) * secondInfo[j];
            }

            // 처리시간 (밀리초)로 변환
           double pMSecond = Double.parseDouble(lines[i].substring(24, lines[i].indexOf("s"))) * 1000;

            // 이제 이 둘을 계산해준다
            int startMSecond = eMSecond - (int) pMSecond+1;
            logInfo.add(new int[]{startMSecond, eMSecond});

        }

        int max = 0;
        for (int j = 0; j < logInfo.size(); j++) {
            int cnt = 1;
            int s = logInfo.get(j)[1];
            int e = s + 999;

            for (int k = j + 1; k < logInfo.size(); k++) {
                int nextS = logInfo.get(k)[0];
                int nextE = logInfo.get(k)[1];

                if (nextS <= s && s <= nextE || s <= nextS && nextS <= e) {
                    cnt++;
                }
            }
            max = Integer.max(max, cnt);
        }

        return max;
    }
}
