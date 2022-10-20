package study60;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Boj16722 {

	static String picture[][] = new String[10][3]; // 9개의 그림
	static boolean pComb[][][] = new boolean[10][10][10];
	static int select[] = new int[3];
	static int count = 0;
	static String list[] = { "", "CIRCLE", "TRIANGLE", "SQUARE", "YELLOW", "RED", "BLUE", "GRAY", "WHITE", "BLACK" };
	static Map<String, Integer> map = new HashMap<>();

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 1; i <= 9; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			picture[i][0] = st.nextToken(); // 모양
			picture[i][1] = st.nextToken(); // 색
			picture[i][2] = st.nextToken(); // 배경

			map.put(list[i], i);
		}

		comb(1, 0);

		
		int n = Integer.parseInt(in.readLine());
		int used = 0;
		int score = 0;
		boolean flag=true;
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			char command = st.nextToken().charAt(0);

			if (command == 'H') {
				List<Integer> list = new ArrayList<>();
				list.add(Integer.parseInt(st.nextToken()));
				list.add(Integer.parseInt(st.nextToken()));
				list.add(Integer.parseInt(st.nextToken()));
				Collections.sort(list);
				if (pComb[list.get(0)][list.get(1)][list.get(2)]) {
					pComb[list.get(0)][list.get(1)][list.get(2)] = false; // 이미 사용한 조합은 false
					used++;
					score++;

				} else {
					score--;
				}
			}

			else {
				if (used == count&&flag) {
					flag=false;
					score += 3;
				} else {
					score--;
				}
			}

		}
		
		System.out.println(score);

	}

	static void comb(int num, int cnt) {

		// 3개의 그림 선택
		if (cnt == 3) {
			int n1 = select[0];
			int n2 = select[1];
			int n3 = select[2];
			if (combCheck(n1, n2, n3)) {
				count++;
				pComb[select[0]][select[1]][select[2]] = true;
			}
		}

		else {
			for (int i = num; i <= 9; i++) {
				select[cnt] = i;
				comb(i + 1, cnt + 1);
			}
		}

	}

	static boolean combCheck(int n1, int n2, int n3) {

		int c1 = map.get(picture[n1][1]);
		int c2 = map.get(picture[n2][1]);
		int c3 = map.get(picture[n3][1]);

		if ((c1 == c2 && c2 == c3) || ((c1 != c2) && (c2 != c3) && (c1 != c3))) {

			int s1 = map.get(picture[n1][0]);
			int s2 = map.get(picture[n2][0]);
			int s3 = map.get(picture[n3][0]);
			if ((s1 == s2 && s2 == s3) || ((s1 != s2) && (s2 != s3) && (s1 != s3))) {

				int bg1 = map.get(picture[n1][2]);
				int bg2 = map.get(picture[n2][2]);
				int bg3 = map.get(picture[n3][2]);
				if ((bg1 == bg2 && bg2 == bg3) || ((bg1 != bg2) && (bg2 != bg3) && (bg1 != bg3))) {
					return true;
				}
			}

		}

		return false;
	}
}
