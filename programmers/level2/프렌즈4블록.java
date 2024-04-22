import java.util.ArrayList;
import java.util.List;

public class 프렌즈4블록 {
    public static int solution(int m, int n, String[] board) {

        int[][] map = new int[m][n];

        mapInit(m, n, map, board);
        List<int[]> list = new ArrayList<>();
        int answer = 0;


        while (true) {
            list.clear();


            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if ((i + 1 < m && j + 1 < n) && map[i][j] != 0) {
                        if (map[i][j] == map[i][j + 1] && map[i][j] == map[i + 1][j] && map[i][j] == map[i + 1][j + 1]) {
                            list.add(new int[]{i, j});
                        }
                    }
                }
            }


            if (list.size() == 0) break;

            for (int i = 0; i < list.size(); i++) {

                int[] now = list.get(i);

                for (int j = 0; j <= 1; j++) {
                    for (int k = 0; k <= 1; k++) {


                        if (map[now[0] + j][now[1] + k] != 0) {
                            map[now[0] + j][now[1] + k] = 0;
                            answer++;
                        }

                    }
                }
            }

            for (int i = 0; i < n; i++) {

                int next = m - 1;
                if (map[m - 1][i] != 0) {
                    next = m - 2;
                }

                for (int j = m - 2; j >= 0; j--) {

                    if (map[j][i] != 0) {
                        int val = map[j][i];
                        map[j][i] = 0;
                        map[next][i] = val;
                        next--;

                    }
                }
            }

        }

        return answer;
    }

    static void mapInit(int m, int n, int[][] map, String[] board) {


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = board[i].charAt(j) - 64; // 빈칸은 0
            }
        }
    }
}
