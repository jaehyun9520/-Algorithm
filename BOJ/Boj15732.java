package study56;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15732 {

	static int n, k, d, ans = 0;
	static int rule[][]; // a번 상자부터 b상자까지 c개 간격

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		rule = new int[k + 1][3];
		for (int i = 1; i <= k; i++) {

			st = new StringTokenizer(in.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			rule[i][0] = a;
			rule[i][1] = b;
			rule[i][2] = c;

		}

		binarySearch();
		System.out.println(ans);
	}

	static void binarySearch() {

		int low = 1, high = n, mid = 0;

		while (low <= high) {

			mid = (low + high) / 2;

			int val = check(mid); 
			if (val == 1) { 
				
				ans = mid;
				high = mid - 1;
			}

			else {
				low = mid + 1;

			}

		}
	}

	static int check(int boxNum) {

		int totalCount = 0;

		for (int i = 1; i <= k; i++) {
			int sNum = rule[i][0];
			int eNum = rule[i][1];
			int dis = rule[i][2];

			// boxNum a b 인 경우 해당 규칙으로 만들어지는 도토리는 없다
			if (sNum <= boxNum) {

				// a ~ boxNum b 라면 eNum을 boxNum에 맞춰준다
				if (eNum > boxNum)
					eNum = boxNum;

				int diff = eNum - sNum;
				totalCount += (diff / dis + 1);

				if (totalCount >= d)
					return 1;
				
			}

		}
		return 0;
	}
}
