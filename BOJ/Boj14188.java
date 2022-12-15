package study68;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj14188 {

	static class Dao {

		int x, y, cnt;
		String record;

		Dao(int x, int y, int cnt, String record) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.record = record;
		}

	}

	static int h, w, n;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[] dir = new int['Z'];
	static char[][] map;
	static char[][] moveInfo;
	static int sx, sy;

	public static void main(String[] args) throws IOException {

		dir['A'] = 2;
		dir['W'] = 1;
		dir['D'] = 3;

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());

		map = new char[h + 1][w + 1];

		for (int i = 1; i <= h; i++) {

			String input = in.readLine();

			for (int j = 1; j <= w; j++) {

				char val = input.charAt(j - 1);

				// 다오의 위치
				if (val == 'D') {
					sx = i;
					sy = j;
					map[i][j] = '.';
				} else {
					map[i][j] = val;
				}

			}

		}

		n = Integer.parseInt(in.readLine());

		moveInfo = new char[n + 1][2];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");

			moveInfo[i][0] = st.nextToken().charAt(0);
			moveInfo[i][1] = st.nextToken().charAt(0);
		}

		String ans=solution();
		
		if(ans.equals("")) { 
			
			System.out.println("NO");
		}
		
		else {
			System.out.println("YES");
			System.out.println(ans);
		}

	}

	static String solution() {

		String ans="";
		Queue<Dao> q = new LinkedList<>();

		
		q.add(new Dao(sx, sy, 0, ""));

		loop:while (!q.isEmpty()) {

			Dao now = q.poll();

			for (int i = 0; i <= 1; i++) {

				// 이동할 수 있는 방향
				char val = moveInfo[now.cnt + 1][i];

				int nx = now.x + dx[dir[val]];
				int ny = now.y + dy[dir[val]];

				if (nx < 1 || nx > h || ny < 1 || ny > w || map[nx][ny] == '@')
					continue;

				// 빈공간인 경우
				if (map[nx][ny] == '.') {

					if(now.cnt+1<n)
					q.add(new Dao(nx, ny, now.cnt + 1, now.record + val));

				}
				
				else if(map[nx][ny]=='Z') {
					
					ans=now.record+val;
					break loop;
				}

			}

		}
		return ans;
	}
}
