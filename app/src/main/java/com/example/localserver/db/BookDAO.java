package com.example.localserver.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.localserver.localhost.AddBookTask;
import com.example.localserver.localhost.DeleteBookTask;
import com.example.localserver.localhost.UpdateBookTask;

import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public BookDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    public void addBook(Book book) {
        ContentValues values = new ContentValues();
        values.put("title", book.getTitle());
        values.put("author", book.getAuthor());
        values.put("year", book.getYear());
        values.put("content", book.getContent());
        values.put("genre", book.getGenre());
        db.insert("books", null, values);

        new AddBookTask().execute(book);
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        Cursor cursor = db.query("books", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String author = cursor.getString(cursor.getColumnIndex("author"));
            int year = cursor.getInt(cursor.getColumnIndex("year"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String genre = cursor.getString(cursor.getColumnIndex("genre"));
            books.add(new Book(id, title, author, year, content, genre));
        }
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        dbHelper.resetDatabase(db);
        cursor.close();
        return books;
    }

    public boolean deleteBook(int id) {
        new DeleteBookTask().execute(id);
        return db.delete("books", "id=?", new String[]{String.valueOf(id)}) > 0;
    }

    public boolean updateBook(Book book) {
        ContentValues values = new ContentValues();
        values.put("title", book.getTitle());
        values.put("author", book.getAuthor());
        values.put("year", book.getYear());
        values.put("content", book.getContent());
        values.put("genre", book.getGenre());
        new UpdateBookTask().execute(book);
        return db.update("books", values, "id=?", new String[]{String.valueOf(book.getId())}) > 0;
    }
}
