package com.example.bismillah;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class ProductActivity extends AppCompatActivity {

    ImageButton[] product;
    TextView[] product_name;

    String brand_data;
    String[] url_;
    String[] info, link, img_link;
    int product_num;
    private Bitmap bitmap;
    String[] name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtime_products);
        product[0] = findViewById(R.id.Product1);
        product[1] = findViewById(R.id.Product2);
        product[2] = findViewById(R.id.Product3);
        product[3] = findViewById(R.id.Product4);
        product[4] = findViewById(R.id.Product5);
        product[5] = findViewById(R.id.Product6);
        product[6] = findViewById(R.id.Product7);
        product[7] = findViewById(R.id.Product8);

        product_name[0] = findViewById(R.id.text_p1);
        product_name[1] = findViewById(R.id.text_p2);
        product_name[2] = findViewById(R.id.text_p3);
        product_name[3] = findViewById(R.id.text_p4);
        product_name[4] = findViewById(R.id.text_p5);
        product_name[5] = findViewById(R.id.text_p6);
        product_name[6] = findViewById(R.id.text_p7);
        product_name[7] = findViewById(R.id.text_p8);

        Intent intent = getIntent();
        brand_data = intent.getExtras().getString("info");
        url_ = intent.getExtras().getStringArray("Url");

        try {
            JSONArray productsinfo = new JSONArray(brand_data);
            product_num = productsinfo.length();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //popup info 얻기
        for (int i =0; i< 8;i ++) {
            JsonParser parser = new JsonParser();
            info[i] = parser.Parsing(brand_data, i);
            link[i] = parser.getUrl();
            img_link[i] = parser.getImageUrl();
            name[i] = parser.getName();
        }


        //이미지 버튼에 사진과 텍스트 넣기
        for(int i =0; i< 8; i++) {
            final int finalI = i;

            Thread mthread = new Thread() {

                @Override
                public void run() {
                    try {
                        URL imgLink = new URL(img_link[finalI]);

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
                product[i].setImageBitmap(bitmap);
                product_name[i].setText(name[i]);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        //Popup 띄우기

        product[0].setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Intent intent = new Intent(getApplicationContext(), PopupActivity.class);
                intent.putExtra("ProductInfo", info[0]);
                intent.putExtra("Link",link[0]);
                startActivity(intent);

                return true;
            }
        });

        product[1].setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Intent intent = new Intent(getApplicationContext(), PopupActivity.class);
                intent.putExtra("ProductInfo", info[1]);
                intent.putExtra("Link",link[1]);
                startActivity(intent);

                return true;
            }
        });

        product[2].setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Intent intent = new Intent(getApplicationContext(), PopupActivity.class);
                intent.putExtra("ProductInfo", info[2]);
                intent.putExtra("Link",link[2]);
                startActivity(intent);

                return true;
            }
        });

        product[3].setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Intent intent = new Intent(getApplicationContext(), PopupActivity.class);
                intent.putExtra("ProductInfo", info[3]);
                intent.putExtra("Link",link[3]);
                startActivity(intent);

                return true;
            }
        });

        product[4].setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Intent intent = new Intent(getApplicationContext(), PopupActivity.class);
                intent.putExtra("ProductInfo", info[4]);
                intent.putExtra("Link",link[4]);
                startActivity(intent);

                return true;
            }
        });

        product[5].setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Intent intent = new Intent(getApplicationContext(), PopupActivity.class);
                intent.putExtra("ProductInfo", info[5]);
                intent.putExtra("Link",link[5]);
                startActivity(intent);

                return true;
            }
        });

        product[6].setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Intent intent = new Intent(getApplicationContext(), PopupActivity.class);
                intent.putExtra("ProductInfo", info[6]);
                intent.putExtra("Link",link[6]);
                startActivity(intent);

                return true;
            }
        });

        product[7].setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Intent intent = new Intent(getApplicationContext(), PopupActivity.class);
                intent.putExtra("ProductInfo", info[7]);
                intent.putExtra("Link",link[7]);
                startActivity(intent);

                return true;
            }
        });
    }
}
