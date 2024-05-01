import java.util.Arrays;

public class Hindex {

    public int solution(int[] citations) {
        int answer = 0;

        Arrays.sort(citations);
        // 300번 인용된 논문은 300편이 있어야한다.
        int cnt=citations.length;
        for(int i=0; i<citations.length; i++) {

            if(citations[i]>=cnt-i) {

                answer=cnt-i;
                break;
            }
        }
        return answer;
    }


}
