package programmers.level2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 혼자놀기의달인 {

    public static void main(String[] args) {

        System.out.println(solution(new int[]{8, 6, 3, 7, 2, 5, 1, 4}));

    }

    public static int solution(int[] cards) {

        int[] group = new int[cards.length]; // 0번이면 아직 어디 그룹애도 속하지 않은것 , 그외의 숫자는 그룹을 가지고 있는것
        List<Integer> groupInfo = new ArrayList<>();


        int number = 1;
        for (int i = 0; i < cards.length; i++) {


            if (group[i] == 0) {
                int now = i;
                int cnt = 0;
                while (group[now] == 0) {
                    group[now] = number;
                    cnt++;

                    now = cards[now] - 1;
                }
                number++;
                groupInfo.add(cnt);
            }


        }
        int answer = 0;

        if (groupInfo.size() >= 2) {

            Collections.sort(groupInfo);
            int length = groupInfo.size();
            answer = groupInfo.get(length - 1) * groupInfo.get(length - 2);
        }


        return answer;
    }
}
