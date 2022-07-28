package a0727;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1111_IQTest {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		
		
		int n = Integer.parseInt(in.readLine());
		int list[] = new int[n + 1];

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}

		if (n <= 2) {
			
			if(n==1)
			{
				System.out.println("A");
			}
			
			else if (list[1] == list[2]) {
				System.out.println(list[1]);
			}

			else {
				System.out.println("A");
			}
		}

		else {
			int a = 0, b = 0;

			int val1 = list[1] - list[2];
			int val2 = list[2] - list[3];

		
			if(val1==0&&val2!=0)
			{
				System.out.println("B");
			}
		
				
			else if (val1==0||val2 % val1 == 0) {
				
				if(val1==0)
				{
					a=0;
				}
				
				else {
					a = val2 / val1;
				}
				
				
				b = list[2] - list[1] * a;

				boolean flag = true;

				for (int i = 3; i <= n - 1; i++) {
					if (list[i] * a + b != list[i + 1]) {
						flag = false;
						break;
					}

				}

				if (flag) {
					System.out.println((list[n] * a + b));
				} else {
					System.out.println("B");
				}

			}

			else {
				System.out.println("B");
			}

		}

	}
}
