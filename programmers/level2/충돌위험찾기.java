import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/*
 a 포인트에서  b 포인트까지의 최단경로는  모든 공간에 장애물이 없기때문에

 ax,ay   bx,by 의 좌표라면?

 |bx-ax| + |by-ay| 이다
 이것보다 더 짧은 거리는 있을 수 없다 여기서
 최단 경로가 여러개인 경우는 항상 r 좌료가 변하는 이동을 c 좌표보다 먼저 해야된다고 했으니 r 먼저 움직여주면 된다


 */


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
        int time=0;
        // 로봇의 포인트를 순서대로 방문
        for(int i=0; i< route.length-1; i++) {

            // 현재 포인트
            int startX = points[route[i]-1][0];
            int startY = points[route[i]-1][1];

            // 다음 포인트
            int targetX = points[route[i+1]-1][0];
            int targetY = points[route[i+1]-1][1];


            int diffX = targetX-startX;
            int diffY = targetY-startY;
            // 시작지점 포함
            for(int j=0; j<= Math.abs(diffX) ; j++) {
                // 맨처음 시작지점을 넣어준다

                if(j!=0) {

                    if (diffX > 0) {
                        startX++;
                        time++;
                    } else if (diffX < 0) {
                        startX--;
                        time++;
                    }
                }
                robotMoveLog[robotNum].put(time,new int[] {startX,startY});
                }


            for(int j=1; j<= Math.abs(diffY) ; j++) {
                // 맨처음 시작지점을 넣어준다
                if(diffY>0) {
                    startY++;
                    time++;
                }

                else if(diffY<0) {
                    startY--;
                    time++;
                }
                robotMoveLog[robotNum].put(time,new int[] {startX,startY});
            }

        }

            maxTime=Integer.max(maxTime,time);

    }


    // 로봇 로그 초기화
    static void robotMoveLogInit(int length) {

        robotMoveLog=new HashMap[length]; // 각 로봇의 로그를 나타내는거
        for(int i=0; i<length; i++) {
            robotMoveLog[i]=new HashMap<>();
        }

    }
}
