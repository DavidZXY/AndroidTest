// IBookManager.aidl
package com.test.daggertest;

// Declare any non-default types here with import statements
import com.test.daggertest.Book;
interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
}
