package com.example.bismillah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;

public class BrandActivity extends AppCompatActivity {

    String brand_info;
    String[] url_;

    ImageButton annasui;
    ImageButton benefit;
    ImageButton clinique;
    ImageButton dior;
    ImageButton ioreal;
    ImageButton maybelline;
    ImageButton nyx;
    ImageButton revlon;
    ImageButton sante;
    ImageButton stila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtime_brand);

        Intent intent = getIntent();
        url_ = intent.getExtras().getStringArray("Url");

        annasui = findViewById(R.id.AnnaButton);
        benefit = findViewById(R.id.BenefitButton);
        clinique = findViewById(R.id.CliniqueButton);
        dior = findViewById(R.id.DiorButton);
        ioreal = findViewById(R.id.IorealButton);;
        maybelline = findViewById(R.id.MaybellineButton);;
        nyx = findViewById(R.id.NyxButton);;
        revlon = findViewById(R.id.RevlonButton);;
        sante = findViewById(R.id.SanteButton);;
        stila = findViewById(R.id.StilaButton);;

        annasui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url_[2] = "brand=annasui";

                //brand info 데이터 가지고 오기
                try {
                    brand_info = new HttpConnector().execute(url_).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                Intent intent2 = new Intent(getApplicationContext(), ProductActivity.class);
                intent2.putExtra("info", brand_info);
                intent2.putExtra("Url", url_);
                startActivity(intent2);
            }
        });

        benefit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url_[2] = "brand=benefit";

                //brand info 데이터 가지고 오기
                try {
                    brand_info = new HttpConnector().execute(url_).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                Intent intent2 = new Intent(getApplicationContext(), ProductActivity.class);
                intent2.putExtra("info", brand_info);
                intent2.putExtra("Url", url_);
                startActivity(intent2);
            }
        });

        clinique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url_[2] = "brand=clinique";

                //brand info 데이터 가지고 오기
                try {
                    brand_info = new HttpConnector().execute(url_).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                Intent intent2 = new Intent(getApplicationContext(), ProductActivity.class);
                intent2.putExtra("info", brand_info);
                intent2.putExtra("Url", url_);
                startActivity(intent2);
            }
        });

        dior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url_[2] = "brand=dior";

                //brand info 데이터 가지고 오기
                try {
                    brand_info = new HttpConnector().execute(url_).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                Intent intent2 = new Intent(getApplicationContext(), ProductActivity.class);
                intent2.putExtra("info", brand_info);
                intent2.putExtra("Url", url_);
                startActivity(intent2);
            }
        });

        ioreal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url_[2] = "brand=ioreal";

                //brand info 데이터 가지고 오기
                try {
                    brand_info = new HttpConnector().execute(url_).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                Intent intent2 = new Intent(getApplicationContext(), ProductActivity.class);
                intent2.putExtra("info", brand_info);
                intent2.putExtra("Url", url_);
                startActivity(intent2);
            }
        });

        maybelline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url_[2] = "brand=maybelline";

                //brand info 데이터 가지고 오기
                try {
                    brand_info = new HttpConnector().execute(url_).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                Intent intent2 = new Intent(getApplicationContext(), ProductActivity.class);
                intent2.putExtra("info", brand_info);
                intent2.putExtra("Url", url_);
                startActivity(intent2);
            }
        });

        nyx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url_[2] = "brand=nyx";

                //brand info 데이터 가지고 오기
                try {
                    brand_info = new HttpConnector().execute(url_).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                Intent intent2 = new Intent(getApplicationContext(), ProductActivity.class);
                intent2.putExtra("info", brand_info);
                intent2.putExtra("Url", url_);
                startActivity(intent2);
            }
        });

        revlon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url_[2] = "brand=revlon";

                //brand info 데이터 가지고 오기
                try {
                    brand_info = new HttpConnector().execute(url_).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                Intent intent2 = new Intent(getApplicationContext(), ProductActivity.class);
                intent2.putExtra("info", brand_info);
                intent2.putExtra("Url", url_);
                startActivity(intent2);
            }
        });

        sante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url_[2] = "brand=sante";

                //brand info 데이터 가지고 오기
                try {
                    brand_info = new HttpConnector().execute(url_).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                Intent intent2 = new Intent(getApplicationContext(), ProductActivity.class);
                intent2.putExtra("info", brand_info);
                intent2.putExtra("Url", url_);
                startActivity(intent2);
            }
        });

        stila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url_[2] = "brand=stila";

                //brand info 데이터 가지고 오기
                try {
                    brand_info = new HttpConnector().execute(url_).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                Intent intent2 = new Intent(getApplicationContext(), ProductActivity.class);
                intent2.putExtra("info", brand_info);
                intent2.putExtra("Url", url_);
                startActivity(intent2);
            }
        });


    }
}
