package study47;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj4577_소코반 {

	static char map[][];
	static int nowR, nowC;
	static int r, c;

	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static int direct[] = new int[96];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		direct['U'] = 1;
		direct['D'] = 0;
		direct['L'] = 2;
		direct['R'] = 3;
		int boxCnt;
		int game = 0;
		while (true) {

			st = new StringTokenizer(in.readLine(), " ");
			game++;
			boxCnt = 0;
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			if (r == 0 && c == 0)
				break;

			map = new char[r + 1][c + 1];

			for (int i = 1; i <= r; i++) {
				String input = in.readLine();
				for (int j = 1; j <= c; j++) {

					map[i][j] = input.charAt(j - 1);

					if (map[i][j] == 'w') {
						map[i][j] = '.';
						nowR = i;
						nowC = j;
					} else if (map[i][j] == 'W') {
						map[i][j] = '+';
						nowR = i;
						nowC = j;
					}

					if (map[i][j] == 'b') {
						boxCnt++;
					}
				}
			}

			char input[] = in.readLine().toCharArray();

			int count = 0;
			if (boxCnt != 0)
				count = simulation(input, boxCnt);

			if (count != 0) {
				sb.append("Game " + game + ": incomplete\n");
			} else {
				sb.append("Game " + game + ": complete\n");
			}

			for (int i = 1; i <= r; i++) {
				for (int j = 1; j <= c; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
		}

		System.out.println(sb.toString());
	}

	static int simulation(char input[], int boxCnt) {

		for (int i = 0; i < input.length; i++) {
			char dir = input[i];

			int nextR = nowR + dx[direct[dir]];
			int nextC = nowC + dy[direct[dir]];

			if (map[nextR][nextC] == '.' || map[nextR][nextC] == '+') // 지시한 방향이 빈칸인 경우 그 칸으로 이동
			{
				nowR = nextR;
				nowC = nextC;
			}

			else if (map[nextR][nextC] == 'b' || map[nextR][nextC] == 'B') // 지시한 방향에 박스가 있는 경우?
			{

				int nnextR = nextR + dx[direct[dir]];
				int nnextC = nextC + dy[direct[dir]];

				if (map[nnextR][nnextC] == '.' || map[nnextR][nnextC] == '+') // 박스가 이동할 칸이 있는지?
				{
					nowR = nextR;
					nowC = nextC;

					if (map[nextR][nextC] == 'b') {
						map[nextR][nextC] = '.'; // b였다면 빈공간이니 .로 바꿔준다
					}

					else if (map[nextR][nextC] == 'B') // B였다면 비어있는 목표점이니 +로 바꿔준다
					{
						boxCnt++; // B->b
						map[nextR][nextC] = '+';
					}

					if (map[nnextR][nnextC] == '.') {
						map[nnextR][nnextC] = 'b';
					}

					else { // b->B
						boxCnt--;
						map[nnextR][nnextC] = 'B';
					}

				}

			}

			if (boxCnt == 0)
				break;
		}

		if (map[nowR][nowC] == '.') {
			map[nowR][nowC] = 'w';
		} else {
			map[nowR][nowC] = 'W';
		}

		return boxCnt;
	}
}
