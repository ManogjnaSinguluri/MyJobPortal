package com.example.myjobportal.ui.bookmarks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myjobportal.R;
import com.example.myjobportal.ui.jobs.JobEntity;

import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder> {
    private List<JobEntity> bookmarkList;

    public BookmarkAdapter(List<JobEntity> bookmarkList) {
        this.bookmarkList = bookmarkList;
    }

    @NonNull
    @Override
    public BookmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_card, parent, false);
        return new BookmarkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkViewHolder holder, int position) {
        JobEntity job = bookmarkList.get(position);
        holder.title.setText(job.title);
        holder.location.setText(job.location);
        holder.salary.setText(job.salary);
        holder.phone.setText(job.phone);
    }

    @Override
    public int getItemCount() {
        return bookmarkList.size();
    }

    static class BookmarkViewHolder extends RecyclerView.ViewHolder {
        TextView title, location, salary, phone;

        BookmarkViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            location = itemView.findViewById(R.id.location);
            salary = itemView.findViewById(R.id.salary);
            phone = itemView.findViewById(R.id.phone);
        }
    }
}