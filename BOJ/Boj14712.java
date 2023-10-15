import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14712 {

    static boolean[][] board;
    static int n,m;
    static int answer=0;
    public static void main(String[] args) throws IOException {

        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(in.readLine());


        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        board=new boolean[n+1][m+1];
        dfs(1,1);
        System.out.println(answer);
    }

    static void dfs(int x, int y) {

        int nx=x;
        int ny=y+1;
        if(ny>m) { ny=1; nx++; }


        if(x==n+1&&y==1) {
            answer++;
            return;
        }

        if(!board[x-1][y]||!board[x][y-1]||!board[x-1][y-1]) {
            board[x][y]=true;
            dfs(nx,ny);
            board[x][y]=false;
        }


        dfs(nx,ny);





    }
}
