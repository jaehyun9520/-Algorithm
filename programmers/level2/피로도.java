public class 피로도 {

    static boolean[] used;
    static int ans = 0;

    public int solution(int k, int[][] dungeons) {

        used = new boolean[dungeons.length];

        dfs(k, dungeons, 0);
        int answer = ans;
        return answer;
    }

    static void dfs(int k, int[][] dungeons, int cnt) {

        ans = Integer.max(ans, cnt);

        for (int i = 0; i < dungeons.length; i++) {

            if (!used[i] && k >= dungeons[i][0]) {

                used[i] = true;
                dfs(k - dungeons[i][1], dungeons, cnt + 1);
                used[i] = false;
            }
        }
    }
}
