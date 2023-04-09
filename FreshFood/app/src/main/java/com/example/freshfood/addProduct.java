package com.example.freshfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.freshfood.api.ApiService;
import com.example.freshfood.model.AsiaFood;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addProduct extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    EditText name,price,desc,img;
    Button add;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        addProduct();
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void addProduct () {
        name = findViewById(R.id.add_name);
        price = findViewById(R.id.add_price);
        img = findViewById(R.id.add_img);
        desc = findViewById(R.id.add_des);
        add = findViewById(R.id.btn_add);
        add.setOnClickListener(v -> {
            String name1 = name.getText().toString();
            String price1 = price.getText().toString();
            String img1 = img.getText().toString();
            String desc1 = desc.getText().toString();
            if(!name1.isEmpty() && !price1.isEmpty() && !img1.isEmpty() && !desc1.isEmpty()){
                setAdd(name1,price1,img1,desc1);
                name.setText("");
                price.setText("");
                img.setText("");
                desc.setText("");
            }
        });
    }
    private void setAdd(String name,String price,String image,String desc){

        ApiService.apiService.addProduct(name,price,image,desc).enqueue(new Callback<AsiaFood>() {
            @Override
            public void onResponse(Call<AsiaFood> call, Response<AsiaFood> response) {
                if(response.isSuccessful()){
                    Toast.makeText(addProduct.this,"Add is success",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<AsiaFood> call, Throwable t) {

            }
        });

    }
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                Intent intent = new Intent(addProduct.this,MainAdmin.class);
                startActivity(intent);
                break;
            case R.id.nav_add:
                break;
            case R.id.nav_user:
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}