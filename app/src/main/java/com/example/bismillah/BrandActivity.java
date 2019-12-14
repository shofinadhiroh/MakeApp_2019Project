package com.example.bismillah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class BrandActivity extends AppCompatActivity {

    String brand_data;
    String[] url_;
    int brand_num;

    LinearLayout layout;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);

        Intent intent = getIntent();
        brand_data = intent.getExtras().getString("BrandData");
        url_ = intent.getExtras().getStringArray("Url");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), ProductActivity.class);
                intent2.putExtra("BrandData", brand_data);
                intent2.putExtra("Url", url_);
                startActivity(intent2);
            }
        });



//        test.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
//                intent.putExtra("BrandData", brand_data);
//                intent.putExtra("Url", url_);
//                startActivity(intent);
//            }
//        });

    }
}
