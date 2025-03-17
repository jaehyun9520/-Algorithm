import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2960 {

    public static void main(String[] args) throws Exception {


            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(in.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int cnt = 0;
            boolean[] visited = new boolean[n + 1];

            loop:
            for (int i = 2; i <= n; i++) {

                if (!visited[i]) {
                    visited[i] = true;
                    cnt++;
                    if (cnt == k) {
                        System.out.println(i);
                        break loop;
                    }

                    for (int j = i * 2; j <= n; j += i) {

                        if (!visited[j]) {

                            visited[j] = true;
                            cnt++;

                            if (cnt == k) {
                                System.out.println(j);
                                break loop;
                            }


                        }


                    }


                }


            }


        }

}
