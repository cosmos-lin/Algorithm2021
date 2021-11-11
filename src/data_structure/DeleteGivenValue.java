package data_structure;

/**
 * 在链表中删除指定值的所有节点
 */
public class DeleteGivenValue {
    
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node removeValue(Node head, int num) {

        // head 来到第一个不需要删除的位置
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }

        // 删除位置匹配的节点
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }

        return head;
    }
}
