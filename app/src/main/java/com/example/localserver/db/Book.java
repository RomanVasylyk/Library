package com.example.localserver.db;

public class Book {
    private int id;
    private String title;
    private String author;
    private int year;
    private String content;
    private String genre;

    public Book(int id, String title, String author, int year, String content, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.content = content;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
