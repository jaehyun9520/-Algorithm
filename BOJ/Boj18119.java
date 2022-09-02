import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Boj18119 {

	static int n, m;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
	

		int wordList[]=new int[n+1];
		for (int i = 1; i <= n; i++) {
			int word = 0;

			String input = in.readLine();

			for (int j = 0; j < input.length(); j++) {
				int val = input.charAt(j) - 97;

				word = word | (1 << val);

			}
			wordList[i]=word;

		}

		int state = (1 << 27) - 1; // 전체 단어를 다 알고있는 상태

		for (int i = 1; i <= m; i++) {
			int cnt = 0;
			st = new StringTokenizer(in.readLine(), " ");
			int query = Integer.parseInt(st.nextToken());
			int val = st.nextToken().charAt(0) - 'a';

			if (query == 1) {
				state = state & (~(1 << val));
			} else {
				state = state | (1 << val);
			}

			for (int j = 1; j <=n; j++) {
				int word = wordList[j];

				if ((word & state) == word) {
					cnt++;
				}

			}
			
			sb.append(cnt+"\n");

		}
		
		System.out.println(sb.toString());

	}

}
