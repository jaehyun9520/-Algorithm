

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class 지게차와크레인 {

    public static void main(String[] args) {

        String[] storage = {"HAH", "HBH", "HHH", "HAH", "HBH"};
        String[] requests = {"C", "B", "B", "B", "B", "H"};



        System.out.println(solution(storage, requests));

    }

    public static int solution(String[] storage, String[] requests) {

        int answer = 0;
        // storage를 탐색하기 변하게 2차원 배열 char로 바꿔준다

        int n = storage.length;
        int m = storage[0].length();
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        char[][] map = new char[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String s = storage[i - 1];
            for (int j = 1; j <= m; j++) {
                map[i][j] = s.charAt(j - 1);
            }
        }

        for (int i = 0; i < requests.length; i++) {
            int length = requests[i].length();
            char alphabet = requests[i].charAt(0);


            if(length==1) {
                // 지게차 사용
                useForklift(n,m,alphabet,map);
            }

            else if(length==2) {
                // 크레인 사용
                useCrane(n,m,alphabet,map);
            }


        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                if (map[i][j] != '0' && map[i][j] != '1') {
                    answer++;
                }
            }
        }
        return answer;
    }



    private static void useCrane(int n, int m, char alphabet, char[][] map) {

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(map[i][j]==alphabet) {
                    map[i][j]='0';
                }
            }
        }
    }

    private static void useForklift(int n, int m, char alphabet, char[][] map) {

        int dx[] ={0,1,0,-1};
        int dy[] ={1,0,-1,0};
        boolean[][] visited = new boolean[n+1][m+1];
        List<int[]> removeList = new ArrayList<>();

        Queue<int[]> q = new LinkedList<>();

        int x=1;
        int y=1;
        int dir=0;

        // 지게차가 들어갈 수 있는 진입점 체크
        while(true)  {
            if(map[x][y]=='0') {

                visited[x][y]=true;
                q.add(new int[]{x,y});
            }
            else if(map[x][y]==alphabet) {
                removeList.add(new int[]{x,y});
            }
            x=x+dx[dir];
            y=y+dy[dir];
            if(x==1&&y==m) {
                dir++;
            }
            else if(x==n&&y==m) {
                dir++;
            }
            else if(x==n&&y==1) {
                dir++;
            }
            // 한바퀴를 다 돌았으니 종료
            if(x==1&&y==1) {
                break;
            }

        }


        while(!q.isEmpty()) {

            int[] loc = q.poll();
            x = loc[0];
            y = loc[1];

            for(int i=0; i<=3; i++) {

                int nx = x+dx[i];
                int ny = y+dy[i];


                // 범위를 벗어났거나 방문했으면 종료
                if(nx<1||nx>n||ny<1||ny>m||visited[nx][ny]) continue;


                if(map[nx][ny]==alphabet) {
                    removeList.add(new int[] {nx,ny});
                }

                // 지게차의 이동공간
                else if(map[nx][ny]=='0') {
                    visited[nx][ny]=true;
                    q.add(new int[] {nx,ny});
                }
            }
        }


        for(int i=0; i<removeList.size(); i++) {
            map[removeList.get(i)[0]][removeList.get(i)[1]]='0';
        }






    }


}