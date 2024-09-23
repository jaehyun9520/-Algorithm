import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 수식복원하기 {

    public static String[] solution(String[] expressions) {
        String[] answer = {};

        boolean[] validNumber = new boolean[10]; // 2~9 만 사용
        Arrays.fill(validNumber, true);

        // 복원해야 하는 수식 리스트
        List<String> list = new ArrayList<>();

        // 가능한 진법들 확인
        for (int i = 0; i < expressions.length; i++) {

            // 숫자의 위치는 0번째 2번째 4번째 ,  연산자의 위치는 1번째
            String[] expression = expressions[i].split(" ");

            // 결과값이 나와있다면? 어떤 진법으로 해당 결과가 가능한지 확인

            // 2~9 진법 중 유효한 진법 확인
            for (int j = 2; j <= 9; j++) {
                if (validNumber[j]) {
                    int result = calculation(expression[0], expression[2], expression[1], j);

                    if ((expression[4].equals("X") && result == -1) || (!expression[4].equals("X")
                            && result != Integer.parseInt(expression[4]))) {
                        validNumber[j] = false;
                    }
                }
            }
            if (expression[4].equals("X")) {
                list.add(expressions[i]);
            }
        }

        answer = new String[list.size()];
        // X를 변환하기
        for (int i = 0; i < list.size(); i++) {
            String[] expression = list.get(i).split(" ");

            int result = -1;
            boolean possible = true;
            for (int j = 2; j <= 9; j++) {

                if (validNumber[j]) {
                    int val = calculation(expression[0], expression[2], expression[1], j);

                    if (val == -1) {
                        validNumber[j] = false;
                    } else if (result == -1) {
                        result = val;
                    } else if (result != val) {
                        possible = false;
                        break;
                    }


                }

            }

            if (possible) {
                answer[i] = list.get(i).replace("X", "" + result);
            } else {
                answer[i] = list.get(i).replace("X", "?");
            }


        }

        return answer;
    }

    private static int calculation(String first, String second, String operation, int j) {

        int firstVal = 0;
        int secondVal = 0;
        int result = 0;

        //  10진법으로 변환 및 결과 계산
        for (int i = first.length() - 1; i >= 0; i--) {

            int number = first.charAt(i) - 48;

            if (number >= j) {
                return -1;
            }
            firstVal += ((Math.pow(j, first.length() - i - 1)) * number);
        }
        for (int i = second.length() - 1; i >= 0; i--) {
            int number = second.charAt(i) - 48;
            if (number >= j) {
                return -1;
            }
            secondVal += ((Math.pow(j, second.length() - i - 1)) * number);
        }
        if (operation.equals("+")) {
            result = firstVal + secondVal;
        } else {
            result = firstVal - secondVal;
        }

        //  결과를 다시 j진법으로 변환
        int tmp = 0;
        for (int i = 2; i >= 0; i--) {
            tmp *= 10;
            tmp += (result / Math.pow(j, i));
            result %= Math.pow(j, i);
        }

        return tmp;
    }
}
