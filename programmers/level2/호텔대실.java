package programmers.level2;
import java.util.*;

public class 호텔대실 {

    public static void main(String[] args) {


        String[][] book_time = {{"10:20", "12:30"}, {"10:20", "12:30"}, {"10:20", "12:30"}};
        System.out.println(solution(book_time));

    }

    public static int solution(String[][] book_time) {

        // 현재까지 사용된 방 정보 ( 가장 최근에 방을 사용한 예약의 종료시간 + 10(청소시간)이 기록되어 있다)
        List<Integer> roomInfo = new ArrayList<>();

        List<int[]> bookInfo = new ArrayList<>();


        for (int i = 0; i < book_time.length; i++) {

            int startTime = Integer.parseInt(book_time[i][0].substring(0, 2)) * 60 + Integer.parseInt(book_time[i][0].substring(3, 5));
            int endTime = Integer.parseInt(book_time[i][1].substring(0, 2)) * 60 + Integer.parseInt(book_time[i][1].substring(3, 5)) + 10;
            bookInfo.add(new int[]{startTime, endTime});
        }

        Collections.sort(bookInfo, (ints, t1) -> ints[0] - t1[0]);


        for (int i = 0; i < bookInfo.size(); i++) {

            boolean flag = false;
            int startTime = bookInfo.get(i)[0];

            for (int j = 0; j < roomInfo.size(); j++) {

                int endTime = roomInfo.get(j);

                if (endTime <= startTime) {
                    flag = true;
                    roomInfo.set(j, bookInfo.get(i)[1]);
                    break;
                }


            }
            if (!flag) {
                roomInfo.add(bookInfo.get(i)[1]);
            }
        }
        int answer = roomInfo.size();
        return answer;
    }
}
