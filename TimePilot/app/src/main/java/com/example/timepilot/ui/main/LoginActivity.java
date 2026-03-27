package com.example.timepilot.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.timepilot.R;
import com.example.timepilot.data.model.Student;
import com.example.timepilot.databinding.ActivityLoginBinding;
import com.example.timepilot.ui.main.faculty.FacultyActivity;
import com.example.timepilot.ui.viewholder.StudentViewModel;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    StudentViewModel studentViewModel;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Edge-to-Edge Padding fix
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.toggleRole.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (isChecked) {
                if (checkedId == R.id.btnStudent) {
                    binding.btnStudent.setTextColor(getColor(R.color.purple_primary));
                    binding.btnFaculty.setTextColor(getColor(R.color.text_secondary));
                    binding.btnFaculty.setBackgroundColor(android.graphics.Color.TRANSPARENT);
                } else if (checkedId == R.id.btnFaculty) {
                    binding.btnFaculty.setTextColor(getColor(R.color.purple_primary));
                    binding.btnStudent.setTextColor(getColor(R.color.text_secondary));
                    binding.btnStudent.setBackgroundColor(android.graphics.Color.TRANSPARENT);
                }
            }
        });

        // Sign In Action: Determine role and redirect
        binding.btnSignIn.setOnClickListener(v -> {

            String email = binding.etId.getText().toString().trim();
            String password = binding.etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {

                Student student = studentViewModel.login(email, password);

                runOnUiThread(() -> {
                    if (student != null) {

                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();

                        boolean isFaculty = (binding.toggleRole.getCheckedButtonId() == R.id.btnFaculty);

                        // Role-based navigation
                        if (isFaculty) {
                            intent = new Intent(LoginActivity.this, FacultyActivity.class);
                        } else {
                            intent = new Intent(LoginActivity.this, MainActivity.class);
                        }

                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                    }
                });

            }).start();
        });
    }
}
