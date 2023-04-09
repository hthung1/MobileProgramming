package com.example.freshfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freshfood.adapter.CartAdapter;
import com.example.freshfood.api.ApiService;
import com.example.freshfood.model.Bill;
import com.example.freshfood.model.CartFood;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cart extends AppCompatActivity {
    RecyclerView cart_rcv;
    CartAdapter cartAdapter;
    ImageView back;
    TextView total, qty_item;
    Button checkout;
    private List<CartFood> cartFoods;
    database db = new database(Cart.this, "freshfood.sqlite", null, 1);
    Cursor cursor;
    float tt = 0;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        back();
        List<CartFood> cartFoods = new ArrayList<>();
        cursor = db.GetData("SELECT * FROM cart");
        while (cursor.moveToNext()) {
            cartFoods.add(new CartFood(cursor.getString(2), cursor.getString(5), cursor.getString(3), cursor.getString(1), cursor.getString(4)));
            setCartAdapter(cartFoods);
            tt += Float.parseFloat(cursor.getString(5));
            i++;

        }
        qty_item = findViewById(R.id.qty_item);
        qty_item.setText(i + "");
        total = findViewById(R.id.total);
        total.setText(tt + "");
        setCartAdapter(cartFoods);
        setCheckout();
    }

    private void setCartAdapter(List<CartFood> cartList) {
        cart_rcv = findViewById(R.id.cart_rcv);
        cart_rcv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        cartAdapter = new CartAdapter(this, cartList);
        cart_rcv.setAdapter(cartAdapter);
    }

    private void setCheckout() {
        checkout = findViewById(R.id.check_out);
        checkout.setOnClickListener(v -> {
            JSONArray jsonArray = new JSONArray();
            cursor = db.GetData("SELECT * FROM cart");

            while (cursor.moveToNext()) {
                JSONObject jsonObject = new JSONObject();
                String id_sp = cursor.getString(1);
                String name = cursor.getString(2);
                String sl = cursor.getString(4);
                String total = cursor.getString(5);

                try {
                    jsonObject.put("id_sanpham", id_sp);
                    jsonObject.put("tenhang", name);
                    jsonObject.put("so_luong", sl);
                    jsonObject.put("don_gia", total);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                jsonArray.put(jsonObject);
            }

            String json = jsonArray.toString();
            String id_user = "";
            cursor = db.GetData("SELECT * FROM token");
            if (cursor.moveToNext()) {
                id_user = cursor.getString(1);
            }
            String finalId_user = id_user;
            ApiService.apiService.bill(json, tt, finalId_user).enqueue(new Callback<Bill>() {
                @Override
                public void onResponse(Call<Bill> call, Response<Bill> response) {
                    Bill bill = response.body();
                }

                @Override
                public void onFailure(Call<Bill> call, Throwable t) {
                    Toast.makeText(Cart.this, "Success", Toast.LENGTH_SHORT).show();
                }
            });
            db.QueryData("DELETE FROM cart");
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        });
    }

    private void back() {
        back = findViewById(R.id.back);
        back.setOnClickListener(v -> finish());
    }
}