package com.example.designmodal.edevnews.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.designmodal.edevnews.DataManager.ApiClient;
import com.example.designmodal.edevnews.DataManager.ApiInterface;
import com.example.designmodal.edevnews.Model.AdsModel;
import com.example.designmodal.edevnews.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class News_Details extends AppCompatActivity
{
    TextView txtTitle,txtDate,txtDetail;
    ImageView ImageNews;
    FloatingActionButton share;
    AdView mAdview ;
    InterstitialAd interstitialAd;
    ImageView adtop,ad1,ad2,ad3,ad4,ad5,ad6,ad7,ad8;

    String AdsTopUrl,Ads1Url,Ads2Url,Ads3Url,Ads4Url,Ads5Url,Ads6Url,Ads7Url,Ads8Url;
    String AdsTopLink,Ads1Link,Ads2Link,Ads3Link,Ads4Link,Ads5Link,Ads6Link,Ads7Link,Ads8Link;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news__details);
        txtTitle = (TextView) findViewById(R.id.news_title);
        txtDate = (TextView) findViewById(R.id.news_date);
        txtDetail = (TextView) findViewById(R.id.news_body);
        ImageNews = (ImageView) findViewById(R.id.Details_news_image);
        share = (FloatingActionButton) findViewById(R.id.fab);
        prepareAd();

        adtop = (ImageView) findViewById(R.id.adTop);
        ad1 = (ImageView) findViewById(R.id.adView1);
        ad2 = (ImageView) findViewById(R.id.adView2);
        ad3 = (ImageView) findViewById(R.id.adView3);
        ad4 = (ImageView) findViewById(R.id.adView4);
        ad5 = (ImageView) findViewById(R.id.adView5);
        ad6 = (ImageView) findViewById(R.id.adView6);
        ad7 = (ImageView) findViewById(R.id.adView7);
        ad8 = (ImageView) findViewById(R.id.adView8);

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        scheduledExecutorService.schedule(new Runnable()
        {
            @Override
            public void run()
            {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run() {
                        if(interstitialAd.isLoaded())
                        {
                            interstitialAd.show();
                        }
                        prepareAd();
                    }
                });

            }
        },20, TimeUnit.SECONDS);
        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");

        mAdview = (AdView) findViewById(R.id.banner_AdView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("17DC3FC438A14114DE14161D7C8F8868").build();
        mAdview.loadAd(adRequest);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent ShareNews = new Intent(Intent.ACTION_SEND);
                ShareNews.setType("text/plain");
                String shareBody = "body here ";
                String shareSub = "your suject here";
                ShareNews.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                ShareNews.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(ShareNews,"Share Using"));
            }
        });
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

        getAdsDetails();
    }


    public void prepareAd()
    {
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.loadAd(new AdRequest.Builder().addTestDevice("17DC3FC438A14114DE14161D7C8F8868").build());

    }

    public void getAdsDetails()
    {
        ApiInterface service = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<List<AdsModel>> call = service.getAds();

        call.enqueue(new Callback<List<AdsModel>>() {
            @Override
            public void onResponse(Call<List<AdsModel>> call, Response<List<AdsModel>> response)
            {
                List<AdsModel> AdsList = response.body();


                AdsTopUrl=ApiClient.BASE_URL+"Ads/"+AdsList.get(0).getAds_image();
                Ads1Url=ApiClient.BASE_URL+"Ads/"+AdsList.get(1).getAds_image();
                Ads2Url=ApiClient.BASE_URL+"Ads/"+AdsList.get(2).getAds_image();
                Ads3Url=ApiClient.BASE_URL+"Ads/"+AdsList.get(3).getAds_image();
                Ads4Url=ApiClient.BASE_URL+"Ads/"+AdsList.get(4).getAds_image();
                Ads5Url=ApiClient.BASE_URL+"Ads/"+AdsList.get(5).getAds_image();
                Ads6Url=ApiClient.BASE_URL+"Ads/"+AdsList.get(6).getAds_image();
                Ads7Url=ApiClient.BASE_URL+"Ads/"+AdsList.get(7).getAds_image();
                Ads8Url=ApiClient.BASE_URL+"Ads/"+AdsList.get(8).getAds_image();

                Glide.with(News_Details.this).load(AdsTopUrl).asGif().into(adtop);
                Glide.with(News_Details.this).load(Ads1Url).asGif().into(ad1);
                Glide.with(News_Details.this).load(Ads2Url).asGif().into(ad2);
                Glide.with(News_Details.this).load(Ads3Url).asGif().into(ad3);
                Glide.with(News_Details.this).load(Ads4Url).asGif().into(ad4);
                Glide.with(News_Details.this).load(Ads5Url).asGif().into(ad5);
                Glide.with(News_Details.this).load(Ads6Url).asGif().into(ad6);
                Glide.with(News_Details.this).load(Ads7Url).asGif().into(ad7);
                Glide.with(News_Details.this).load(Ads8Url).asGif().into(ad8);

                AdsTopLink = AdsList.get(0).getAds_link();
                Ads1Link = AdsList.get(1).getAds_link();
                Ads2Link = AdsList.get(2).getAds_link();
                Ads3Link = AdsList.get(3).getAds_link();
                Ads4Link = AdsList.get(4).getAds_link();
                Ads5Link = AdsList.get(5).getAds_link();
                Ads6Link = AdsList.get(6).getAds_link();
                Ads7Link = AdsList.get(7).getAds_link();
                Ads8Link = AdsList.get(8).getAds_link();
                adtop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        String url = String.valueOf(Uri.parse(AdsTopLink));
                        //Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(News_Details.this,Ad_webView.class);
                        intent.putExtra("link",url);
                        startActivity(intent);
                    }
                });

                ad1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        String url = String.valueOf(Uri.parse(Ads1Link));
                        //Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(News_Details.this,Ad_webView.class);
                        intent.putExtra("link",url);
                        startActivity(intent);
                    }
                });
                ad2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        String url = String.valueOf(Uri.parse(Ads2Link));
                        //Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(News_Details.this,Ad_webView.class);
                        intent.putExtra("link",url);
                        startActivity(intent);
                    }
                });
                ad3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        String url = String.valueOf(Uri.parse(Ads3Link));
                        //Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(News_Details.this,Ad_webView.class);
                        intent.putExtra("link",url);
                        startActivity(intent);
                    }
                });

                ad4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        String url = String.valueOf(Uri.parse(Ads3Link));
                        //Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(News_Details.this,Ad_webView.class);
                        intent.putExtra("link",url);
                        startActivity(intent);
                    }
                });

                ad5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        String url = String.valueOf(Uri.parse(Ads5Link));
                        //Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(News_Details.this,Ad_webView.class);
                        intent.putExtra("link",url);
                        startActivity(intent);
                    }
                });
                ad6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        String url = String.valueOf(Uri.parse(Ads6Link));
                        //Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(News_Details.this,Ad_webView.class);
                        intent.putExtra("link",url);
                        startActivity(intent);
                    }
                });
                ad7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        String url = String.valueOf(Uri.parse(Ads7Link));
                        //Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(News_Details.this,Ad_webView.class);
                        intent.putExtra("link",url);
                        startActivity(intent);
                    }
                });

                ad8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        String url = String.valueOf(Uri.parse(Ads8Link));
                        //Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(News_Details.this,Ad_webView.class);
                        intent.putExtra("link",url);
                        startActivity(intent);
                    }
                });
            }
            @Override
            public void onFailure(Call<List<AdsModel>> call, Throwable t)
            {
                Toast.makeText(News_Details.this, "error", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

    }
}
