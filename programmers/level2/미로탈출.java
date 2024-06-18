package programmers.level2;
import java.util.LinkedList;
import java.util.Queue;

public class 미로탈출 {

    public static void main(String[] args) {

        String[] maps = {"LOOXS","OOOOX","OOOOO","OOOOO","EOOOO"};

        System.out.println(solution(maps));

    }

    public static int solution(String[] maps) {

        int[] startLoc = findStartLoc(maps);
        return  bfs(maps, maps.length, maps[0].length(), startLoc[0], startLoc[1]);
    }

    static int bfs(String[] maps, int n, int m, int sx, int sy) {

        int ans = -1;
        boolean[][][] visited = new boolean[2][n][m]; // 0은 레버를 내리지 않은 상태 , 1은 레버를 내린 상태
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        Queue<int[]> q = new LinkedList<>();

        visited[0][sx][sy] = true;

        // x,y,레버 사용유무, 이동횟수
        q.add(new int[]{sx, sy, 0, 0});


        loop:
        while (!q.isEmpty()) {

            int[] now = q.poll();

            for (int i = 0; i <= 3; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                // 범위를 벗어나거나 이미 방문했거나 벽인 경우 생략
                if (nx < 0 || nx > n - 1 || ny < 0 || ny > m - 1 || visited[now[2]][nx][ny] || maps[nx].charAt(ny) == 'X')
                    continue;

                // 방문 체크
                visited[now[2]][nx][ny] = true;


                // 레버가 있는 위치를 처음 방문한 경우
                if (now[2] == 0 && maps[nx].charAt(ny) == 'L') {

                    visited[1][nx][ny] = true;
                    q.add(new int[]{nx, ny, 1, now[3] + 1});
                }
                // 레버를 작동시키고 출구에 방문한 경우
                else if (now[2] == 1 && maps[nx].charAt(ny) == 'E') {
                    ans = now[3] + 1;
                    break loop;
                } else {
                    q.add(new int[]{nx, ny, now[2], now[3] + 1});
                }
            }
        }
        return ans;
    }

    static int[] findStartLoc(String[] maps) {

        int[] startLoc = new int[2];

        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length(); j++) {

                if (maps[i].charAt(j) == 'S') {

                    startLoc[0] = i;
                    startLoc[1] = j;
                }
            }
        }
        return startLoc;
    }
}
