package com.example.neeraj.blackerspro.timelineWork.library;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.neeraj.blackerspro.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.LibraryAdapterViewHolder> {


    List<GetLibraryData> getLibraryDatas;
    Context context;


    public LibraryAdapter(List<GetLibraryData> getLibraryDatas, Context context) {
        this.getLibraryDatas = getLibraryDatas;
        this.context = context;
    }

    @NonNull
    @Override
    public LibraryAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.librarylayout,viewGroup,false);
        return new LibraryAdapterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull LibraryAdapterViewHolder ViewHolder, int i) {

    GetLibraryData getLibraryData = getLibraryDatas.get(i);
    ViewHolder.bookname.setText(getLibraryData.getBookname());
        ViewHolder.bookauthor.setText(getLibraryData.getBookauthor());
    ViewHolder.image.setImageResource(getLibraryData.getImage());

    }

    @Override
    public int getItemCount() {
        return getLibraryDatas.size();
    }

    public class LibraryAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView bookname,bookauthor;
        ImageView image;
        public LibraryAdapterViewHolder(@NonNull View itemView) {

            super(itemView);

            bookauthor = itemView.findViewById(R.id.bookAuthorTV);
            bookname = itemView.findViewById(R.id.booknameTV);
            image = itemView.findViewById(R.id.BookImage);
        }
    }
}
