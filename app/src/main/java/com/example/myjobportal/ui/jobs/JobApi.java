package com.example.myjobportal.ui.jobs;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JobApi {
    @GET("common/jobs")
    Call<JobResponse> getJobs(@Query("page") int page);
}

class JobResponse {
    @SerializedName("results")
    private List<Job> results;

    public List<Job> getResults() { return results; }
}
