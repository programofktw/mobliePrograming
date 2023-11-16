package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickByButton(View v){
        Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show();

        //id로 객체를 찾는 것.
        TextView textView = (TextView)findViewById(R.id.textView);

        // 텍스트 속성 설정
        textView.setText("Change!!");
    }
}