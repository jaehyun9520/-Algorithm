import java.util.ArrayList;
import java.util.List;

public class 완전범죄 {



    public static void main(String[] args) {


        int[][] info = {{3, 1}, {2, 1}, {2, 2}, {1, 2}, {1, 3}};
        int n = 6;
        int m = 6;


        System.out.println(solution(info, n, m));


    }

    public static int solution(int[][] info, int n, int m) {

        int ans = Integer.MAX_VALUE;

        List<int[]> preList = new ArrayList<>();

        preList.add(new int[]{0, 0});

        for (int i = 0; i < info.length; i++) {

            boolean[][] used = new boolean[n][m];
            List<int[]> nextList = new ArrayList<>();
            int nextA = info[i][0];
            int nextB = info[i][1];

            for (int j = 0; j < preList.size(); j++) {


                int a = preList.get(j)[0];
                int b = preList.get(j)[1];


                if (a + nextA < n&& !used[a+nextA][b]) {
                    used[a+nextA][b]=true;
                    nextList.add(new int[]{a + nextA, b});
                }
                if (b + nextB < m&&!used[a][b+nextB]) {

                    used[a][b+nextB]=true;
                    nextList.add(new int[]{a, b + nextB});
                }
            }

            preList = nextList;
        }


        for (int i = 0; i < preList.size(); i++) {


            ans = Integer.min(ans, preList.get(i)[0]);
        }

        if (ans == Integer.MAX_VALUE) {
            ans = -1;
        }


        return ans;


    }
}