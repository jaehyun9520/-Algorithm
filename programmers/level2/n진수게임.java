public class n진수게임 {
    public String solution(int n, int t, int m, int p) {

        int length = t * m;

        StringBuilder sb = new StringBuilder();
        String s = "";
        int number = 0;
        while (sb.length() < length) {

            sb.append(Integer.toString(number++, n));
        }
        
        s = sb.toString().toUpperCase();
        String answer = "";

        for (int i = p; i <= length; i += m) {
            answer += s.charAt(i - 1);
        }

        return answer;
    }
}
