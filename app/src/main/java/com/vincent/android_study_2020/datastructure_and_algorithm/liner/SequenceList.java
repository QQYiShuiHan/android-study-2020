package com.vincent.android_study_2020.datastructure_and_algorithm.liner;

import androidx.annotation.NonNull;

import com.vincent.android_study_2020.datastructure_and_algorithm.Utils;

import java.util.Arrays;
import java.util.Iterator;

public class SequenceList<T> implements Iterable<T> {

    private T[] mObjects;
    private int mLength;

    public SequenceList(int capactiy) {
        this.mObjects = (T[]) new Object[capactiy];
        this.mLength = 0;
    }

    public void insert(int index, T t) {
        if (mLength + 1 >= mObjects.length) {
            resize(mObjects.length * 2);
        }
        if (Utils.isOutOfIndex(mObjects.length, index)) {
            if (index >= mLength) {
                mObjects[mLength] = t;
                mLength++;
                return;
            }
            for (int i = mLength; i >= index && mLength > 0; i--) {
                mObjects[i] = mObjects[i - 1];
            }
            mObjects[index] = t;
            mLength++;
        }
    }

    public void insert(T t) {
        insert(mLength, t);
    }

    public T get(int index) {
        if (Utils.isOutOfIndex(mLength, index)) {
            return mObjects[index];
        }
        return null;
    }

    public int indexOf(T t) {
        for (int i = 0; i < mObjects.length; i++) {
            if (mObjects[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }

    public T remove(int index) {
        T temp = null;
        if (Utils.isOutOfIndex(mLength, index)) {
            temp = mObjects[index];
            System.arraycopy(mObjects, index + 1, mObjects, index, mLength - 1 - index);
            mLength--;
            return temp;
        }
        return null;
    }

    public void clear() {
        Arrays.fill(mObjects, null);
        mLength = 0;
    }

    public int size() {
        return this.mLength;
    }

    public boolean isEmpty() {
        return mLength == 0;
    }

    public void resize(int newSize) {
        T[] temp = mObjects;
        mObjects = (T[]) new Object[newSize];
        System.arraycopy(temp, 0, mObjects, 0, temp.length);
    }

    @NonNull
    @Override
    public Iterator<T> iterator() {
        return new SIterator();
    }

    private class SIterator implements Iterator<T> {

        private int cusor;

        public SIterator() {
            this.cusor = 0;
        }

        @Override
        public boolean hasNext() {
            return cusor < mLength;
        }

        @Override
        public T next() {
            return mObjects[cusor++];
        }
    }
}
