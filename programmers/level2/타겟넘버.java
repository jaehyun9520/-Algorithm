public class 타겟넘버 {


    static int[] operator; // 각 숫자가 가지는 음수 or 양수 (0 은 - , 1은 +)
    static int ans = 0;

    public int solution(int[] numbers, int target) {
        int answer = 0;

        operator = new int[numbers.length];

        dfs(0, numbers, target);
        answer = ans;
        return answer;


    }

    static void dfs(int cnt, int[] numbers, int target) {


        //System.out.println("cnt="+cnt);
        // 다 골랐으면 실제로 계산
        if (cnt == operator.length) {

            int sum = 0;
            for (int i = 0; i < numbers.length; i++) {

                if (operator[i] == 0) {
                    sum -= numbers[i];
                } else {
                    sum += numbers[i];
                }


            }

            if (target == sum) ans++;

            return;
        } else {
            operator[cnt] = 0;
            dfs(cnt + 1, numbers, target);
            operator[cnt] = 1;
            dfs(cnt + 1, numbers, target);
        }
    }

}
