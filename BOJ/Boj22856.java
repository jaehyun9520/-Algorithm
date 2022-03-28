package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj22856_트리순회 {

	static int N, count = 0, endPoint = 0, ans = 0;
	static int node[][];
	static boolean flag = true;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(in.readLine());
		node = new int[N + 1][2]; // 0은 왼쪽 노드 1은 오른쪽 노드

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine(), " ");

			int now, left, right;

			now = Integer.parseInt(st.nextToken());
			left = Integer.parseInt(st.nextToken());
			right = Integer.parseInt(st.nextToken());

			node[now][0] = left;
			node[now][1] = right;
		}

		inorder(1); // 중위 순회로 중위순회의 마지막 노드를 찾는다
		inorder2(1);

		System.out.println(ans);

	}

	static void inorder(int num) {
		if (node[num][0] != -1)
			inorder(node[num][0]);

		count++;
		if (count == N) {
			endPoint = num;
		}

		if (node[num][1] != -1)
			inorder(node[num][1]);
	}

	static void inorder2(int num) {

		if (node[num][0] != -1) // 1번
		{
			ans += 2;
			inorder2(node[num][0]);
		}

		if (node[num][1] != -1) // 2번
		{
			ans += 1;
			inorder2(node[num][1]);

			if (flag == true)
				ans += 1;
		}

		if (num == endPoint) {
			flag = false;
		}

	}
}
