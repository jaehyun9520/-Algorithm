import java.util.HashSet;
import java.util.Set;

public class 스킬트리 {

    public static void main(String[] args) {

    }
    public static int solution(String skill, String[] skill_trees) {

        int answer = 0;
        Set<String> validComb = new HashSet<>();
        Set<Character> usedSkill = new HashSet<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < skill.length(); i++) {
            char c = skill.charAt(i);
            usedSkill.add(c); // 선행 스킬에 사용된 알파벳 추가
            sb.append(c);
            validComb.add(sb.toString()); // 입력값으로 만들 수 있는 유효한 조합
        }

        for (int i = 0; i < skill_trees.length; i++) {

            String skillTree = skill_trees[i];
            sb = new StringBuilder();
            for (int j = 0; j < skillTree.length(); j++) {
                char c = skillTree.charAt(j);
                if (usedSkill.contains(c)) {
                    sb.append(c);
                }
            }


            if (validComb.contains(sb.toString()) || sb.toString().length() == 0) {
                answer++;
            }
        }

        return answer;
    }
}
