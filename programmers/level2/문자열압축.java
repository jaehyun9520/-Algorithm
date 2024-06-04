package programmers.level2;


import java.io.IOException;

public class 문자열압축 {

    public static void main(String[] args) throws IOException {

        System.out.println(solution("aabbaccc"));

    }

    public static int solution(String s) {

        int answer = Integer.MAX_VALUE;


        // 문자열 자르는 단위  1개 ~ 전체 문자열의 길이/2개까지
        for (int i = 1; i <= (s.length() / 2 == 0 ? 1 : s.length() / 2); i++) {

            int sum = 0;
            String pre = "";
            int cnt = 1;

            // 전체 문자열을  i만큼 잘라서 확인
            for (int j = 0; j < s.length(); j += i) {

                //i 만큼 자를수 있든 없든 그 부분문자열을 가져오고 현재 부분문자열의 마지막 부분을 가져온다  = 이게 s.length()-1 이라면 더 이상 볼게 없는거
                int end = j + i - 1;
                if (end > s.length() - 1) {
                    end = s.length() - 1;
                }

                String subString = s.substring(j, end + 1);

                // 이전의 문자와 비교해서 같으면 다음으로 진행
                if (pre.equals(subString)) {
                    cnt++;
                }
                // 다르면 초기화
                else {
                    // 1이면 문자열만  2이상이면 숫자를 앞에 붙여준다
                    if (cnt == 1) {
                        sum += (pre.length());
                    } else {
                        sum += (cnt + "" + pre).length();
                    }

                    pre = subString;
                    cnt = 1;
                }


                // 마지막으로 길이가 완전 끝에 도달했다면? 더해준다
                if (end == s.length() - 1) {
                    if (cnt == 1) {
                        sum += (pre.length());
                    } else {
                        sum += (cnt + "" + pre).length();
                    }
                }

            }


            answer = Integer.min(answer, sum);
        }

        return answer;
    }
}
