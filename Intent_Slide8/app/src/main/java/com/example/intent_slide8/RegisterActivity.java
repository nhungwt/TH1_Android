package com.example.intent_slide8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.intent_slide8.model.Account;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtUsername, edtPass;
    private Button btnCancel, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtUsername = findViewById(R.id.edtUsername);
        edtPass = findViewById(R.id.edtPass);
        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                Account account = new Account(edtUsername.getText().toString(), edtPass.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("data", account);
                setResult(RESULT_OK, intent);
                finish(); //Turn back to previous screen
                break;
            case R.id.btnCancel:
                setResult(RESULT_CANCELED, null);
                finish();
                break;
        }

// https://youtu.be/WyZ-kNUgCJM?list=PLD8zSU7U1L2GVhpPIUlJegpP8HRC2r58w&t=1562
    }
}