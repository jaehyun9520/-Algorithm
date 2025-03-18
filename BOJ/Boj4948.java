import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj4948 {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int max = 123456 * 2;
        // 123456 * 2
        int[] sum = new int[max + 1];
        boolean[] visited = new boolean[max + 1];

        for (int i = 2; i <= max; i++) {

            if (!visited[i]) {

                visited[i] = true;
                sum[i] = sum[i - 1] + 1;

                for (int j = i * 2; j <= max; j += i) {
                    visited[j] = true;
                }
            } else {
                sum[i] = sum[i - 1];
            }
        }

        StringBuilder sb = new StringBuilder();

        while (true) {

            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }

            sb.append(sum[2 * n] - sum[n]).append("\n");
        }

        System.out.println(sb.toString());
    }
}
