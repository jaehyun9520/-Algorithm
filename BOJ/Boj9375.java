package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Boj9375_패션왕신혜빈 {

	static Map<String, Integer> map = new HashMap<>();
	static ArrayList<Integer> list = new ArrayList<>();


	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int i = 1; i <= T; i++) {
			map.clear();
			int n = Integer.parseInt(in.readLine());

			for (int j = 1; j <= n; j++) {
				st = new StringTokenizer(in.readLine(), " ");

				st.nextToken();
				String type = st.nextToken();

				if (!map.containsKey(type)) {
					map.put(type, 1);
				}

				else {
					int value = map.get(type);

					map.replace(type, ++value);
				}
			}

			list.clear();
			

			for (Map.Entry<String, Integer> e : map.entrySet()) {
				list.add(e.getValue());
			}

			int sum=1;
			
			
			for(int k=0; k<list.size(); k++)
			{
				sum*=(list.get(k)+1);
			}
			sum-=1;
			
			

			sb.append(sum + "\n");
		}

		System.out.println(sb.toString());

	}


}
