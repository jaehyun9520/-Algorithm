package iteratorPattern;

import java.util.*;

public class 무인도여행 {
    public int[] solution(String[] maps) {

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int r = maps.length;
        int c = maps[0].length();
        int[] answer;
        boolean[][] visited = new boolean[r][c];
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < r; i++) {

            for (int j = 0; j < c; j++) {


                if (('1' <= maps[i].charAt(j) && maps[i].charAt(j) <= '9') && !visited[i][j]) {
                    int sum = maps[i].charAt(j) - 48;
                    visited[i][j] = true;

                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[]{i, j});


                    while (!q.isEmpty()) {

                        int[] now = q.poll();

                        for (int k = 0; k <= 3; k++) {

                            int nx = now[0] + dx[k];
                            int ny = now[1] + dy[k];

                            if (nx < 0 || nx >= r || ny < 0 || ny >= c || visited[nx][ny] || maps[nx].charAt(ny) == 'X')
                                continue;

                            visited[nx][ny] = true;
                            sum += (maps[nx].charAt(ny) - 48);
                            q.add(new int[]{nx, ny});
                        }
                    }
                    list.add(sum);
                }
            }
        }
        Collections.sort(list);
        if (list.size() == 0) list.add(-1);
        answer = list.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}
