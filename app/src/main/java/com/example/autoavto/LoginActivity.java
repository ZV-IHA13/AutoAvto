package com.example.autoavto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText LogInPass;
    EditText LogInLog;
    Button LogInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        LogInButton = findViewById(R.id.LogInButton);
        LogInLog = findViewById(R.id.LogInLog);
        LogInPass = findViewById(R.id.LogInPass);

        View.OnClickListener c = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((LogInLog.getText().toString() == "admin") && (LogInPass.getText().toString() == "1234")) {

                }
            }
        };
        LogInButton.setOnClickListener(c);
    }
}