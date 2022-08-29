package study52;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj20302_민트초코 {

	static int n;
	static boolean pnum[] = new boolean[100001]; //true면 소수
	static ArrayList<Integer> list = new ArrayList<>(); // 소수만 들어있는 list

	static int numCnt[] = new int[100001]; 

	public static void main(String[] args) throws IOException {

		findPnum(); //먼저 10만까지 소수를 다 찾고 pnum에 true표시하고 list에 추가

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		int sNum = Math.abs(Integer.parseInt(st.nextToken()));

		
		numberCount(0,sNum);

		int num = 0;
		int type = 0;
		for (int i = 2; i < 2 * n; i++) {
			if (i % 2 == 1) {
				num = Integer.parseInt(st.nextToken());
				numberCount(type,Math.abs(num));
			} else {
				String operator = st.nextToken();
				if (operator.equals("*")) {
					type = 0;
				} else {
					type = 1;
				}
			}

		}
		
		
		
		boolean result = true;

		for (int i = 0; i < list.size(); i++) {
			if (numCnt[list.get(i)] < 0) {
				result = false;
				break;
			}
		}
		
		if(numCnt[0]>0) result=true;
		
		
		if(result)
		{
			System.out.println("mint chocolate");
		}
		else {
			System.out.println("toothpaste");
		}

	}

	
	static void numberCount(int type, int val)
	{
		if(pnum[val])  //소수이면 더 이상 진행하지 않고 중단
		{
			if(type==0)
			numCnt[val]++;
			
			else 
			numCnt[val]--;
			return;
		}
		
		
		while (val != 1) { //소수가 아니면 나누면서 기록
			if (val == 0) {
				numCnt[0]++;
				break;
			}

			for (int j = 0; j < list.get(j); j++) {

				if (val % list.get(j) == 0) {
					val = val / list.get(j);

					if (type == 0) {
						numCnt[list.get(j)]++;
					} else {
						numCnt[list.get(j)]--;
					}
					break;
				}
			}
		}
		
		
		
		
		
	}
	
	
	static void findPnum() { 
		Arrays.fill(pnum, Boolean.TRUE);

		for (int i = 2; i <= 100000; i++) {
			if (pnum[i]) {
				list.add(i);

				int k = 3;
				for (int j = i * 2; j <= 100000; j = i * k) {
					k++;
					pnum[j] = false;
				}

			}

		}
	}
	
}
