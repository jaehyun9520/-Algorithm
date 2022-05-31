package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj4256_트리 {

	static int preOrder[];
	static int inOrder[];
	static int tree[][]; // 왼쪽 , 오른쪽
	static int n = 0;
	static int index;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(in.readLine());

		for (int i = 1; i <= t; i++) {
			n = Integer.parseInt(in.readLine());
			index = 2;
			preOrder = new int[n + 1];
			inOrder = new int[n + 1];
			tree = new int[n + 1][3]; // 내 위에 루트 ,왼쪽,오른쪽 트리

			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= n; j++) {

				preOrder[j] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= n; j++) {
				int val = Integer.parseInt(st.nextToken());

				inOrder[val] = j; // 해당 숫자가 중위순회의 어느 위치에 있는지?
			}

			// 루트는 j=1

			for (int j = 2; j <= n; j++) // 전위 순회기준으로 돌면서 트리 만들기
			{
				int now = preOrder[j - 1];
				int next = preOrder[j];

				if (inOrder[now] > inOrder[next]) // 중위순회 기준으로 내 왼쪽에 위치
				{

					tree[now][1] = next; // 왼쪽 서브트리 루트에 추가
					tree[next][0] = now; // 내 루트가 누구인지 추가
				}

				else { // 중위 순회 기준으로 나보다 크다면

					// 내 루트들을 거슬러 올라가면서 다음번 트리의 인덱스보다 작으면서 가장 큰 값을 찾는다

					int index = 0;
					int num = 0;
					int pre = now;
					while (true) {
						if (pre == 0)
							break;

						if (inOrder[pre] < inOrder[next] && index < inOrder[pre]) {

							index = inOrder[pre];
							num = pre;
						}

						pre = tree[pre][0];
					}

					// num이 해당 트리의 부모
					tree[num][2] = next;
					tree[next][0] = num;

				}

			}

			postOrder(preOrder[1]);

			System.out.println();
		}

	}

	static void postOrder(int now) {
		if (tree[now][1] != 0) {
			postOrder(tree[now][1]);
		}

		if (tree[now][2] != 0) {
			postOrder(tree[now][2]);
		}

		System.out.print(now + " ");

	}

}
