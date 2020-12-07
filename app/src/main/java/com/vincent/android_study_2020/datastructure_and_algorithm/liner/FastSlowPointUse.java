package com.vincent.android_study_2020.datastructure_and_algorithm.liner;

/**
 * 快慢指针在链表中的应用
 * 1.获取链表的中间值
 * 2.判断链表是否有环
 * 3.获取链表的环入口
 */
public class FastSlowPointUse {


    public static Node<String> crateLinkList(boolean isCircle) {
        Node<String> first = new Node<>("aa");
        Node<String> second = new Node<>("bb");
        Node<String> third = new Node<>("cc");
        Node<String> fourth = new Node<>("dd");
        Node<String> fifth = new Node<>("ee");
        Node<String> six = new Node<>("ff");

        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = six;
        if (isCircle) {
            six.next = third;
        }
        return first;
    }


    /**
     * 获取链表的中间值
     *
     * @param first
     * @return
     */
    public static Node getLinkListMiddleValue(Node<String> first) {
        Node fast = first;
        Node slow = first;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 判断链表是否有环
     *
     * @param first
     * @return
     */
    public static boolean checkLinkListIsHasCircle(Node<String> first) {
        Node fast = first;
        Node slow = first;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow.equals(fast)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取链表的环入口
     *
     * @param first
     * @return
     */
    public static Node getCircleEnter(Node<String> first) {
        Node fast = first;
        Node slow = first;
        Node temp = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow.equals(fast)) {
                temp = first;
                continue;
            }
            if (temp != null) {
                temp = temp.next;
                if (slow.equals(temp)) {
                    break;
                }
            }
        }
        return temp;
    }


}
