package com.sachin.news_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sachin.news_app.Pojo.Article;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class News_Adapter extends RecyclerView.Adapter<News_Adapter.MyHolder>
{
    Context context;
    List<Article> articleList;

    public News_Adapter(Context context, List<Article> articleList) {
        this.context = context;
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.news_layout,parent,false);
        MyHolder myHolder=new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.tvauthorname.setText(articleList.get(position).getAuthor());
      //  holder.tvdescription.setText(articleList.get(position).getDescription());
        holder.tvpublish.setText(articleList.get(position).getPublishedAt());
        holder.tvcontent.setText(articleList.get(position).getContent());
        holder.tvtitle.setText(articleList.get(position).getTitle());
        Glide.with(context).load(articleList.get(position).getUrlToImage()).into(holder.circleImageView);

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder
    {
        TextView tvtitle,tvdescription,tvauthorname,tvcontent,tvpublish;
        CircleImageView circleImageView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            tvtitle=itemView.findViewById(R.id.titleid);
          //  tvdescription=itemView.findViewById(R.id.descriptionid);
            tvauthorname=itemView.findViewById(R.id.authornameid);
            tvcontent=itemView.findViewById(R.id.contentid);
            tvpublish=itemView.findViewById(R.id.datetimeid);

            circleImageView=itemView.findViewById(R.id.profile_image);


        }
    }
}
