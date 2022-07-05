package study45;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj1062_가르침 {

	static int n, k, ans = 0, cnt = 0;
	static boolean used[] = new boolean[26];
	static ArrayList<String> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= n; i++) {
			list.add(in.readLine());
		}

		if (k < 5) {
			System.out.println(ans);
		}

		else {
			cnt = k - 5; // 뽑아야하는 개수
			used['a'-97] = true;
			used['n'-97] = true;
			used['t'-97] = true;
			used['i'-97] = true;
			used['c'-97] = true;

			comb(0, 0);

			System.out.println(ans);
		}

	}

	static void comb(int index, int count) {
		if (count == cnt) {
			int cnt1 = 0;
			for (int i = 0; i < n; i++) {

				boolean flag = true;
				String words = list.get(i);

				for (int j = 4; j < words.length() - 4; j++) {
					if (!used[words.charAt(j) - 97]) {
						flag = false;
						break;
					}

				}
				
				if(flag) cnt1++;

			}
			
			ans=Integer.max(ans,cnt1);

		}

		else {

			for (int i = index; i < 26; i++) {
				if (used[i] == false) {
					used[i] = true;
					comb(i + 1, count + 1);
					used[i] = false;
				}

			}

		}

	}
}
