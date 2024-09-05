package programmers.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class 오픈채팅방 {

  public static void main(String[] args) {

    String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234",
        "Enter uid1234 Prodo", "Change uid4567 Ryan"};

    System.out.println(Arrays.toString(solution(record)));

  }

  public static String[] solution(String[] record) {
    List<String> answerList = new ArrayList<>();
    ArrayList<String[]> log = new ArrayList<>(); // { 입출력 , 아이디}

    // 유저 아이디 ,  닉네임
    Map<String, String> userInfo = new HashMap<>();

    for (int i = 0; i < record.length; i++) {

      String input = record[i];

      StringTokenizer st = new StringTokenizer(input, " ");
      String type = st.nextToken();
      String userId = st.nextToken();
      String nickname = null;

      // 입장했거나 닉네임 변경시 아이디별 닉네임 정보 갱신
      if (type.equals("Enter") || type.equals("Change")) {
        nickname = st.nextToken();
        userInfo.put(userId, nickname);
      }
      // 입퇴장시 로그에 기록
      if (type.equals("Enter") || type.equals("Leave")) {
        log.add(new String[]{type, userId});
      }
    }

    for (int i = 0; i < log.size(); i++) {

      String[] s = log.get(i);

      if (s[0].equals("Enter")) {
        answerList.add(userInfo.get(s[1]) + "님이 들어왔습니다.");
      } else {
        answerList.add(userInfo.get(s[1]) + "님이 나갔습니다.");
      }
    }

    return answerList.toArray(new String[0]);
  }

}
