import java.util.LinkedList;
import java.util.Queue;

public class 리코쳇로봇 {
    static int n, m; // 게임판의 행, 열 크기
    static boolean[][] visited; // 방문여부 확인 배열

    public int solution(String[] board) {

        n = board.length;
        m = board[0].length();
        visited = new boolean[n][m];

        int sx = 0, sy = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 처음 로봇의 위치 확인
                if (board[i].charAt(j) == 'R') {
                    sx = i;
                    sy = j;
                    board[i].replace('R', '.'); // 이 처리가 굳이 필요할까? 생각해보자
                    break;
                }
            }
        }
        return bfs(board, sx, sy);
    }

    static int bfs(String[] board, int sx, int sy) {

        int ans = -1;
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        // { 행위치, 열위치, 이동횟수)
        Queue<int[]> q = new LinkedList<>();
        visited[sx][sy] = true; // 초기 위치

        q.add(new int[]{sx, sy, 0});


        loop:while (!q.isEmpty()) {

            int[] now = q.poll();
            int x = now[0], y = now[1], cnt = now[2];
            for (int i = 0; i <= 3; i++) {

                int px = x, py = y;

                while (true) {

                    int nx = px + dx[i];
                    int ny = py + dy[i];

                    // 장애물을 만났거나 격자를 벗어난 경우 중단
                    if (nx == n || nx == -1 || ny == -1 || ny == m || board[nx].charAt(ny) == 'D') {
                        break;
                    }
                    px = nx;
                    py = ny;

                }

                if (!visited[px][py]) {

                    visited[px][py] = true;

                    if (board[px].charAt(py) == 'G') {
                        ans = cnt + 1;
                        break loop;
                    }
                    q.add(new int[]{px, py, cnt + 1});
                }
            }
        }

        return ans;
    }
}

