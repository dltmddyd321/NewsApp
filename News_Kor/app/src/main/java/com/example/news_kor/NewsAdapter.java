package com.example.news_kor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    Context context;
    ArrayList<NewsModel> newsModelArrayList;

    public NewsAdapter(Context context, ArrayList<NewsModel> newsModelArrayList) {
        this.context = context;
        this.newsModelArrayList = newsModelArrayList;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflater를 통해 Adapter에 layout 연결
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebView.class);
                intent.putExtra("url",newsModelArrayList.get(position).getUrl());
                context.startActivity(intent);
            }
        });

        holder.time.setText("Published At:-"+newsModelArrayList.get(position).getPublishedAt());
        holder.author.setText(newsModelArrayList.get(position).getAuthor());
        holder.heading.setText(newsModelArrayList.get(position).getTitle());
        holder.content.setText(newsModelArrayList.get(position).getDescription());

        Glide.with(context)
                .load(newsModelArrayList.get(position).getUrlToImage())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return newsModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView heading, content, author, time;
        CardView cardView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //데이터를 보여줄 화면의 요소들을 연결
            heading = itemView.findViewById(R.id.mainHeader);
            content = itemView.findViewById(R.id.newsContent);
            author = itemView.findViewById(R.id.author);
            time = itemView.findViewById(R.id.time);
            imageView = itemView.findViewById(R.id.newsImg);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
