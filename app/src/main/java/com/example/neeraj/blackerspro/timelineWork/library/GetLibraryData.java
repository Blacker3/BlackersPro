package com.example.neeraj.blackerspro.timelineWork.library;

public class GetLibraryData {
    String bookname, bookauthor;
    int image;

    public GetLibraryData(String bookname, String bookauthor, int image) {
        this.bookname = bookname;
        this.bookauthor = bookauthor;
        this.image = image;
    }

    public String getBookname() {
        return bookname;
    }

    public String getBookauthor() {
        return bookauthor;
    }

    public int getImage() {
        return image;
    }
}

