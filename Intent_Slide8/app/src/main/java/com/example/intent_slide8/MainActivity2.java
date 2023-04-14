package com.example.intent_slide8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.intent_slide8.model.Student;

import java.util.Arrays;

public class MainActivity2 extends AppCompatActivity {
    TextView tv, tvObject;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//        Gửi nhận bình thường
        tv=findViewById(R.id.tv);
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        int age=intent.getIntExtra("age",123);
        String[] subject=intent.getStringArrayExtra("subject");
        String result="Name:"+name+"\n age:"+age+"\nSubject:"+
                Arrays.toString(subject);
        tv.setText(result);

//      Gửi nhận object
        tvObject = findViewById(R.id.tvObject);
        Student s=(Student) intent.getSerializableExtra("chris");
        tvObject.setText(s.getName()+" "+s.getAge());
        img=findViewById(R.id.img);
//        txtname=findViewById(R.id.tvName);
        img.setImageResource(s.getImg());
//        txtname.setText(s.getName());

    }
}