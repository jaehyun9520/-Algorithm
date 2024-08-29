package programmers.level3;

public class 정수삼각형 {

    public static int solution(int[][] triangle) {

        int answer = 0;
        int[][] dp =new int[triangle.length][triangle.length];

        dp[0][0]=triangle[0][0];

        for(int i=1; i< triangle.length; i++) {

            for(int j=0; j<triangle[i].length; j++) {
                if(j==0) {
                    dp[i][j]= dp[i-1][j]+triangle[i][j];
                }
                else if(j==triangle[i].length-1) {
                    dp[i][j]=dp[i-1][j-1]+triangle[i][j];
                }
                else {
                    dp[i][j]=triangle[i][j]+Integer.max(dp[i-1][j],dp[i-1][j-1]);
                }
                if(i==triangle.length-1) {
                    answer=Integer.max(answer,dp[i][j]);
                }
            }
        }
        return answer;
    }
}
