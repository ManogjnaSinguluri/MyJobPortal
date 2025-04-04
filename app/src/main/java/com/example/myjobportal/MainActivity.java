package com.example.myjobportal;

import android.os.Bundle;

import com.example.myjobportal.ui.bookmarks.BookmarksFragment;
import com.example.myjobportal.ui.jobs.JobsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myjobportal.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.nav_view); // Updated ID to match your XML
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            if (item.getItemId() == R.id.navigation_jobs) { // Updated ID to match your menu
                selectedFragment = new JobsFragment();
            } else if (item.getItemId() == R.id.navigation_bookmarks) {
                selectedFragment = new BookmarksFragment();
            }
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main, selectedFragment) // Updated ID to match your XML
                        .commit();
            }
            return true;
        });

        // Load JobsFragment by default
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_main, new JobsFragment())
                    .commit();
        }
    }
}