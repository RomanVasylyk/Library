package com.example.localserver.rotation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.localserver.R;
import com.example.localserver.db.Book;

import java.util.List;

public class LandscapeBookAdapter extends ArrayAdapter<Book> {
    private Context context;
    private List<Book> books;

    public LandscapeBookAdapter(Context context, List<Book> books) {
        super(context, 0, books);
        this.context = context;
        this.books = books;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.book_list_item_landscape, parent, false);
        }

        Book book = books.get(position);

        TextView titleTextView = convertView.findViewById(R.id.titleTextView);
        TextView authorTextView = convertView.findViewById(R.id.authorTextView);
        TextView yearTextView = convertView.findViewById(R.id.yearTextView);
        TextView genreTextView = convertView.findViewById(R.id.genreTextView);

        titleTextView.setText("Title: " + book.getTitle());
        authorTextView.setText("Author: " + book.getAuthor());
        yearTextView.setText("Year: " + book.getYear());
        genreTextView.setText("Genre: " + book.getGenre());

        return convertView;
    }
}
