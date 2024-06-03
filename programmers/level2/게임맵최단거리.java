package programmers.level2;
    import java.util.*;


/**
 *  bfs 방식
 */
public class 게임맵최단거리 {


    static int n,m;
    static boolean[][] visited;
    static int dx[]={1,-1,0,0};
    static int dy[]={0,0,1,-1};

    public int solution(int[][] maps) {

        n=maps.length;
        m=maps[0].length;

        visited=new boolean[n][m];



        int answer = bfs(maps);
        return answer;
    }

    static int bfs(int[][] maps) {

        int answer=-1;
        Queue<int[]> q=new LinkedList<>();
        visited[0][0]=true;

        q.add(new int[] {0,0,1});

        while(!q.isEmpty()) {

            int[] now=q.poll();
            int x=now[0];
            int y=now[1];
            int cnt=now[2];

            if(x==n-1&&y==m-1) {
                answer=cnt;
                break;
            }


            for(int i=0; i<=3; i++) {

                int nx=x+dx[i];
                int ny=y+dy[i];

                if(nx<0||nx>=n||ny<0||ny>=m||visited[nx][ny]||maps[nx][ny]==0) continue;


                visited[nx][ny]=true;
                q.add(new int[] {nx,ny,cnt+1});

            }


        }



        return answer;

    }


}








