package study45;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj17178_줄서기 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		Stack<int[]> s = new Stack<>(); // 0 이 알파벳 대문자 1이 뒤에 번호
		ArrayList<int[]> list = new ArrayList<>(); // 줄 서 있는 사람들 (n*5)
		boolean flag = true;
		int e1 = 0;
		int e2 = 0;
		int n = Integer.parseInt(in.readLine());

		for (int i = 1; i <= n; i++) {

			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= 5; j++) {
				String input = st.nextToken();

				StringTokenizer st2 = new StringTokenizer(input, "-");

				int num1 = st2.nextToken().charAt(0);
				int num2 = (Integer.parseInt(st2.nextToken()));

				list.add(new int[] { num1, num2 });

			}

		}

		loop: for (int i = 0; i < list.size(); i++) {
			
			int now1 = list.get(i)[0];
			int now2 = list.get(i)[1];

			while (true) {
				if (s.isEmpty()) {  //대기줄이 비어있다면?
					s.add(new int[] { now1, now2 });
					break;
				}

				else { //대기줄에 누군가 있다면?

					if (now1 < s.peek()[0] || (now1 == s.peek()[0] && now2 < s.peek()[1]))
					// 대기줄 가장 앞에 있는 사람보다 내가 먼저 들어가야한다면 대기줄로 들어가기
					{
						s.add(new int[] { now1, now2 });
						break;
					}

					else {
						// 대기줄 가장 앞에 있는 사람이 나보다 먼저 들어가야한다면 내보내기

						// 가장 최근에 들어간 사람과 비교해서 그 사람보다 우선순위가 낮은 경우만 정상 진행

						if (e1 < s.peek()[0] || (e1 == s.peek()[0] && e2 < s.peek()[1])) {
							e1 = s.peek()[0];
							e2 = s.peek()[1];
							s.pop();
						}

						else {
							flag = false;
							break loop;
						}

					}
				}

			}

			if (i == list.size() - 1 && !s.isEmpty()) // 마지막까지 정상 진행됐고 대기줄에 누군가가 남아있다면?
			{
				while (!s.isEmpty()) {
					if (e1 < s.peek()[0] || (e1 == s.peek()[0] && e2 < s.peek()[1])) {
						e1 = s.peek()[0];
						e2 = s.peek()[1];
						s.pop();
					}

					else {
						flag = false;
						break;
					}

				}
			}

		}

		if(flag) System.out.println("GOOD");
		
		else System.out.println("BAD");
		
	}
}
