package programmers.level2;


import java.util.LinkedList;
import java.util.Queue;


public class 카카오프렌즈컬러링북 {

  public int[] solution(int m, int n, int[][] picture) {
    int numberOfArea = 0;
    int maxSizeOfOneArea = 0;

    int dx[] = {1, -1, 0, 0};
    int dy[] = {0, 0, 1, -1};

    boolean[][] visited = new boolean[m][n];

    int[] answer = new int[2];

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {

        if (picture[i][j] != 0 && !visited[i][j]) {

          visited[i][j] = true;
          numberOfArea++;
          int size = 1;
          int color = picture[i][j];

          Queue<int[]> q = new LinkedList<>();

          q.add(new int[]{i, j});

          while (!q.isEmpty()) {

            int[] loc = q.poll();

            for (int k = 0; k <= 3; k++) {

              int nx = loc[0] + dx[k];
              int ny = loc[1] + dy[k];

              if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1 || visited[nx][ny]
                  || color != picture[nx][ny]) {
                continue;
              }

              size++;
              visited[nx][ny] = true;
              q.add(new int[]{nx, ny});
            }
          }
          maxSizeOfOneArea = Integer.max(size, maxSizeOfOneArea);
        }
      }
    }

    answer[0] = numberOfArea;
    answer[1] = maxSizeOfOneArea;
    return answer;
  }
}
