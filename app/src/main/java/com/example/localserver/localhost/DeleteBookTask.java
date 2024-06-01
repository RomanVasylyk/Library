package com.example.localserver.localhost;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
public class DeleteBookTask extends AsyncTask<Integer, Void, Void> {
    private static final String DELETE_BOOK_URL = "http://10.0.2.2/con/deleteBook.php";
    @Override
    protected Void doInBackground(Integer... ids) {
        int id = ids[0];
        try {
            URL url = new URL(DELETE_BOOK_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            String data = "id=" + id;
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
