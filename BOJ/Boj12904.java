package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj12904 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		char S[] = in.readLine().toCharArray();
		char T[] = in.readLine().toCharArray();

		int end = T.length - 1;
		for (int i = 1; i <= T.length - S.length; i++) {

			if (T[end] == 'A') {
				end--;
			}

			else if (T[end] == 'B') {
				end--;
				char tmp[] = new char[end+1];

				for (int j = end; j >= 0; j--) {
					tmp[end - j] = T[j];
				}

				for (int j = 0; j <= end; j++) {
					T[j] = tmp[j];
				}

			}

		}
		 boolean flag=true;
		 for(int i=0; i<S.length; i++)
		 {
			if(T[i]!=S[i])
			{
				flag=false;
				break;
			}
			 
		 }
		 
		 if(flag) System.out.println(1);
		 else System.out.println(0);

	}

}
