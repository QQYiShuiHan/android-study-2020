package com.vincent.android_study_2020.datastructure_and_algorithm.liner;


import androidx.annotation.NonNull;

import java.util.Iterator;

public class LinkList<T> implements Iterable<T> {

    private Node mHead;
    private int mLength;

    public LinkList() {
        this.mHead = new Node(null, null);
        this.mLength = 0;
    }

    public void insert(T t) {
        Node curr = mHead;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = new Node(t, null);
        mLength++;
    }

    public boolean insert(int index, T t) {
        Node pre = mHead;
        if (index >= 0 && index < mLength) {
            for (int i = 0; i < index; i++) {
                pre = pre.next;
            }
            Node currIndexNode = pre.next;
            pre.next = new Node(t, currIndexNode);
            mLength++;
        } else if (index >= mLength) {
            while (pre.next != null) {
                pre = pre.next;
            }
            pre.next = new Node(t, null);
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
        Node pre = mHead;
        if (index >= 0 && index < mLength) {
            for (int i = 0; i < index; i++) {
                pre = pre.next;
            }
            Node curr = pre.next;
            pre.next = curr.next;
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

    // 单向链表的反转
    public void reverse() {
        if (isEmpty()) {
            return;
        }
        reverse(mHead.next);
    }

    private Node reverse(Node curr) {
        if (curr.next == null) {
            mHead.next = curr;
            return curr;
        }
        Node pre = reverse(curr.next);
        pre.next = curr;
        curr.next = null;
        return curr;
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
        T item;

        public Node(T item, Node next) {
            this.next = next;
            this.item = item;
        }
    }

}
