package com.example.localserver;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.localserver.db.Book;
import com.example.localserver.db.BookDAO;
import com.example.localserver.rotation.LandscapeBookAdapter;
import com.example.localserver.rotation.PortraitBookAdapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BookDAO bookDAO;
    private ListView listView;
    private ArrayAdapter<Book> adapter;
    private List<Book> books;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        addButton = findViewById(R.id.addButton);

        bookDAO = new BookDAO(this);
        bookDAO.open();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
                startActivity(intent);
            }
        });

        updateBookList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateBookList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bookDAO.close();
    }

    private void updateBookList() {
        books = bookDAO.getAllBooks();
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return b1.getTitle().compareTo(b2.getTitle());
            }
        });

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            adapter = new LandscapeBookAdapter(this, books);
        } else {
            adapter = new PortraitBookAdapter(this, books);
        }

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book selectedBook = books.get(position);
                Intent intent = new Intent(MainActivity.this, BookDetailActivity.class);
                intent.putExtra("id", selectedBook.getId());
                intent.putExtra("title", selectedBook.getTitle());
                intent.putExtra("author", selectedBook.getAuthor());
                intent.putExtra("year", selectedBook.getYear());
                intent.putExtra("content", selectedBook.getContent());
                intent.putExtra("genre", selectedBook.getGenre());
                startActivity(intent);
            }
        });
    }
}
