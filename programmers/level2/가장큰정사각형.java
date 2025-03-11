public class 가장큰정사각형 {

    public int solution(int[][] board) {

        int answer = 0;
        int[][] dp = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {

            for (int j = 0; j < board[0].length; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = board[i][j] == 1 ? 1 : 0;
                } else if(board[i][j]==1) {
                    int min = Integer.min(dp[i - 1][j], Integer.min(dp[i - 1][j - 1], dp[i][j - 1]));
                    dp[i][j] = min + 1;
                }

                answer = Integer.max(dp[i][j], answer);
            }
        }

        return answer * answer;
    }
}
