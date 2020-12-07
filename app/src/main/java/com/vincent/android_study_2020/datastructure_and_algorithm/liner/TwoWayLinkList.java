package com.vincent.android_study_2020.datastructure_and_algorithm.liner;


import androidx.annotation.NonNull;

import java.util.Iterator;

public class TwoWayLinkList<T> implements Iterable<T> {

    private Node mHead;
    private Node mLast;
    private int mLength;

    public TwoWayLinkList() {
        this.mHead = new Node(null, null, null);
        this.mLast = null;
        this.mLength = 0;
    }

    public void insert(T t) {
        Node curr = mHead;
        while (curr.next != null) {
            curr = curr.next;
        }
        Node newNode = new Node(t, curr, null);
        curr.next = newNode;
        mLast = newNode;
        mLength++;
    }

    public boolean insert(int index, T t) {
        Node pre = mHead;
        if (index >= 0 && index < mLength) {
            for (int i = 0; i < index; i++) {
                pre = pre.next;
            }
            Node currIndexNode = pre.next;
            Node newNode = new Node(t, pre, currIndexNode);
            pre.next = newNode;
            currIndexNode.pre = newNode;
            mLength++;
        } else if (index >= mLength) {
            while (pre.next != null) {
                pre = pre.next;
            }
            Node newNode = new Node(t, pre, null);
            mLast = newNode;
            pre.next = newNode;
            mLength++;
        } else {
            return false;
        }
        return true;
    }

    public T get(int index) {
        Node result = mHead.next;
        if (index >= 0 && index < mLength) {
            for (int i = 0; i < index; i++) {
                result = result.next;
            }
            return result.item;
        }
        return null;
    }

    public T remove(int index) {
        Node pre = mHead.next;
        if (index >= 0 && index < mLength) {
            for (int i = 0; i < index; i++) {
                pre = pre.next;
            }
            Node curr = pre.next;
            Node nextNode = curr.next;
            pre.next = nextNode;
            nextNode.pre = pre;
            mLength--;
            return curr.item;
        }
        return null;
    }

    public int size() {
        return mLength;
    }

    public void clear() {
        mHead.next = null;
        mHead.pre = null;
        mHead.item = null;
        mLast = null;
        mLength = 0;
    }

    public int indexOf(T t) {
        Node curr = mHead.next;
        for (int i = 0; curr.next != null; i++) {
            if (curr.item.equals(t)) {
                return i;
            }
            curr = curr.next;
        }
        return -1;
    }

    public boolean isEmpty() {
        return mHead.next == null;
    }

    public T getFirst() {
        return mHead.next.item;
    }

    public T getLast() {
        return mLast.item;
    }

    @NonNull
    @Override
    public Iterator<T> iterator() {
        return new LIterator();
    }

    private class LIterator implements Iterator<T> {

        private Node cursor;

        public LIterator() {
            this.cursor = mHead;
        }

        @Override
        public boolean hasNext() {
            return cursor.next != null;
        }

        @Override
        public T next() {
            cursor = cursor.next;
            return cursor.item;
        }
    }

    private class Node {
        Node next;
        Node pre;
        T item;

        public Node(T item, Node pre, Node next) {
            this.next = next;
            this.pre = pre;
            this.item = item;
        }
    }

}
