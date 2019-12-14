package com.example.bismillah;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ProductActivity extends AppCompatActivity {

    ImageButton product1;

    String brand_data;
    String[] url_;
    Bitmap bitmap;
    String info, link, img_link;
    int product_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        product1 = findViewById(R.id.imageButton1_p);

        Intent intent = getIntent();
        brand_data = intent.getExtras().getString("BrandData");
        url_ = intent.getExtras().getStringArray("Url");

        try {
            JSONArray productsinfo = new JSONArray(brand_data);
            product_num = productsinfo.length();
        } catch (JSONException e) {
            e.printStackTrace();
        }




        JsonParser parser = new JsonParser();
        info = parser.Parsing(brand_data);
        link = parser.getUrl();
        img_link = parser.getImageUrl();



        //이미지 버튼에 사진 넣기

        Thread mthread = new Thread() {

            @Override
            public void run() {
                try {
                    URL imgLink = new URL(img_link);

                    HttpURLConnection conn = (HttpURLConnection) imgLink.openConnection();

                    conn.setDoInput(true);
                    conn.connect();

                    InputStream in = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(in);
                } catch (
                        MalformedURLException e) {
                    e.printStackTrace();
                } catch (
                        IOException e) {
                    e.printStackTrace();
                }
            }
        };

        mthread.start();

        try {
            mthread.join();
            product1.setImageBitmap(bitmap);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        product1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {



                Intent intent = new Intent(getApplicationContext(), PopupActivity.class);
                intent.putExtra("ProductInfo", info);
                intent.putExtra("Link",link);
                startActivity(intent);

                return true;
            }
        });
    }
}
