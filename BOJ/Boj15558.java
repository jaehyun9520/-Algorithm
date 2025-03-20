import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj15558 {

    /*

    지문 정리

    지도는 2개  각각 N개의 1차월 배열 형태

    각 칸은 위험한 칸 , 안전한 칸으로 나누어져 있다.

   매 초마다 가능한 행동 3가지

   1. 한 칸 앞으로 이동

   2. 한 칸 뒤로 이동

   3. 반대편으로 점프 이때 원래 있던 칸보다 k칸 앞의 칸으로 이동해야 한다
      현재 있는 칸이 i번 칸이라면 i+k칸으로 이동한다

    N번 칸보다 더 큰 칸으로 이동한 경우 게임은 클리어


    1초가 지날때마다 한칸씩 사라진다  1초가 지나면 1번째 칸  2초는 2번째 칸
    편의상 유저가 먼저 움직이고 칸이 사라진다

    N의 최대값은 10만

    각 칸의 정보가 주어졌을때 게임을 클리할 수 있는지 확인한다

    가장 처음에 유저는 왼쪽 줄의 1번 칸위에 있다


    풀이방법

    1. dfs
    - 최악의 경우 즉 모든 맵이 뚫려있고 k는 1이고 마지막 N-2번째부터 막혀있다면 3^N-2 의 경우수를 모두 확인해야 한다
    처음 선택할 수 있는건 3가지 경우의 수  *  그다음도 3가지 경우의 수
    - 즉 너무 비효율적으로 된다

    2. bfs
    - bfs 는 레벨별로 방문하며 최단거리를 계산할때 주로 사용한다 여기서 bfs가 왜 사용돼야 할까..?
    - 초단위를 레벨로 잡으면 1초안에 방문가능한 모든곳 -> 2초안에 방문가능한 모든곳 이런식으로 방문하게 된다
      게임을 클리어할 수 있는지 확인해야 하는데 1초마다 앞의 칸이 사라지기 때문에 최단거리로 이동해야만 각 초에 방문할 수 있는
      칸을 정상적으로 모두 방문하게 된다

      즉 2초에 bfs 방식으로 이동하면 8번째 칸에 도달할 수 있는데 bfs가 아닌 다른 방식으로 이동해서 도달하지 못하게 되는 문제를
      막을 수 있는것 그래서 bfs 방식을 사용하게 된다


      구현 방법
      - 내부 클래스를 둔다 ( 현재 위치 (칸의 위치와 두 지도 중 어디인지) , 현재 몇초의 상태인지)



     */


    static class State {

        int map;
        int loc;
        int sec;

        State(int map , int loc, int sec) {
            this.map = map;
            this.loc = loc;
            this.sec = sec;
        }

    }

    public static void main(String[] args) throws Exception{

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine()," ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());


        int[][] map = new int[2][n+1];
        boolean[][] visited = new boolean[2][n+1];

        int[] command = {1,-1,k};


        for(int i=0; i<=1; i++) {

            String input = in.readLine();

            for(int j=1; j<=n; j++) {
                map[i][j]=input.charAt(j-1)-48;

            }
        }


        Queue<State> q= new LinkedList<>();

        visited[0][1]=true;
        q.add(new State(0,1,0));

        int answer=0;

        loop:while(!q.isEmpty()) {


            State s = q.poll();

            int nextLoc = s.loc;
            int nextMap = s.map;
            for(int i=0; i<=2; i++) {


                if(i==2) nextMap=  ((s.map+1)%2);

                nextLoc= s.loc + command[i];


                // 이미 사라진 그리고 다음에 사라질 칸은 이동할 수 없다
                if(s.sec+1<nextLoc) {

                    if(nextLoc>n) {
                        answer=1;
                        break loop;
                    }
                    else {

                        if(!visited[nextMap][nextLoc]&&map[nextMap][nextLoc]==1) {
                            visited[nextMap][nextLoc] = true;
                            q.add(new State(nextMap,nextLoc,s.sec+1));
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }

}
