import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class 충돌위험찾기 {

    public static void main(String[] args) {

    int[][] points= {{2, 2}, {2, 3}, {2, 7}, {6, 6}, {5, 2}};
    int[][] routes ={{2, 3, 4, 5}, {1, 3, 4, 5}};


        System.out.println(solution(points,routes));
    }


    // 상 하 좌 우 순으로 이동
    static int dx[] = {1,-1,0,0};
    static int dy[] = {0,0,-1,1};

    static int maxTime =0;
    static Map<Integer,int[]>[] robotMoveLog;


    public static int solution(int[][] points, int[][] routes) {


        int answer = 0;
        robotMoveLogInit(routes.length);


        // 각 로봇을 bfs 를 사용하여 정해진 순서대로 포인트를 방문시키고 그 경로를 log 에 기록
        for(int i=0; i<routes.length; i++) {

            robotSimulation(i,points,routes[i]);
        }


        // 로그를 확인하며 각 초마다 얼마나 겹쳤는지 확인한다

        for(int i=0; i<=maxTime;  i++) {

            int[][] visited = new int[101][101];

            for (int j = 0; j < robotMoveLog.length; j++) {

                int[] log = robotMoveLog[j].get(i);

                if (log != null) {
                    int x = log[0];
                    int y = log[1];
                    // 2개 이상이 겹친다면?
                    if (visited[x][y] == 1) {
                        answer++;
                    }
                    visited[x][y]++;

                }
            }
        }


        return answer;
    }

    private static void robotSimulation(int robotNum,int[][] points, int[] route) {


        // 마지막 진행시간  ( 처음에는 0 ,  그다음에는 1번째 포인트까지 도달하느데 걸리는 시간, 그다음은 2번째 포인트 )
        int lastTime=0;
        // 로봇의 포인트를 순서대로 방문
        for(int i=0; i< route.length-1; i++) {

            // 현재 포인트
            int startX = points[route[i]-1][0];
            int startY = points[route[i]-1][1];

            // 다음 포인트
            int targetX = points[route[i+1]-1][0];
            int targetY = points[route[i+1]-1][1];


            int[][][] visited = new int[101][101][2];


            // bfs 구성요소  ( 현재 좌표, 이전 좌표, 현재 시간)

            Queue<int[]> q = new LinkedList<>();

            // 시작점
            visited[startX][startY][0]=-1;
            visited[startX][startY][1]=-1;
            q.add(new int[] {startX,startY,lastTime});

           loop: while(!q.isEmpty()) {

                int[] now =q.poll();


                for(int k=0; k<=3; k++) {

                    int nx= now[0]+dx[k];
                    int ny= now[1]+dy[k];


                    // 좌표를 벗어나거나 이미 방문된적이 있으면 중단
                    if(nx<1||nx>100||ny<1||ny>100||visited[nx][ny][0]!=0) continue;


                    visited[nx][ny][0]=now[0];
                    visited[nx][ny][1]=now[1];


                    // 포인트에 도달했으면 로그 기록을 위해서 현재까지의 이동을 역으로 거슬러 올라간다
                    if(nx==targetX&&ny==targetY) {

                        robotMoveLog[robotNum].put(now[2]+1,new int[] {nx,ny});

                        lastTime=now[2]+1;
                        int x=visited[nx][ny][0];
                        int y=visited[nx][ny][1];
                        int time = now[2];

                        while(x!=-1) {
                            robotMoveLog[robotNum].put(time, new int[]{x,y});
                            time--;
                            nx= visited[x][y][0];
                            ny= visited[x][y][1];
                            x=nx;
                            y=ny;
                        }
                            break loop;
                    }
                    q.add(new int[]{nx,ny,now[2]+1});
                }
            }


        }

            maxTime=Integer.max(maxTime,lastTime);



    }


    // 로봇 로그 초기화
    static void robotMoveLogInit(int length) {

        robotMoveLog=new HashMap[length]; // 각 로봇의 로그를 나타내는거
        for(int i=0; i<length; i++) {
            robotMoveLog[i]=new HashMap<>();
        }

    }
}
