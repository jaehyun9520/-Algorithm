public class 유연근무제 {
    public static void main(String[] args) {

    }

    public static int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        int[] eventDayInfo = new int[7];

        for (int i = 0; i <= 6; i++) {

            eventDayInfo[i] = (startday + i) % 7 == 0 ? 7 : (startday + i) % 7;


        }

        for (int i = 0; i < schedules.length; i++) {
            int hour = schedules[i] / 100;
            int minute = schedules[i] % 100 + 10;
            hour = hour + minute / 60;
            minute = minute % 60;
            schedules[i] = hour * 100 + minute;
        }

        for (int i = 0; i < timelogs.length; i++) {
            // 평일인지?
            int cnt = 0;
            for (int j = 0; j <= 6; j++) {

                if (eventDayInfo[j] <= 5) {

                    if (timelogs[i][j] <= schedules[i]) {
                        cnt++;
                    }
                }

            }
            if (cnt == 5) {
                answer++;
            }
        }

        return answer;
    }
}
