import java.util.*;

public class 길찾기게임 {

    static int cnt = 0;

    static class Node implements Comparable<Node> {

        int x, y, number;
        Node leftNode;
        Node rightNode;

        Node(int x, int y, int number, Node leftNode, Node rightNode) {
            this.x = x;
            this.y = y;
            this.number = number;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }

        @Override
        public int compareTo(Node o) {
            return o.y - this.y;
        }

    }
    public static int[][] solution(int[][] nodeinfo) {
        int[][] answer = {};

        List<Node> list = new ArrayList<>();

        for (int i = 0; i < nodeinfo.length; i++) {
            list.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1, null, null));
        }

        Collections.sort(list);

        answer = new int[2][list.size()];
        Node rootNode = list.get(0);

        // 이진트리 생성
        for (int i = 1; i < list.size(); i++) {
            setNodePosition(rootNode, list.get(i));
        }

        cnt = 0;
        // 전위순회, 후위순회 출력
        preOrder(rootNode, answer);
        cnt = 0;
        postOrder(rootNode, answer);
        return answer;
    }

    private static void postOrder(Node rootNode, int[][] answer) {
        if (rootNode.leftNode != null) {
            postOrder(rootNode.leftNode, answer);
        }
        if (rootNode.rightNode != null) {
            postOrder(rootNode.rightNode, answer);
        }
        answer[1][cnt] = rootNode.number;
        cnt++;
    }

    private static void preOrder(Node rootNode, int[][] answer) {

        answer[0][cnt] = rootNode.number;
        cnt++;

        if (rootNode.leftNode != null) {
            preOrder(rootNode.leftNode, answer);
        }
        if (rootNode.rightNode != null) {
            preOrder(rootNode.rightNode, answer);
        }
    }

    private static void setNodePosition(Node crruentNode, Node node) {

        int x = crruentNode.x;

        // 오른쪽 서브트리에 해당
        if (x < node.x) {

            // 오른쪽 서브트리가 비어있다면 해당 노드가 위치
            if (crruentNode.rightNode == null) {
                crruentNode.rightNode = node;
                return;
            }
            // 오른쪽 서브트리에 노드가 있다면 더 진행
            else {
                setNodePosition(crruentNode.rightNode, node);
            }
        }

        // 왼쪽 서브트리에 해당
        else {
            // 왼쪽 서브트리가 비어있다면 해당 노드가 위치
            if (crruentNode.leftNode == null) {
                crruentNode.leftNode = node;
                return;
            }
            // 오른쪽 서브트리에 노드가 있다면 더 진행
            else {
                setNodePosition(crruentNode.leftNode, node);
            }
        }


    }
}