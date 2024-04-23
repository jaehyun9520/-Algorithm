import java.util.*;


public class 석유시추 {

    public int solution(int[][] land) {
        int n=land.length;
        int m=land[0].length;
        int[] cnt =new int[m];
        boolean[][] visited=new boolean[n][m];


        for(int i=0; i<n; i++) {

            for(int j=0; j<m; j++) {

                if(land[i][j]==1&&!visited[i][j]) {
                    visited[i][j]=true;
                    bfs(i,j,n,m,land,visited,cnt);
                }
            }
        }
        int answer=0;
        for(int i=0; i<m; i++) {

            answer=Integer.max(answer,cnt[i]);
        }
        return answer;
    }

    static void bfs(int i, int j,int n, int m,int[][] land,boolean[][] visited,int[] cnt) {

        boolean[] visitedRow= new boolean[m];
        List<Integer> rowList=new ArrayList<>();
        int[] dx={1,-1,0,0};
        int[] dy={0,0,1,-1};
        int size=1;
        Queue<int[]> q= new LinkedList<>();
        visitedRow[j]=true;
        rowList.add(j);

        q.add(new int[]{i,j});


        while(!q.isEmpty()) {
            int[] now= q.poll();

            for(int k=0; k<=3; k++) {
                int nx=now[0]+dx[k];
                int ny=now[1]+dy[k];

                if(nx<0||nx>n-1||ny<0||ny>m-1||land[nx][ny]==0||visited[nx][ny]) continue;

                size++;
                visited[nx][ny]=true;
                if(!visitedRow[ny]) {
                    visitedRow[ny]=true;
                    rowList.add(ny);
                }
                q.add(new int[] {nx,ny});
            }
        }
        for(int k=0; k<rowList.size(); k++) {
            cnt[rowList.get(k)]+=size;
        }
    }
}
