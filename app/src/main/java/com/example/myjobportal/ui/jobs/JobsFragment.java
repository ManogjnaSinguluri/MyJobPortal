package com.example.myjobportal.ui.jobs;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myjobportal.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JobsFragment extends Fragment {
    private RecyclerView recyclerView;
    private JobAdapter adapter;
    private List<Job> jobList = new ArrayList<>();
    private ProgressBar progressBar;
    private TextView errorText;
    private int currentPage = 1;
    private boolean isLoading = false;

    // Constant for Intent extra key
    public static final String EXTRA_JOB = "job";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jobs, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        progressBar = view.findViewById(R.id.progress_bar);
        errorText = view.findViewById(R.id.error_text);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new JobAdapter(jobList, job -> {
            if (job != null && getContext() != null) {
                Intent intent = new Intent(getContext(), JobDetailActivity.class);
                intent.putExtra(EXTRA_JOB, job); // Use the constant key
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItemCount = layoutManager.getItemCount();
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + 5)) {
                    loadMoreJobs();
                }
            }
        });

        loadJobs(currentPage);
        return view;
    }

    private void loadJobs(int page) {
        showLoading();
        isLoading = true;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://testapi.getlokalapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JobApi api = retrofit.create(JobApi.class);
        api.getJobs(page).enqueue(new Callback<JobResponse>() {
            @Override
            public void onResponse(Call<JobResponse> call, Response<JobResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Job> newJobs = response.body().getResults();
                    if (newJobs != null && !newJobs.isEmpty()) {
                        jobList.addAll(newJobs);
                        adapter.notifyDataSetChanged();
                        showData();
                        currentPage++;
                    } else {
                        showEmpty();
                    }
                } else {
                    showError("Failed to load jobs");
                }
                isLoading = false;
            }

            @Override
            public void onFailure(Call<JobResponse> call, Throwable t) {
                showError("Error: " + t.getMessage());
                isLoading = false;
            }
        });
    }

    private void loadMoreJobs() {
        loadJobs(currentPage);
    }

    private void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        errorText.setVisibility(View.GONE);
    }

    private void showData() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        errorText.setVisibility(View.GONE);
    }

    private void showError(String message) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        errorText.setVisibility(View.VISIBLE);
        errorText.setText(message);
    }

    private void showEmpty() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        errorText.setVisibility(View.VISIBLE);
        errorText.setText("No jobs available");
    }
}