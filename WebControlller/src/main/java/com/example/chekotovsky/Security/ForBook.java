package com.example.chekotovsky.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.print.Book;

@Component
public class ForBook {
    private Book book;
    @Autowired
    public ForBook(Book book) {
        this.book = book;
    }
}
