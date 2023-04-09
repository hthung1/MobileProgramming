package com.example.freshfood;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freshfood.api.ApiService;
import com.example.freshfood.model.User;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    TextView signup;
    EditText username,password;
    Button login;
    ProgressDialog progressDialog;

    database db = new database(Login.this,"freshfood.sqlite",null,1);
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        db.QueryData("CREATE TABLE IF NOT EXISTS token(id INTEGER PRIMARY KEY AUTOINCREMENT, token TEXT)");
        setSignup();
        setLogin();
        toIntent();
    }
    private void setSignup(){
        signup = findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Signup.class);
                startActivity(intent);
            }
        });

    }
    private void setLogin(){
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                check1(user,pass);
            }
        });
    }
    private void check1(String user,String pass){
        if(!user.isEmpty() && !pass.isEmpty()){
            check2(user,pass);
            progressDialog.show();
        }else{
            Toast.makeText(Login.this,"Please enter enough information!",Toast.LENGTH_SHORT).show();
        }
    }
    private void check2(String user,String pass){
        ApiService.apiService.userCall(user,pass).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user1 = response.body();
                if (response.isSuccessful()){
                    Toast.makeText(Login.this, "login is success", Toast.LENGTH_SHORT).show();
                    if(user1.getRole().equals("0")) {
                        toMainActivity(user1.getId());
                    }else if(user1.getRole().equals("1")){
                        toAdmin(user1.getId());
                    }
                }
                else {
                    Toast.makeText(Login.this,"login is failse",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(Login.this,"Username and password don't match",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void toMainActivity(String id) {
        db.QueryData("DELETE FROM token");
        db.QueryData("DELETE FROM cart");
        Intent intent = new Intent(Login.this, MainActivity.class);
        db.QueryData("INSERT INTO token VALUES(null,'"+id+"')");
        startActivity(intent);
        progressDialog.hide();

    }
    private void toAdmin(String id) {
        db.QueryData("DELETE FROM token");
        db.QueryData("DELETE FROM cart");
        Intent intent = new Intent(Login.this, MainAdmin.class);
        db.QueryData("INSERT INTO token VALUES(null,'"+id+"')");
        startActivity(intent);
        progressDialog.hide();
    }
    private void toIntent(){
        cursor = db.GetData("SELECT * FROM token");
        Intent intent;
        if (cursor.moveToNext()){
            if(cursor.getString(1).equals("2")){
                intent = new Intent(Login.this,MainAdmin.class);
            }
            else{
                intent = new Intent(Login.this,MainActivity.class);
            }
            startActivity(intent);
        }
    }
}