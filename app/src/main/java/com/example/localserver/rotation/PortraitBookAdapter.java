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

public class PortraitBookAdapter extends ArrayAdapter<Book> {
    private Context context;
    private List<Book> books;

    public PortraitBookAdapter(Context context, List<Book> books) {
        super(context, 0, books);
        this.context = context;
        this.books = books;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.book_list_item_portrait, parent, false);
        }

        Book book = books.get(position);

        TextView titleTextView = convertView.findViewById(R.id.titleTextView);
        titleTextView.setText(book.getTitle());

        return convertView;
    }
}
