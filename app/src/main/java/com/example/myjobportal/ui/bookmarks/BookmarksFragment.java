package com.example.myjobportal.ui.bookmarks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myjobportal.R;
import com.example.myjobportal.ui.AppDatabase;
import com.example.myjobportal.ui.jobs.JobEntity;

import java.util.ArrayList;
import java.util.List;

public class BookmarksFragment extends Fragment {
    private RecyclerView recyclerView;
    private BookmarkAdapter adapter;
    private List<JobEntity> bookmarkList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookmarks, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BookmarkAdapter(bookmarkList);
        recyclerView.setAdapter(adapter);

        loadBookmarks();
        return view;
    }

    private void loadBookmarks() {
        new Thread(() -> {
            bookmarkList.clear();
            bookmarkList.addAll(AppDatabase.getInstance(getContext()).jobDao().getAllBookmarks());
            getActivity().runOnUiThread(() -> adapter.notifyDataSetChanged());
        }).start();
    }
}
