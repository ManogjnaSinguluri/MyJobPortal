package com.example.myjobportal.ui.jobs;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myjobportal.R;
import com.example.myjobportal.ui.AppDatabase;

public class JobDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);

        TextView title = findViewById(R.id.detail_title);
        TextView location = findViewById(R.id.detail_location);
        TextView salary = findViewById(R.id.detail_salary);
        TextView phone = findViewById(R.id.detail_phone);
        Button bookmarkButton = findViewById(R.id.bookmark_button);

        Job job = getIntent().getParcelableExtra(JobsFragment.EXTRA_JOB);
        if (job != null) {
            title.setText(job.getTitle());
            location.setText(job.getLocation());
            salary.setText(job.getSalary());
            phone.setText(job.getPhone());

            bookmarkButton.setOnClickListener(v -> {
                JobEntity jobEntity = new JobEntity();
                jobEntity.title = job.getTitle();
                jobEntity.location = job.getLocation();
                jobEntity.salary = job.getSalary();
                jobEntity.phone = job.getPhone();
                new Thread(() -> AppDatabase.getInstance(this).jobDao().insert(jobEntity)).start();
                Toast.makeText(this, "Job bookmarked", Toast.LENGTH_SHORT).show();
            });
        }
    }
}
