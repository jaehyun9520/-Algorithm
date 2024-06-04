package programmers.level2;

import java.util.*;


public class 과제진행하기 {

    static class Task implements Comparable<Task> {
        int startTime;
        int playTime;
        String name;

        Task(int startTime, int playTime, String name) {
            this.startTime = startTime; // 분으로 치환한다
            this.playTime = playTime;
            this.name = name;
        }

        @Override
        public int compareTo(Task o) {

            return this.startTime - o.startTime;
        }
    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(solution(new String[][]{{"aaa", "12:00", "20"}, {"bbb", "12:10", "30"}, {"ccc", "12:40", "10"}})));

    }


    public static String[] solution(String[][] plans) {

        List<Task> list = new ArrayList<>();
        String[] answer = new String[plans.length];
        List<String> ans = new ArrayList<>();
        LinkedList<int[]> unfinishedTask = new LinkedList<>(); // 작업 번호와 남은 시간

        // 이름, 시작시간,  종료시간
        for (int i = 0; i < plans.length; i++) {

            StringTokenizer st = new StringTokenizer(plans[i][1], ":");
            int startTime = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
            list.add(new Task(startTime, Integer.parseInt(plans[i][2]), plans[i][0]));
        }
        // 오름차순 정렬
        Collections.sort(list);


        for (int i = 0; i < list.size() - 1; i++) {

            Task now = list.get(i);
            // 다음 과제의 시작 시간과 현재 과제의 종료 시간이 겹치는지 확인
            int endTime = now.startTime + now.playTime;
            int nextStartTime = list.get(i + 1).startTime;


            // 기존 과제가 다 끝날 수 없다면 멈춰둔 과제에 추가
            if (endTime > nextStartTime) {
                unfinishedTask.add(new int[]{i, endTime - nextStartTime}); // 현재 task의 번호와 남은시간
            }


            // 기존 과제도 다 끝내고 시간도 남는다면 멈춰둔 과제를 가장 최신꺼부터 하나씩 처리
            else if (endTime <= nextStartTime) {

                ans.add(now.name);

                int remainingTime = nextStartTime - endTime;

                while (remainingTime > 0 && unfinishedTask.size() != 0) {

                    int[] pre = unfinishedTask.getLast();

                    if (pre[1] <= remainingTime) {
                        remainingTime -= pre[1];
                        ans.add(list.get(pre[0]).name);
                        unfinishedTask.removeLast();
                    } else {
                        pre[1] = pre[1] - remainingTime;
                        break;
                    }


                }

            }

        }


        ans.add(list.get(list.size() - 1).name);
        // 다음 과제가 없는경우
        while (unfinishedTask.size() != 0) {
            int[] pre = unfinishedTask.getLast();
            ans.add(list.get(pre[0]).name);
            unfinishedTask.removeLast();
        }


        return ans.toArray(String[]::new);
    }
}
