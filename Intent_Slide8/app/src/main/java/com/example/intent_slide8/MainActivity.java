package com.example.intent_slide8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.intent_slide8.model.Student;

public class MainActivity extends AppCompatActivity {
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new
                        Intent(MainActivity.this, MainActivity2.class);
                String name = "To An An";
                intent.putExtra("name", name);
                int age = 22;
                intent.putExtra("age", age);
                String[] subject = {"OOP", "Computer Graphics", "Android"};
                intent.putExtra("subject", subject);

                Student s = new Student(R.drawable.img, "Chris Hemsworth", 37);
                intent.putExtra("chris", s);

                startActivity(intent);
            }
        });
    }
}