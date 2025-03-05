public class 두개이하로다른비트 {

    public static long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        for (int i = 0; i < numbers.length; i++) {

            String s = "0" + Long.toBinaryString(numbers[i]);
            StringBuilder sb = new StringBuilder(s);
            int loc = 0;
            for (int j = s.length() - 1; j >= 1; j--) {

                char val = s.charAt(j);
                char nextVal = s.charAt(j - 1);

                if (val == '0') {
                    loc = j;
                    sb.setCharAt(j, '1');
                    break;
                } else if (val == '1' && nextVal == '0') {
                    loc = j;
                    sb.setCharAt(j, '0');
                    sb.setCharAt(j - 1, '1');
                    break;
                }
            }

            answer[i] = Long.parseLong(sb.toString(), 2);

        }
        return answer;
    }
}
