package com.example.intent_slide8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.intent_slide8.model.Account;

public class HomeActivity extends AppCompatActivity {

    private TextView txtInfo;
    private Account user, accountEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        txtInfo = findViewById(R.id.txtInfo);
        Intent intent = getIntent();
        if ((intent.getSerializableExtra("userRegisted") != null) &&
                (intent.getSerializableExtra("account")) != null){

            user = (Account) intent.getSerializableExtra("userRegisted");
            accountEnter = (Account) intent.getSerializableExtra("account");
            if (user.getUsername().equalsIgnoreCase(accountEnter.getUsername()) &&
                    user.getPass().equals(accountEnter.getPass())) {
                txtInfo.setText("Hello " + user.getUsername()
                        + "\nWellcom to Home...");
            } else {
                txtInfo.setText("Account not exist!");
            }
        }
    }
}