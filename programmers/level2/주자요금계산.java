package programmers.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 주자요금계산 {

  public static void main(String[] args) {

    int[] fees = {1, 461, 1, 10};
    String[] records = {"00:00 1234 IN"};

    System.out.println(Arrays.toString(solution(fees, records)));

  }

  public static int[] solution(int[] fees, String[] records) {

    List<Integer> answerList = new ArrayList<>();
    int[][] carInOutLog = new int[10000][2]; // 0 은 입차 , 1은 출차 정보
    boolean[] isCarInput = new boolean[10000];
    Arrays.fill(isCarInput, false);

    int[] carTotalMinutes = new int[10000];
    //  18:59 0000 IN
    for (int i = 0; i < records.length; i++) {

      String[] record = records[i].split(" ");

      int minutes = timeToMinutes(record[0]);
      int carNumber = Integer.parseInt(record[1]);
      String type = record[2];
      if (type.equals("IN")) {
        isCarInput[carNumber] = true;
        carInOutLog[carNumber][0] = minutes;
      } else {
        carInOutLog[carNumber][1] = minutes;
        carTotalMinutes[carNumber] += (carInOutLog[carNumber][1] - carInOutLog[carNumber][0]);

        carInOutLog[carNumber][0] = 0;
        carInOutLog[carNumber][1] = 0;
        isCarInput[carNumber] = false;
      }
    }

    for (int i = 0; i <= 9999; i++) {

      // 입차 기록이 남아있거나  계산된 요금이 0이 아닌경우
      if (isCarInput[i] || carTotalMinutes[i] != 0) {

        if (isCarInput[i]) {
          carInOutLog[i][1] = timeToMinutes("23:59");
          carTotalMinutes[i] += (carInOutLog[i][1] - carInOutLog[i][0]);
        }
        answerList.add(feeCalculation(carTotalMinutes[i], fees));

      }
    }
    int[] answer = new int[answerList.size()];
    for (int i = 0; i < answerList.size(); i++) {
      answer[i] = answerList.get(i);
    }

    return answer;
  }


  public static int timeToMinutes(String time) {
    int result = 0;
    StringTokenizer st = new StringTokenizer(time, ":");
    result += Integer.parseInt(st.nextToken()) * 60;
    result += Integer.parseInt(st.nextToken());
    return result;
  }

  // fee 180, 5000, 10, 600
  public static int feeCalculation(int minutes, int[] fees) {
    int fee = fees[1];
    minutes -= fees[0];
    if (minutes > 0) {
      fee += (minutes / fees[2] * fees[3]);
      if (minutes % fees[2] != 0) {
        fee += fees[3];
      }
    }
    return fee;
  }
}
