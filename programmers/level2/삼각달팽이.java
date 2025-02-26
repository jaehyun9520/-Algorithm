
public class 삼각달팽이 {
    public static void main(String[] args) {
        solution(4);
    }

    static int[][] arr;

    public static int[] solution(int n) {

        arr = new int[n + 1][n + 1];

        simul(0, 1, n, 0);

        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }

        int[] answer = new int[sum];
        int cnt = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                answer[cnt++] = arr[i][j];
            }

        }

        return answer;
    }


    static void simul(int x, int y, int n, int num) {

        // n만큼 아래로

        for (int i = 1; i <= n; i++) {
            x = x + 1;
            arr[x][y] = ++num;
        }
        // n-1만큼 오른쪽으로
        for (int i = 1; i <= n - 1; i++) {
            y = y + 1;
            arr[x][y] = ++num;
        }
        // n-2만큼 왼쪽 위 대각선으로
        for (int i = 1; i <= n - 2; i++) {
            x = x - 1;
            y = y - 1;
            arr[x][y] = ++num;
        }
        if (n - 3 > 0) {
            simul(x, y, n - 3, num);
        }
    }

}