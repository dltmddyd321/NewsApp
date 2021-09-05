package com.example.news_kor;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthFragment extends Fragment {

    String newsApi = "bef70722d1874f65a72dcb962491fc8c";
    ArrayList<NewsModel> newsModelArrayList;
    NewsAdapter newsAdapter;
    String country = "kr";
    private RecyclerView recyclerViewOfHealth;
    private String category = "health";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.healthfragment, null);

        recyclerViewOfHealth = view.findViewById(R.id.recyclerViewHealth);
        newsModelArrayList = new ArrayList<>();
        recyclerViewOfHealth.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new NewsAdapter(getContext(),newsModelArrayList);
        recyclerViewOfHealth.setAdapter(newsAdapter);

        findNews();

        return view;

    }

    private void findNews() {

        NewsUtil.getApiInterface().getCategoryNews(country,category,100,newsApi).enqueue(new Callback<MainNews>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {

                newsModelArrayList.addAll(response.body().getArticles());
                newsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {

            }
        });
    }
}
