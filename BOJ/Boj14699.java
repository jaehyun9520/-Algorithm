package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj14699 {


static int n,m;
static List<Integer> node[];
static int[] levelInfo;
static int[] dp;

    public static void main(String[] args) throws Exception {


        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(in.readLine());
        StringBuilder sb=new StringBuilder();

       n=Integer.parseInt(st.nextToken());
       m=Integer.parseInt(st.nextToken());


       node=new List[n+1];
       dp=new int[n+1];
       levelInfo= new int[n+1];

       for(int i=1; i<=n; i++) {
           node[i]=new ArrayList<>();
           dp[i]=-1;
       }

       st=new StringTokenizer(in.readLine());

       for(int i=1; i<=n; i++) {
           levelInfo[i]= Integer.parseInt(st.nextToken());
       }


       for(int i=1; i<=m; i++) {
st=new StringTokenizer(in.readLine());
           int a=Integer.parseInt(st.nextToken());
           int b=Integer.parseInt(st.nextToken());

           node[a].add(b);
           node[b].add(a);


       }


       for(int i=1; i<=n; i++) {

           dfs(i);
           sb.append(dp[i]+"\n");
       }

        System.out.println(sb.toString());

    }

    static void dfs(int num) {

        int max=0;
        int nowLevel=levelInfo[num];
        for(int i=0; i<node[num].size(); i++) {

            int next=node[num].get(i);
            int nextLevel=levelInfo[next];


            if(nowLevel<nextLevel) {

                if(dp[next]==-1) {
                   dfs(next);

                }
                max=Integer.max(max,dp[next]);


            }

        }


        dp[num]=max+1;


    }
}