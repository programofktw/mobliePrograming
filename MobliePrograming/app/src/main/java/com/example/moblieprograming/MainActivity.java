package com.example.moblieprograming;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    EditText nameText;
    EditText emailText;

    EditText phoneText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //자신의 EditText ID로 대체
        nameText = (EditText) findViewById(R.id.NameText);
        emailText= (EditText) findViewById(R.id.emailText);
        phoneText = (EditText) findViewById(R.id.phoneText);

    }


    public void onClickSave(View view){
        try{
            String name = nameText.getText().toString();
            String email = emailText.getText().toString();
            String phone = phoneText.getText().toString();
            String output = name + "/" + email + "/" + phone;
            FileOutputStream outputStream = openFileOutput("filename", MODE_PRIVATE);
            outputStream.write(output.getBytes());
            outputStream.close();//반드시 해줄것.
            nameText.setText("");
            emailText.setText("");
            phoneText.setText("");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onClickLoad(View view){
        try {
            byte[] b = new byte[1000];
            FileInputStream inputStream = openFileInput("filename");
            inputStream.read(b);
            String s = new String(b);
            inputStream.close(); //반드시 해줄 것.

            //입력값 구분
            StringTokenizer stringTokenizer = new StringTokenizer(s,"/");

            nameText.setText(stringTokenizer.nextToken());
            emailText.setText(stringTokenizer.nextToken());
            phoneText.setText(stringTokenizer.nextToken());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClickReset(View view){
        deleteFile("filename");
        nameText.setText("name");
        emailText.setText("email");
        phoneText.setText("01000000000");
    }

    public void onClick(View view){
        byte[] b = new byte[1000];
        try{
            //자신의 raw디렉토리 파일명(확장자는 포함 X)
            //읽기만 가능
            InputStream inputStream = getResources().openRawResource(R.raw.data);
            inputStream.read(b);
            String s = new String(b);

            inputStream.close(); //반드시 해줄 것.

            //입력값 구분
            StringTokenizer stringTokenizer = new StringTokenizer(s,"/");

            nameText.setText(stringTokenizer.nextToken());
            emailText.setText(stringTokenizer.nextToken());
            phoneText.setText(stringTokenizer.nextToken());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String state = Environment.getExternalStorageState();

        Toast.makeText(this, state, Toast.LENGTH_SHORT).show();
    }

    public void onClickOutSave(View view){

        try {
            FileOutputStream outputStream = new FileOutputStream(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                            .getAbsoluteFile()+"/aaa.txt");
            String name = nameText.getText().toString();
            String email = emailText.getText().toString();
            String phone = phoneText.getText().toString();
            String output = name + "/" + email + "/" + phone;
            outputStream.write(output.getBytes());
            outputStream.close();
            nameText.setText("");
            emailText.setText("");
            phoneText.setText("");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void onClickOutLoad(View view){
        try {
            byte[] b = new byte[1000];
            FileInputStream inputStream = new FileInputStream(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                            .getAbsolutePath()+"/aaa.txt");
            inputStream.read(b);
            String s = new String(b);
            inputStream.close();
            StringTokenizer stringTokenizer = new StringTokenizer(s,"/");

            nameText.setText(stringTokenizer.nextToken());
            emailText.setText(stringTokenizer.nextToken());
            phoneText.setText(stringTokenizer.nextToken());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}