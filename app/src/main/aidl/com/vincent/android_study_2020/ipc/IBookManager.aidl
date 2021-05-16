// IBookManager.aidl
package com.vincent.android_study_2020.ipc;

import com.vincent.android_study_2020.ipc.Book;

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
}