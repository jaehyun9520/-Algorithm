package study58;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj18428 {

	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };
	static boolean ans = false;
	static int map[][];
	static int n;
	static List<int[]> empty = new ArrayList<>();
	static List<int[]> teacher = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());
		map = new int[n + 1][n + 1];

		StringTokenizer st;

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= n; j++) {
				char val = st.nextToken().charAt(0);

				if (val == 'T') {
					teacher.add(new int[] { i, j });
				} else if (val == 'X') {
					empty.add(new int[] { i, j });
				}

				map[i][j] = val;
			}
		}

		comb(0, 0);
		if(ans) {
			System.out.println("YES");
		}
		else {
			System.out.println("NO");
		}

	}

	static boolean simul(int x, int y) {

		for (int i = 0; i <= 3; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			while (true) {

				if (nx < 1 || nx > n || ny < 1 || ny > n || map[nx][ny] == 'O')
					break;

				if (map[nx][ny] == 'S') {
					return false;  // 학생 발견 
				}

				nx+=dx[i];
				ny+=dy[i];
			}

		}
		
		return true; //학생 발견 못한 경우

	}

	static void comb(int cnt, int number) {

		if (ans)
			return; // 이미 정답이 나왔으면 중단

		// 빈공간에 장애물 3개를 다 설치했으면 시뮬레이션
		if (cnt == 3) {


			for (int i = 0; i < teacher.size(); i++) {
				if (!simul(teacher.get(i)[0], teacher.get(i)[1])) {
				return;
				}

			}
				ans = true;
		}

		else {

			for (int i = number; i < empty.size(); i++) {
				map[empty.get(i)[0]][empty.get(i)[1]] = 'O';
				comb(cnt + 1, i + 1);
				map[empty.get(i)[0]][empty.get(i)[1]] = 'X';
			}
		}

	}
}
