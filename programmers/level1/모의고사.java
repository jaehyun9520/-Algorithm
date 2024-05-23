import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 모의고사 {
    static int pattern[][] = {{1, 2, 3, 4, 5}, {2, 1, 2, 3, 2, 4, 2, 5}, {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}}; //5 ,8,10 단위로 반복된다.

    public static int[] solution(int[] answers) {


        int[] score = {0, 0, 0};

        for (int i = 0; i < answers.length; i++) {

            int p1 = pattern[0][i % 5];
            int p2 = pattern[1][i % 8];
            int p3 = pattern[2][i % 10];

            if (p1 == answers[i]) score[0]++;
            if (p2 == answers[i]) score[1]++;
            if (p3 == answers[i]) score[2]++;
        }

        int max = Integer.max(Integer.max(score[0], score[1]), score[2]);

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i <= 2; i++) {
            if (max == score[i]) list.add(i + 1);
        }
        Collections.sort(list);

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
