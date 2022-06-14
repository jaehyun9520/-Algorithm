package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj4883_삼각그래프 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int cnt = 1;
		while (true) {
			int k = Integer.parseInt(in.readLine());

			if (k == 0)
				break;

			int graph[][] = new int[k + 1][4];

			for (int i = 1; i <= k; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 1; j <= 3; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			graph[1][3] = graph[1][2] + graph[1][3];
			for (int i = 1; i <= 3; i++) {
				if (i == 1) {
					graph[2][i] = graph[2][i] + graph[1][2];
				}
				else if (i == 2) {
					graph[2][i] = graph[2][i] + Integer.min(graph[2][1], Integer.min(graph[1][2], graph[1][3]));

				}

				else if(i==3){
					graph[2][i] = graph[2][i] + Integer.min(graph[2][2], Integer.min(graph[1][2], graph[1][3]));

				}

			}

			for (int i = 3; i <= k; i++) {
				for (int j = 1; j <= 3; j++) {
					if (j == 1) {
						graph[i][j] = graph[i][j] + Integer.min(graph[i - 1][j], graph[i - 1][j + 1]);
					}

					else if (j == 2) {
						graph[i][j] = graph[i][j] + Integer.min(Integer.min(graph[i - 1][j - 1], graph[i][j - 1]),
								Integer.min(graph[i - 1][j], graph[i - 1][j + 1]));
					}

					else {
						graph[i][j] = graph[i][j]
								+ Integer.min(graph[i][j - 1], Integer.min(graph[i - 1][j - 1], graph[i - 1][j]));
					}
				}

			}
			sb.append(cnt + ". " + graph[k][2]+"\n");

			cnt++;

		}
		System.out.println(sb.toString());

	}
}
