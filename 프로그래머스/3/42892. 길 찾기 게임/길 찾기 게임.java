import java.util.*;

class Solution {
    
    class Node {
        int x;
        int y;
        int num;
        Node left;
        Node right;

        Node(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }
    
    public int[][] solution(int[][] nodeinfo) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < nodeinfo.length; i++) {
            nodes.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1));
        }

        nodes.sort(Comparator.comparingInt((Node n) -> n.y).reversed()
                      .thenComparingInt(n -> n.x)
        );
        
        Node root = nodes.get(0);

        for (int i = 1; i < nodes.size(); i++) {
            insert(root, nodes.get(i));
        }

        List<Integer> preorder = new ArrayList<>();
        List<Integer> postorder = new ArrayList<>();

        preorder(root, preorder);
        postorder(root, postorder);

        return new int[][] {
            preorder.stream().mapToInt(Integer::intValue).toArray(),
            postorder.stream().mapToInt(Integer::intValue).toArray()
        };
    }
    private void insert(Node parent, Node child) {
        if (child.x < parent.x) {
            if (parent.left == null) {
                parent.left = child;
            } else {
                insert(parent.left, child);
            }
        } else {
            if (parent.right == null) {
                parent.right = child;
            } else {
                insert(parent.right, child);
            }
        }
    }

    private void preorder(Node node, List<Integer> result) {
        if (node == null) return;

        result.add(node.num);
        preorder(node.left, result);
        preorder(node.right, result);
    }

    private void postorder(Node node, List<Integer> result) {
        if (node == null) return;

        postorder(node.left, result);
        postorder(node.right, result);
        result.add(node.num);
    }
}