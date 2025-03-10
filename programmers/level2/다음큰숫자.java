public class 다음큰숫자 {


    public static int solution(int n) {
        int answer = 0;

        int cnt = 0;
        boolean flag = true;
        int len = 0;
        while (answer <= n) {
            int val = (1 << len);

            // 해당 위치에 1이 존재한다면?

            if (flag && (n & val) > 0) {
                // 다음칸이 0 이라면?
                if (((1 << (len + 1)) & n) == 0) {
                    answer = answer | (1 << len + 1);
                    flag = false;
                } else {
                    answer = answer | (1 << cnt);
                    cnt++;
                }
            } else if (!flag && (n & val) > 0) {
                answer = answer | (1 << len);
            }

            len++;
        }

        return answer;
    }
}
