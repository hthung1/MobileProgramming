package com.example.freshfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {
    ImageView back,image,increase,reduction,add_cart;
    TextView name,describe,price,qty;
    String image1,name1,price1,id1;
    database db = new database(Details.this, "freshfood.sqlite", null, 1);
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        back();

        image = findViewById(R.id.image_detail);
        name = findViewById(R.id.name_detail);
        describe = findViewById(R.id.describe_detail);
        price = findViewById(R.id.price_detail);
        reduction = findViewById(R.id.reduction);
        qty = findViewById(R.id.qty);
        increase = findViewById(R.id.increase);

        Intent intent = getIntent();
        name1 = intent.getStringExtra("name");
        String describe1 = intent.getStringExtra("describe");
        price1 = intent.getStringExtra("price");
        image1 = intent.getStringExtra("image");
        id1 = intent.getStringExtra("id");

        name.setText(name1);
        describe.setText(describe1);
        price.setText(price1);
        Picasso.get().load(image1).into(image);

        db.QueryData("CREATE TABLE IF NOT EXISTS cart(id INTEGER PRIMARY KEY AUTOINCREMENT,id_sanpham TEXT,tenhang TEXT,hinhanh TEXT,soluong int(11),dongia double)");
        setAdd_cart();
        setIncrease();
        setReduction();

    }
    // tăng sl
    private void setIncrease(){
        increase.setOnClickListener(v -> {
            int increase = Integer.parseInt(qty.getText().toString());
            increase += 1;
            qty.setText(String.valueOf(increase));
        });
    }
    private void setReduction(){
        reduction.setOnClickListener(v -> {
            int reduction = Integer.parseInt(qty.getText().toString());
            if(reduction > 1){
                reduction -= 1;
            }
            qty.setText(String.valueOf(reduction));
        });
    }
    //Add_cart
    private void setAdd_cart () {
        add_cart = findViewById(R.id.add_to_cart);
        add_cart.setOnClickListener(v -> {
            cursor = db.GetData("SELECT * FROM cart WHERE id_sanpham = "+id1+"");
            if(cursor.moveToNext()){
                int id = cursor.getInt(1);
                int sl = cursor.getInt(4) + Integer.parseInt(qty.getText().toString());
                float dg = cursor.getFloat(5)*sl;
                db.QueryData("UPDATE cart SET soluong = "+sl+", dongia = "+dg+" WHERE id_sanpham = "+id);
                Toast.makeText(Details.this,"Buy to success",Toast.LENGTH_SHORT).show();
            }else{
                int soluong =Integer.parseInt(qty.getText().toString());
                double tongtien = soluong * Double.parseDouble(price1);
                Toast.makeText(Details.this,"Buy to success",Toast.LENGTH_SHORT).show();
                db.QueryData("INSERT INTO cart(id_sanpham,tenhang,hinhanh,soluong,dongia) VALUES('"+id1+"','"+name1+"', '"+image1+"', "+soluong+", "+tongtien+")");
            }
            Intent intent = new Intent(Details.this,Cart.class);
            startActivity(intent);
        });
    }

    private void back(){
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}