package com.example.timepilot.ui.main.faculty;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.timepilot.R;
import com.example.timepilot.databinding.ActivityFacultyBinding;
import com.example.timepilot.ui.fragments.FacultyBroadcastFragment;
import com.example.timepilot.ui.fragments.FacultyCreateTaskFragment;
import com.example.timepilot.ui.fragments.FacultyHomeFragment;
import com.example.timepilot.ui.fragments.FacultyScheduleFragment;

public class FacultyActivity extends AppCompatActivity {

    private ActivityFacultyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityFacultyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.facultyMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Load the default fragment (Faculty Home) on first launch
        if (savedInstanceState == null) {
            loadFragment(new FacultyHomeFragment());
        }

        // Set up faculty bottom navigation
        setupBottomNavigation();
    }

    private void setupBottomNavigation() {
        binding.facultyBottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.faculty_nav_home) {
                loadFragment(new FacultyHomeFragment());
                return true;
            } else if (itemId == R.id.faculty_nav_tasks) {
                loadFragment(new FacultyCreateTaskFragment());
                return true;
            } else if (itemId == R.id.faculty_nav_notices) {
                loadFragment(new FacultyBroadcastFragment());
                return true;
            } else if (itemId == R.id.faculty_nav_profile) {
                loadFragment(new FacultyScheduleFragment());
                return true;
            }
            return false;
        });
    }

    /**
     * Swap the currently displayed fragment in the faculty container.
     */
    public void loadFragment(Fragment fragment, boolean addToBackStack) {
        androidx.fragment.app.FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.facultyFragmentContainer, fragment);
        
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        
        transaction.commit();
    }

    private void loadFragment(Fragment fragment) {
        loadFragment(fragment, false);
    }
}
