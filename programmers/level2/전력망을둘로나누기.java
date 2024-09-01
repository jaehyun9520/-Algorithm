import java.util.*;

public class 전력망을둘로나누기 {
    static boolean[] visited;
    public static void main(String[] args) {

        int[][] wires= {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};

        solution(9,wires);

    }
    public static int solution(int n, int[][] wires) {

        int answer = 101;
        // 끊어진 전선은 1  아닌 전선은 0으로 보면 된다
        int[][] unconnected =new int[101][101];

        List<Integer> list[] =new List[n+1];

        for(int i=1; i<=n; i++) {

            list[i]=new ArrayList<>();
        }

        for(int i=0; i<wires.length; i++) {

            int n1=wires[i][0];
            int n2=wires[i][1];

            list[n1].add(n2);
            list[n2].add(n1);

        }


        for(int i=0; i<wires.length; i++) {

            visited=new boolean[n+1];

            unconnected[wires[i][0]][wires[i][1]]=1;
            unconnected[wires[i][1]][wires[i][0]]=1;
            List<Integer> answerList =new ArrayList<>();

            for(int j=1; j<=n; j++) {


                if(!visited[j]) {

                    visited[j]=true;
                    int cnt=1;
                    Queue<Integer> q =new LinkedList<>();
                    q.add(j);
                    while(!q.isEmpty()) {

                        int num = q.poll();

                        for(int k=0; k<list[num].size(); k++) {

                            int next=list[num].get(k);

                            if(!visited[next]&&unconnected[num][next]==0) {
                                visited[next]=true;
                                cnt++;
                                q.add(next);
                            }
                        }
                    }

                    answerList.add(cnt);
                }


            }
            System.out.println(answerList.get(0) +"  "+answerList.get(1));
            answer=Integer.min(answer,Math.abs(answerList.get(0)-answerList.get(1)));

            unconnected[wires[i][0]][wires[i][1]]=0;
            unconnected[wires[i][1]][wires[i][0]]=0;
        }



        return answer;
    }
}
