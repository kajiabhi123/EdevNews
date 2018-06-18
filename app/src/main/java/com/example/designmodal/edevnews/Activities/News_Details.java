package com.example.designmodal.edevnews.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.designmodal.edevnews.R;
import com.squareup.picasso.Picasso;

public class News_Details extends AppCompatActivity
{
    TextView txtTitle,txtDate,txtDetail;
    ImageView ImageNews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news__details);
        txtTitle = (TextView) findViewById(R.id.news_title);
        txtDate = (TextView) findViewById(R.id.news_date);
        txtDetail = (TextView) findViewById(R.id.news_body);
        ImageNews = (ImageView) findViewById(R.id.Details_news_image);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            String news_title = (String) bd.get("title");
            String news_date = (String) bd.get("date");
            String news_detail = (String) bd.get("detail");
            String ImageUrl  = (String) bd.get("imageurl");


            //setting the job description value

            txtTitle.setText(news_title);
            txtDate.setText(news_date);
            txtDetail.setText(news_detail);
            Picasso.with(this).load(ImageUrl).into(ImageNews);

        }
    }
}
