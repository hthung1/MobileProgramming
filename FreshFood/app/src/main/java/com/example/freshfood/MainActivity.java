package com.example.freshfood;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.freshfood.adapter.AsiaFoodAdapter;
import com.example.freshfood.adapter.PopularFoodAdapter;
import com.example.freshfood.api.ApiService;
import com.example.freshfood.model.AsiaFood;
import com.example.freshfood.model.PopularFood;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Cart> carts;
    private RecyclerView asia_rcv, food_rcv;
    PopularFoodAdapter popularFoodAdapter;
    AsiaFoodAdapter asiaFoodAdapter;
    private List<PopularFood> PopularFoods;
    private List<AsiaFood> AsiaFoods;
    ImageView cart,login;
    EditText search;
    database db = new database(MainActivity.this, "freshfood.sqlite", null, 1);
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        food_rcv = findViewById(R.id.food_rcv);
        asia_rcv = findViewById(R.id.asia_rcv);
        click_cart();
        setLogin();
        getPopularFood();
        search();
        getAsiaFood();
        cursor = db.GetData("SELECT * FROM token");
        if (cursor.moveToNext())
            cursor.getString(1);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull String name, @NonNull @NotNull Context context, @NonNull @NotNull AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    private void getAsiaFood () {
        ApiService.apiService.asiaFoodCall().enqueue(new Callback<List<AsiaFood>>() {
            @Override
            public void onResponse(Call<List<AsiaFood>> call, Response<List<AsiaFood>> response) {
                AsiaFoods = response.body();
                asia(AsiaFoods);
            }

            @Override
            public void onFailure(Call<List<AsiaFood>> call, Throwable t) {

            }
        });
    }


    private void asia(List<AsiaFood> list){
        asia_rcv.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false));
        asia_rcv.setAdapter(new AsiaFoodAdapter(list,MainActivity.this));
    }


    private void getPopularFood () {
        ApiService.apiService.popularFoodCall().enqueue(new Callback<List<PopularFood>>() {
            @Override
            public void onResponse(Call<List<PopularFood>> call, Response<List<PopularFood>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this,"call api success",Toast.LENGTH_SHORT).show();
                    PopularFoods = response.body();
                    popular(PopularFoods);

                }
            }

            @Override
            public void onFailure(Call<List<PopularFood>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"call api faild",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void popular(List<PopularFood> list){
        food_rcv.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false));
        food_rcv.setAdapter(new PopularFoodAdapter(list, MainActivity.this));
    }

    private void click_cart(){
        cart = findViewById(R.id.cart_img);
        cart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Cart.class);
            startActivity(intent);
        });
    }
    private void setLogin(){
        login = findViewById(R.id.login);
        login.setOnClickListener(v -> {
            db.QueryData("DELETE FROM token");
            Intent intent = new Intent(MainActivity.this,Login.class);
            startActivity(intent);
        });
    }

    private void search () {
        search = findViewById(R.id.searchFood);
        search.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Search.class);
            startActivity(intent);
        });
    }

    private void setCart () {
        if(carts != null){

        }else{
            carts = new ArrayList<>();
        }
    }

}