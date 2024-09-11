package programmers.level1;

public class 동영상재생기 {

  public static void main(String[] args) {

    String solution = solution("34:33", "13:00", "00:55", "02:55", new String[]{"next", "prev"});
    System.out.println(solution);


  }

  public static String solution(String video_len, String pos, String op_start, String op_end,
      String[] commands) {
    String answer = "";
    String[] video = video_len.split(":");
    String[] position = pos.split(":");
    String[] start = op_start.split(":");
    String[] end = op_end.split(":");

    //비디오 정보(분*60+초)
    int videoTime = Integer.parseInt(video[0]) * 60 + Integer.parseInt(video[1]);
    // 현재 재생 위치 (분*60+초)
    int posTime = Integer.parseInt(position[0]) * 60 + Integer.parseInt(position[1]);
    // 오프닝 시작 시간 (분*60+초)
    int opStartTime = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
    // 오프닝 종료 시간 (분*60+초)
    int opEndTime = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]);

    // 비디오가 초기부터 오프닝 범위안에 속할 수 있으니 예외처리
    if (opStartTime <= posTime && posTime <= opEndTime) {
      posTime = opEndTime;
    }

    for (int i = 0; i < commands.length; i++) {

      String command = commands[i];

      // 10초 전으로 이동
      if (command.equals("prev")) {
        posTime -= 10;
        if (posTime < 0) {
          posTime = 0;
        }
      }

      // 10초 이후로 이동
      else if (command.equals("next")) {
        posTime += 10;
        if (posTime > videoTime) {
          posTime = videoTime;
        }
      }

      // 오프닝 범위에 속하는지
      if (opStartTime <= posTime && posTime <= opEndTime) {
        posTime = opEndTime;
      }

    }
    int min = posTime / 60;
    int sec = posTime % 60;

    answer += (min <= 9 ? "0" + min : "" + min);
    answer += ":";
    answer += (sec <= 9 ? "0" + sec : "" + sec);

    return answer;
  }

}
