package study56;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1753 {

	static class State implements Comparable<State> {

		int number, wegiht;

		State(int number, int weight) {
			this.number = number;
			this.wegiht = weight;
		}

		@Override
		public int compareTo(State o) {

			return this.wegiht - o.wegiht; // 가중치가 가장 작은 정점부터 나오게 된다
		}
	}

	static List<int[]> node[];
	static int v, e;
	static int distance[];
	static boolean visited[];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(in.readLine());
		distance = new int[v + 1];
		visited = new boolean[v + 1];
		node = new ArrayList[v + 1];
		for (int i = 1; i <= v; i++) {
			distance[i] = Integer.MAX_VALUE;
			node[i] = new ArrayList<>();
		}

		for (int i = 1; i <= e; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			node[a].add(new int[] { b, weight });

		}

		

		dijkstra(start);
		
		
		for(int i=1; i<=v; i++)
		{
			if(distance[i]!=Integer.MAX_VALUE)
			System.out.println(distance[i]);
			
			else {
				System.out.println("INF");
			}
		}
	}

	static void dijkstra(int start) {

		PriorityQueue<State> pq = new PriorityQueue<>();

		distance[start] = 0;
		pq.add(new State(start, 0));

		while (!pq.isEmpty()) {

			State now = pq.poll();
			int number = now.number;
			int weight = now.wegiht;
			if (!visited[number]) {

				visited[number] = true;

				for (int i = 0; i < node[number].size(); i++) {

					int[] next = node[number].get(i);

					if (!visited[next[0]] && distance[next[0]] > weight + next[1]) {

						distance[next[0]] = weight + next[1];
						pq.add(new State(next[0], distance[next[0]]));

					}

				}

			}

		}

	}

}
