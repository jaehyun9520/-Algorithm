public class 비밀코드해독 {
    static boolean[] used = new boolean[31];
    static int answer=0;
    public static void main(String[] args) {


        int n = 10;
        int[][] q = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {3, 7, 8, 9, 10}, {2, 5, 7, 9, 10}, {3, 4, 5, 6, 7}};
        int[] ans= {2,3,4,3,3};


        System.out.println(solution(n,q,ans));
    }

    public static int solution(int n, int[][] q, int[] ans) {




        createSecretCode(0,n,1,q,ans);



        return answer;
    }


    static void createSecretCode(int cnt ,int n, int loc ,int[][] q, int[] ans) {


        // nC5인 경우 입력값으로 주어진 시도와 비교해서 시스템이 준 응답값이 나올 수 있는지 확인한다
        if(cnt==5) {
            for (int i = 0; i < q.length; i++) {

                int responseVal = ans[i];

                int total = 0;
                for (int j = 0; j < q[i].length; j++) {
                    if (used[q[i][j]]) {
                        total++;
                    }
                }
                if (total != responseVal) {
                    return;
                }
            }

            answer++;

        }
        else {
            for(int i=loc; i<=n; i++) {
                used[i]=true;
                createSecretCode(cnt+1,n,i+1,q,ans);
                used[i]=false;
            }
        }
    }

}
