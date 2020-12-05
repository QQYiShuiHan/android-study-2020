package com.vincent.android_study_2020.datastructure_and_algorithm;

import android.util.Log;

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
}
