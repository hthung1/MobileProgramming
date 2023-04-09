package com.example.freshfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freshfood.api.ApiService;
import com.example.freshfood.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends AppCompatActivity {
    TextView login;
    EditText username,email,phone,pass,pass_confirm;
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setLogin();
        signup();
    }
    private void setLogin(){
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this,Login.class);
                startActivity(intent);
            }
        });
    }
    private void signup(){
        username = findViewById(R.id.inputName);
        email = findViewById(R.id.editTextTextEmailAddress);
        phone = findViewById(R.id.editTextPhone);
        pass = findViewById(R.id.inputPassword);
        pass_confirm = findViewById(R.id.confirmPassword);
        signup = findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String mail = email.getText().toString();
                String num = phone.getText().toString();
                String pss = pass.getText().toString();
                String cf_pss = pass_confirm.getText().toString();
                check1(user,mail,num,pss,cf_pss);
            }
        });
    }
    private void check1(String user,String mail,String num,String pss,String cf_pss){
        if(!user.isEmpty() && !mail.isEmpty() && !num.isEmpty() && !pss.isEmpty() && !cf_pss.isEmpty()){
//            if(pss != cf_pss){
//                Toast.makeText(Signup.this,"Repeat incorrect password!",Toast.LENGTH_SHORT).show();
//            }else{
                check2(user,mail,num,pss);

//            }
        }else{
            Toast.makeText(Signup.this,"Please enter enough information!",Toast.LENGTH_SHORT).show();
        }
    }
    private void check2(String user,String mail,String num,String pss){
        ApiService.apiService.signup(user,mail,num,pss).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(Signup.this,"SignUp is success!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}