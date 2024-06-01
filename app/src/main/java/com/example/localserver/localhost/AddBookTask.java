package com.example.localserver.localhost;

import android.os.AsyncTask;

import com.example.localserver.db.Book;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddBookTask extends AsyncTask<Book, Void, Void> {
    private static final String ADD_BOOK_URL = "http://10.0.2.2/con/addBook.php";
    @Override
    protected Void doInBackground(Book... books) {
        Book book = books[0];
        try {
            URL url = new URL(ADD_BOOK_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            String data = "title=" + book.getTitle() +
                    "&author=" + book.getAuthor() +
                    "&year=" + book.getYear() +
                    "&content=" + book.getContent() +
                    "&genre=" + book.getGenre();

            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(data);
            writer.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            writer.close();
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
