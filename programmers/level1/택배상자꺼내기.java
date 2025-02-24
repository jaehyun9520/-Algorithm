public class 택배상자꺼내기 {

    public static void main(String[] args) {

    }

    public static int solution(int n, int w, int num) {

        int answer = 0;

        int h = n / w + ((n % w != 0) ? 1 : 0);
        int cnt = 0;
        int[][] arr = new int[h + 1][w + 1];

        loop:
        for (int i = 1; i <= h; i++) {

            if (i % 2 == 1) {

                for (int j = 1; j <= w; j++) {

                    arr[i][j] = ++cnt;

                    if (cnt == n) {
                        break loop;
                    }
                }

            } else {

                for (int j = w; j >= 1; j--) {
                    arr[i][j] = ++cnt;

                    if (cnt == n) {
                        break loop;
                    }
                }
            }
        }

        int loc = num / w + ((num % w) != 0 ? 1 : 0);

        for (int i = 1; i <= w; i++) {

            if (arr[loc][i] == num) {
                answer++;
                for (int j = loc + 1; j <= h; j++) {

                    if (arr[j][i] != 0) {
                        answer++;
                    }
                }
            }
        }

        return answer;
    }
}
