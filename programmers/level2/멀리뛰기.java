public class 멀리뛰기 {
    public long solution(int n) {

        Long[] dp = new Long[2001];
        dp[1] = 1L;
        dp[2] = 2L;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1234567;
        }
        long answer = dp[n];
        return answer;
    }
}
