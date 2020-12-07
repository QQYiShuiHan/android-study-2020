package com.vincent.android_study_2020.datastructure_and_algorithm;

import com.vincent.android_study_2020.datastructure_and_algorithm.liner.FastSlowPointUse;
import com.vincent.android_study_2020.datastructure_and_algorithm.liner.LinkList;
import com.vincent.android_study_2020.datastructure_and_algorithm.liner.Node;
import com.vincent.android_study_2020.datastructure_and_algorithm.liner.SequenceList;


import java.util.Iterator;

public class DataStructureTest {

    public static void testSequenceList() {
        SequenceList<Integer> sequenceList = new SequenceList<>(4);
        sequenceList.insert(0);
        sequenceList.insert(3, 1);
        Iterator<Integer> iterator = sequenceList.iterator();
        while (iterator.hasNext()) {
            System.out.println("SequenceList = " + iterator.next());
        }
        System.out.println("SequenceList size = " + sequenceList.size());
        sequenceList.insert(2);
        sequenceList.insert(3);
        sequenceList.insert(4);
        sequenceList.insert(5);
        Iterator<Integer> iterator1 = sequenceList.iterator();
        while (iterator1.hasNext()) {
            System.out.println("SequenceList = " + iterator1.next());
        }
        System.out.println("SequenceList size = " + sequenceList.size());
        Integer temp = sequenceList.remove(1);
        System.out.println("SequenceList size = " + sequenceList.size() + " remove = " + temp);
        Iterator<Integer> iterator2 = sequenceList.iterator();
        while (iterator2.hasNext()) {
            System.out.println("SequenceList = " + iterator2.next());
        }
    }


    public static void testLinkList() {
        LinkList<String> linkList = new LinkList<>();
        linkList.insert("张三");
        linkList.insert(1, "李四");
        linkList.insert("赵六");
        linkList.insert(2, "王五");
        linkList.reverse();
        Iterator<String> iterator = linkList.iterator();
        while (iterator.hasNext()) {
            System.out.println("LinkList = " + iterator.next());
        }
        System.out.println("LinkList size = " + linkList.size());
        System.out.println("LinkList indexOf = " + linkList.indexOf("张三"));
        System.out.println("LinkList indexOf = " + linkList.indexOf("王五"));
        linkList.remove(1);
        Iterator<String> iterator1 = linkList.iterator();
        while (iterator1.hasNext()) {
            System.out.println("LinkList = " + iterator1.next());
        }
        System.out.println("LinkList size = " + linkList.size());
        linkList.clear();
        System.out.println("LinkList size = " + linkList.size());
    }


    public static void testFastSlowPointUse() {
        Node<String> firstHasCircle = FastSlowPointUse.crateLinkList(true);
        Node<String> firstNotCircle = FastSlowPointUse.crateLinkList(false);
        Node<String> middleNode = FastSlowPointUse.getLinkListMiddleValue(firstNotCircle);
        System.out.println("testFastSlowUse middle  = " + middleNode.value);
        boolean isCircle = FastSlowPointUse.checkLinkListIsHasCircle(firstHasCircle);
        System.out.println("testFastSlowUse is circle = " + isCircle);
        boolean isCircle1 = FastSlowPointUse.checkLinkListIsHasCircle(firstNotCircle);
        System.out.println("testFastSlowUse is circle = " + isCircle1);
        Node<String> circleEnter = FastSlowPointUse.getCircleEnter(firstHasCircle);
        System.out.println("testFastSlowUse circle enter = " + circleEnter.value);


    }
}
