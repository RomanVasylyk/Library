package com.example.localserver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.localserver.db.Book;
import com.example.localserver.db.BookDAO;

public class BookDetailActivity extends AppCompatActivity {
    private EditText titleEditText;
    private EditText authorEditText;
    private EditText yearEditText;
    private EditText contentEditText;
    private Spinner genreSpinner;
    private Button deleteButton;
    private Button updateButton;
    private int bookId;
    private BookDAO bookDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        titleEditText = findViewById(R.id.titleEditText);
        authorEditText = findViewById(R.id.authorEditText);
        yearEditText = findViewById(R.id.yearEditText);
        contentEditText = findViewById(R.id.contentEditText);
        genreSpinner = findViewById(R.id.genreSpinner);
        deleteButton = findViewById(R.id.deleteButton);
        updateButton = findViewById(R.id.updateButton);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.genres_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genreSpinner.setAdapter(adapter);

        Intent intent = getIntent();
        bookId = intent.getIntExtra("id", 0);
        titleEditText.setText(intent.getStringExtra("title"));
        authorEditText.setText(intent.getStringExtra("author"));
        yearEditText.setText(String.valueOf(intent.getIntExtra("year", 0)));
        contentEditText.setText(intent.getStringExtra("content"));

        String genre = intent.getStringExtra("genre");
        if (genre != null) {
            int spinnerPosition = adapter.getPosition(genre);
            genreSpinner.setSelection(spinnerPosition);
        }

        bookDAO = new BookDAO(this);
        bookDAO.open();

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean deleted = bookDAO.deleteBook(bookId);
                if (deleted) {
                    Toast.makeText(BookDetailActivity.this, "Book deleted", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(BookDetailActivity.this, "Failed to delete book", Toast.LENGTH_SHORT).show();
                }
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String author = authorEditText.getText().toString();
                int year = Integer.parseInt(yearEditText.getText().toString());
                String content = contentEditText.getText().toString();
                String genre = genreSpinner.getSelectedItem().toString();

                Book updatedBook = new Book(bookId, title, author, year, content, genre);
                boolean updated = bookDAO.updateBook(updatedBook);
                if (updated) {
                    Toast.makeText(BookDetailActivity.this, "Book updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(BookDetailActivity.this, "Failed to update book", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bookDAO.close();
    }
}
