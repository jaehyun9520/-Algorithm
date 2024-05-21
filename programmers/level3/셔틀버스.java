import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class 셔틀버스 {
    public static String solution(int n, int t, int m, String[] timetable) {

        List<Integer> shuttleInfo = new ArrayList<>();
        LinkedList<Integer> crewInfo = new LinkedList<>();

        // 계산을 편하게 하기위해 크루원들의 도착시간과 버스 도착시간을 다 분으로 통일한다

        int conInfo = 0;

        // 셔틀 도착 정보 추가 ( 오전 9시부터 시작)
        for (int i = 0; i < n; i++) {
            int next = 540 + (t * i);
            shuttleInfo.add(next);
        }


        // 크루 도착 정보 추가

        for (int i = 0; i < timetable.length; i++) {

            String[] info = timetable[i].split(":");
            int[] arrive = {,};
            crewInfo.addLast(Integer.parseInt(info[0]) * 60 + Integer.parseInt(info[1]));
        }

        Collections.sort(crewInfo);


        // 콘이 가장 늦게 버스를 탈 수 잇는 시간을 확인
        String hourS = "";
        String minS = "";
        for (int i = 0; i < shuttleInfo.size(); i++) {

            int shuttleBus = shuttleInfo.get(i);

            int lastCrew = 0;
            int cnt = 0;

            while (true) {

                if (crewInfo.size() == 0) break;

                int crew = crewInfo.getFirst();

                // 해당 버스를 탈 수 있다면?
                if (shuttleBus >= crew) {
                    // 한 버스에 탈 수 있는 최대 크루수가 m보다 작다면 아직 더 들어갈 수 있다
                    if (cnt < m) {
                        cnt++;
                        lastCrew = crew;
                        crewInfo.removeFirst();
                    }

                    // 다 채웠으면 중단
                    else {
                        break;
                    }
                }
                // 남아있지만 현재 버스를 탈 수 없다면 중단
                else {
                    break;
                }
            }

            // 버스에 아직 탈 공간이 있다면? 버스 도착시간에 딱 맞춰 도착하면 된다
            if (cnt < m) {

                conInfo = shuttleBus;
            }
            // 꽉 찾으면? 이 버스를 타려면 마지막 탑승자보다 1분 더 빨리 도착해야 한다.
            else {
                conInfo = lastCrew - 1;
            }


            int hour = conInfo / 60;
            int minute = conInfo % 60;


            hourS = "" + hour;
            minS = "" + minute;
            if (hour < 10) {
                hourS = "0" + hourS;
            }

            if (minute < 10) {
                minS = "0" + minS;
            }

        }
        String answer = hourS + ":" + minS;
        return answer;
    }

}
