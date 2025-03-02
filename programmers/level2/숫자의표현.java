public class 숫자의표현 {

    public static void main(String[] args) {

        int solution = solution(15);
        System.out.println(solution);

    }

    public static int solution(int n) {

        int answer = 0;

        int start=1;
        int end=1;

        int sum=1;

        while(start<=end&&end<=10000) {
            if(sum<n) {
                end++;
                sum+=end;
            }

            else if(sum>n) {
                sum-=start;
                start++;
            }

            else if(sum==n) {
                sum-=start;
                start++;
                answer++;
            }
        }
        return answer;
    }
}
