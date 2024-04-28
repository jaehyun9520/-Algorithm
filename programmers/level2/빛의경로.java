import java.util.*;

public class 빛의경로 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    //현재 방향이 상,하,좌,우일때  S,L,R 했을때의 이동방향
    static int[][] nextDir = {{0, 2, 3}, {1, 3, 2}, {2, 1, 0}, {3, 0, 1}};
    static boolean[][][] visited;
    static int[][] map;
    static int x, y;
    static List<Integer> cycleList = new ArrayList<>();

    public int[] solution(String[] grid) {

        x = grid.length;
        y = grid[0].length();

        visited = new boolean[x][y][4]; // 0~3 은 상하좌우 방향으로 방문여부를 판단하고 방문순서를 기록한다
        map = new int[x][y];


        // S L R => 0 1 2 숫자로 변경
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {

                char dir = grid[i].charAt(j);

                if (dir == 'S') {
                    map[i][j] = 0;
                } else if (dir == 'L') {
                    map[i][j] = 1;
                } else {
                    map[i][j] = 2;
                }
            }
        }

        // dfs로 사이클 확인
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {

                for (int k = 0; k <= 3; k++) {

                    if (!visited[i][j][k]) {
                        visited[i][j][k] = true;
                        int cnt = 1;
                        int dir = k;
                        int nowX = i;
                        int nowY = j;
                        while (true) {

                            int nx = nowX + dx[nextDir[dir][0]];
                            int ny = nowY + dy[nextDir[dir][0]];

                            nx = (nx + x) % x;
                            ny = (ny + y) % y;
                            int nextD = nextDir[dir][map[nx][ny]];
                            if (!visited[nx][ny][nextD]) {


                                cnt++;
                                visited[nx][ny][nextD] = true;
                                nowX = nx;
                                nowY = ny;
                                dir = nextD;

                            } else if (visited[nx][ny][nextD]) {

                                cycleList.add(cnt);
                                break;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
        Collections.sort(cycleList);
        int[] answer = new int[cycleList.size()];

        for (int i = 0; i < cycleList.size(); i++) {
            answer[i] = cycleList.get(i);
        }
        return answer;
    }
}
