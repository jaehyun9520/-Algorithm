package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj17609 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= T; t++) {
			char input[] = in.readLine().toCharArray();

			int size = input.length;

			int flag = 0;
			int point1 = 0, point2 = size - 1;

			while (point1 <= point2) {

				if (input[point1] == input[point2]) {
					point1++;
					point2--;
				}

				else {

					// 왼쪽을 하나 지우면 같이 지는 경우
					if (input[point1 + 1] == input[point2]) {

						int tmp1 = point1 + 1;
						int tmp2 = point2;
						flag = 1;

						while (tmp1 <= tmp2) {
							if (input[tmp1] == input[tmp2]) {
								
								tmp1++;
								tmp2--;
							}

							else {
								
								flag = 2;
								break;
							}

						}

					}
					
					
					if(input[point1]==input[point2-1]&&flag!=1)
					{
						
						int tmp1=point1;
						int tmp2=point2-1;
						flag=1;
						
						while (tmp1 <= tmp2) {
							if (input[tmp1] == input[tmp2]) {
								tmp1++;
								tmp2--;
							}

							else {
								
								flag = 2;
								break;
							}

						}
						
					}
					
					
					if(flag==0)
					{
						flag=2;
					}
					

					break;
				}

			}

			sb.append(flag + "\n");

		}
		System.out.println(sb.toString());

	}

}
