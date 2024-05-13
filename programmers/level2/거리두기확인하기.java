package programmers.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class 거리두기확인하기 {

    public static void main(String[] args) {


        String[][] places = {{"OOOXP", "OOOPX", "OOOOO", "OOOOO", "OOOOO"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};


        System.out.println(Arrays.toString(solution(places)));


    }

    public static int[] solution(String[][] places) {

        int[] answer = new int[places.length];

        for (int i = 0; i < places.length; i++) {

            String[] place = places[i]; // 0 1 2 3 4


            int result = 1;

            List<int[]> list = new ArrayList<>();


            for (int j = 0; j <= 4; j++) {
                for (int k = 0; k <= 4; k++) {

                    char val = place[j].charAt(k);

                    if (val == 'P') {

                        list.add(new int[]{j, k});
                    }
                }
            }


            loop:
            for (int j = 0; j < list.size() - 1; j++) {
                for (int k = j + 1; k < list.size(); k++) {

                    int[] p1 = list.get(j);
                    int[] p2 = list.get(k);

                    int length = Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);

                    if (length < 2) {
                        result = 0;
                        break loop;
                    } else if (length == 2) {
                        int sx = Integer.min(p1[0], p2[0]);
                        int ex = Integer.max(p1[0], p2[0]);
                        int sy = Integer.min(p1[1], p2[1]);
                        int ey = Integer.max(p1[1], p2[1]);
                        for (int x = sx; x <= ex; x++) {
                            for (int y = sy; y <= ey; y++) {

                                if (place[x].charAt(y) != 'X' && place[x].charAt(y) != 'P') {
                                    result = 0;
                                    break loop;
                                }
                            }
                        }
                    }
                }
            }
            answer[i] = result;
        }
        return answer;
    }

}
