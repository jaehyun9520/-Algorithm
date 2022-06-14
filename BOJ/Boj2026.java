package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Boj2026_소풍 {

	static int k, n, f;
	static int friends[][];
	static int used[];
	static int ansList[];
	static boolean flag = false;
	static ArrayList<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		k = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		f = Integer.parseInt(st.nextToken());

		ansList = new int[k + 1];
		friends = new int[n + 1][n + 1]; // 친구관계 표시
		used = new int[n + 1]; // 사용여부 ( 조합을 만들 리스트에 넣기 위해서)

		for (int i = 1; i <= f; i++) {
			st = new StringTokenizer(in.readLine(), " ");

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			friends[a][b] = 1;
			friends[b][a] = 1;

			if (used[a] == 0) {
				used[a] = 1;
				list.add(a);
			}
			if (used[b] == 0) {
				used[b] = 1;
				list.add(b);
			}

		}

		// 조합을 만들 학생 리스트를 오름차순으로 정렬?

		Collections.sort(list);

		comb(0, 0);
		
		if(flag)
		{
			for(int i=1; i<=k; i++)
			{
				System.out.println(ansList[i]);
			}
		}
		
		else {
			System.out.println(-1);
		}

	}

	static void comb(int cnt, int idx) {

		
		

		
		if (cnt == k) {
			flag = true;
			return;
		}

		for (int i = idx; i < list.size(); i++) {
			
			if(flag) return;
			
			
			int next = list.get(i);

			if (cnt == 0) {
				ansList[cnt + 1] = next;
				comb(cnt + 1, i + 1);
			}

			else {
				boolean check = true;

				for (int j = 1; j <= cnt; j++) {
					if (friends[ansList[j]][next] == 0) // 친구관계가 하나라도 아닐시?
					{
						check = false;
						break;
					}

				}

				if (check) {

					ansList[cnt + 1] = next;
					comb(cnt + 1, i + 1);
				}

			}

		}

	}
}
