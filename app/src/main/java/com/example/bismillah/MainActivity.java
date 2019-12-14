package com.example.bismillah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;


//Face part 고르기
/*
Blush
Bronzer
Eyebrow
Eyeliner
Eyeshadow
Foundation
Lip liner
Lipstick
Mascara

 */

public class MainActivity extends AppCompatActivity {

    ImageButton blush;
    ImageButton bronzer;
    ImageButton eyebrow;
    ImageButton eyeliner;
    ImageButton eyeshadow;
    ImageButton foundation;
    ImageButton lipstick;
    ImageButton lipliner;
    ImageButton mascara;

    String[] url_={"http://makeup-api.herokuapp.com/api/v1/products.json","","",""};

    String[] parts = {"blush"
            ,"bronzer"
            ,"eyebrow"
            ,"eyeliner"
            ,"eyeshadow"
            ,"foundation"
            ,"lipliner"
            ,"lipstick"
            ,"mascara"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtime_face);

        blush = findViewById(R.id.BrushButton);
        bronzer = findViewById(R.id.BronzerButton);
        eyebrow= findViewById(R.id.EyebrowButton);
        eyeshadow = findViewById(R.id.EyeshadowButton);
        eyeliner = findViewById(R.id.EyelinerButton);
        foundation= findViewById(R.id.FoundationButton);
        lipliner = findViewById(R.id.LiplinerButton);
        lipstick = findViewById(R.id.LipstickButton);
        mascara = findViewById(R.id.MascaraButton);


        blush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                url_[1] = "product_type=blush";

                Intent intent = new Intent(getApplicationContext(), BrandActivity.class);
                intent.putExtra("Url", url_);
                startActivity(intent);
            }
        });

        bronzer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                url_[1] = "product_type=bronzer";


                Intent intent = new Intent(getApplicationContext(), BrandActivity.class);
                intent.putExtra("Url", url_);
                startActivity(intent);
            }
        });
        eyeshadow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                url_[1] = "product_type=eyeshadow";

                Intent intent = new Intent(getApplicationContext(), BrandActivity.class);
                intent.putExtra("Url", url_);
                startActivity(intent);
            }
        });
        eyeliner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                url_[1] = "product_type=eyeliner";

                Intent intent = new Intent(getApplicationContext(), BrandActivity.class);
                intent.putExtra("Url", url_);
                startActivity(intent);
            }
        });
        eyebrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                url_[1] = "product_type=eyebrow";

                Intent intent = new Intent(getApplicationContext(), BrandActivity.class);
                intent.putExtra("Url", url_);
                startActivity(intent);
            }
        });
        foundation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                url_[1] = "product_type=foundation";

                Intent intent = new Intent(getApplicationContext(), BrandActivity.class);
                intent.putExtra("Url", url_);
                startActivity(intent);
            }
        });
        lipliner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                url_[1] = "product_type=lipliner";

                Intent intent = new Intent(getApplicationContext(), BrandActivity.class);
                intent.putExtra("Url", url_);
                startActivity(intent);
            }
        });
        lipstick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                url_[1] = "product_type=lipstick";

                Intent intent = new Intent(getApplicationContext(), BrandActivity.class);
                intent.putExtra("Url", url_);
                startActivity(intent);
            }
        });


        mascara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                url_[1] += "product_type=mascara";

                Intent intent = new Intent(getApplicationContext(), BrandActivity.class);
                intent.putExtra("Url", url_);
                startActivity(intent);
            }
        });


    }


}
