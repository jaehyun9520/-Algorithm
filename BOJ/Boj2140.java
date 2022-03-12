package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Boj2140 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		int dx[] = { 1, -1, 0, 0, 1, 1, -1, -1 };
		int dy[] = { 0, 0, -1, 1, 1, -1, 1, -1 };

		int n, ans = 0;
		int input[][];
		int check[][];
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

			
		n = Integer.parseInt(in.readLine());
		input = new int[n + 1][n + 1];
		check = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			String list = in.readLine();

			for (int j = 1; j <= n; j++) {
				input[i][j] = list.charAt(j - 1);
			}

		}

		ArrayList<int[]> plus = new ArrayList<>();

		for (int i = 2; i < n; i++) {

			for (int j = 2; j < n; j++) {

				if (i > 2 && i < n - 1 && j > 2 && j < n - 1) {
					
					ans++;
				}

				else {

					boolean flag = true;

					for (int k = 0; k <= 7; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];

						if (input[nx][ny] >= '0' && input[nx][ny] <= '8') {
							
							
							if (check[nx][ny] >= input[nx][ny]-'0') {
							
								flag = false;
								break;
							} else {

								
								plus.add(new int[] { nx, ny });
							}

						}
					}

					if (flag) {
						ans++;
						for (int k = 0; k < plus.size(); k++) {
							int x = plus.get(k)[0];
							int y = plus.get(k)[1];

							check[x][y]++;

						}

					}

					plus.clear();

				}

			}

		}
		
		System.out.println(ans);

	}
}
