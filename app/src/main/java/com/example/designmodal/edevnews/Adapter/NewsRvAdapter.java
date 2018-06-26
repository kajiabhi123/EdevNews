package com.example.designmodal.edevnews.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.designmodal.edevnews.Activities.News_Details;
import com.example.designmodal.edevnews.DataManager.ApiClient;
import com.example.designmodal.edevnews.Model.NewsModel;
import com.example.designmodal.edevnews.R;
import com.squareup.picasso.Picasso;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by compware on 6/17/2018.
 */

public class NewsRvAdapter extends RecyclerView.Adapter<RecyclerViewHolder>
{

    Context context;
    private List<NewsModel> item;
    String news_time;
    public NewsRvAdapter(Context context, List<NewsModel> item)
    {
        this.context = context;
        this.item = item;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.news_feeds, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(layoutView);

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position)
    {
        final String news_title = item.get(position).getNews_title();
        final String news_description = item.get(position).getNews_detail();
        final String news_date = item.get(position).getNews_date();
        String imageName = item.get(position).getNews_image();
        final String ImageUrl = ApiClient.BASE_URL+"uploads/"+imageName;

         Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //return number of milliseconds since January 1, 1970, 00:00:00 GMT
        long current_time = timestamp.getTime();
        //Date current = new Date();
        try {
            SimpleDateFormat yourStandardDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date Publish_Date = yourStandardDateFormat.parse(news_date);
            //date1 = dates.parse(CurrentDate);

            // Comparing dates
            long difference = Math.abs(current_time-(Publish_Date.getTime()));
            long diff_in_sec = (difference/1000);
            long diff_in_minutes = (difference/60000);
            long diff_in_hours = (difference/3600000);
            long diff_in_days = (difference/86400000);
            //long diff_in_years =  (difference/(31536000));


            Toast.makeText(context, diff_in_days+"", Toast.LENGTH_SHORT).show();


            if (diff_in_sec<=60)
            {
                news_time ="Just Now";

            } else if (diff_in_minutes <(60))
            {
                news_time = diff_in_minutes +" "+"Minutes Ago";

            }
            else if (diff_in_hours <(24))
            {
                news_time = diff_in_hours+ " "+"Hours Ago";


            }
            else if (diff_in_days>=1)
            {
                news_time = diff_in_days +""+" Days Ago";
            }
            holder.dateView.setText(news_time);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }


        YoYo.with(Techniques.FadeIn).playOn(holder.cardView);
        holder.titleView.setText(news_title);
        holder.descriptionView.setText(news_description);
        //holder.dateView.setText(minutes1+"");
        Picasso.with(context).load(ImageUrl).into(holder.newsImg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Toast.makeText(context, ImageUrl, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, News_Details.class);
                intent.putExtra("date",news_date);
                intent.putExtra("title",news_title);
                intent.putExtra("detail",news_description);
                intent.putExtra("imageurl",ImageUrl);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return item.size();
    }
}
