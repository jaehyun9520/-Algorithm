import java.util.HashSet;
import java.util.Set;

public class 영어끝말잇기 {


    public static void main(String[] args) {


        String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
        solution(3,words);
    }

    public static int[] solution(int n, String[] words) {
        int[] answer ={0,0};

        int[] number = new int[n];

        Set<String> set =new HashSet<>();
        for(int i=0; i<words.length; i++) {;

                number[i % n]++;

            if(set.contains(words[i])) {


                answer=new int[] {i%n +1,number[i%n]};
                break;
            }
            set.add(words[i]);
        }





        return answer;
    }




}
