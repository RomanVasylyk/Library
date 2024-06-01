package com.example.localserver;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.localserver.db.Book;
import com.example.localserver.db.BookDAO;

public class AddBookActivity extends AppCompatActivity {
    private EditText titleEditText;
    private EditText authorEditText;
    private EditText yearEditText;
    private EditText contentEditText;
    private Spinner genreSpinner;
    private Button saveButton;
    private BookDAO bookDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        titleEditText = findViewById(R.id.titleEditText);
        authorEditText = findViewById(R.id.authorEditText);
        yearEditText = findViewById(R.id.yearEditText);
        contentEditText = findViewById(R.id.contentEditText);
        genreSpinner = findViewById(R.id.genreSpinner);
        saveButton = findViewById(R.id.saveButton);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.genres_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genreSpinner.setAdapter(adapter);

        bookDAO = new BookDAO(this);
        bookDAO.open();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String author = authorEditText.getText().toString();
                int year = Integer.parseInt(yearEditText.getText().toString());
                String content = contentEditText.getText().toString();
                String genre = genreSpinner.getSelectedItem().toString();

                Book book = new Book(0, title, author, year, content, genre);
                bookDAO.addBook(book);
                bookDAO.close();
                Toast.makeText(AddBookActivity.this, "Book added", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
