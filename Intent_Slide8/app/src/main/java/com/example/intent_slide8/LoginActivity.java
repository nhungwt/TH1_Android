package com.example.intent_slide8;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.intent_slide8.model.Account;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtUsername, edtPass;
    private Button btnLogin, btnRegister;
    public final static int REQUEST_CODE = 10000;
    private Account u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUsername = findViewById(R.id.edtUsername);
        edtPass = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                Intent loginIntent = new Intent(LoginActivity.this,
                        HomeActivity.class);
                Account account = new Account(edtUsername.getText().toString(), edtPass.getText().toString());
                loginIntent.putExtra("account", account);
                loginIntent.putExtra("userRegisted", u);
                startActivity(loginIntent);
                break;
            case R.id.btnRegister:
                Intent registerIntent = new Intent(LoginActivity.this,
                        RegisterActivity.class);
//                Bcs want to receive back data form Register, so use this method instead of
//                startActivity(), then the result can be received when
//                call method onActivityResult()
                startActivityForResult(registerIntent, REQUEST_CODE);
                break;
        }
    }

    //  This method go with startActivityForResult() above
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                if (data == null) { // When user doesnot enter data
                    Toast.makeText(getApplicationContext(), "Ban da huy dang ki",
                            Toast.LENGTH_LONG).show();
                } else {
                    u = (Account) data.getSerializableExtra("data");
                    edtUsername.setText(u.getUsername());
                    edtPass.setText(u.getPass());
                }
            } else {
                u = null;
            }
        }
    }
}