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

public class HomeFragment extends Fragment {

    String newsApi = "bef70722d1874f65a72dcb962491fc8c";
    ArrayList<NewsModel> newsModelArrayList;
    NewsAdapter newsAdapter;
    String country = "kr";
    private RecyclerView recyclerViewOfHome;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment, null);

        recyclerViewOfHome = view.findViewById(R.id.recyclerViewHome);
        newsModelArrayList = new ArrayList<>();
        recyclerViewOfHome.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new NewsAdapter(getContext(),newsModelArrayList);
        recyclerViewOfHome.setAdapter(newsAdapter);
        
        findNews();

        return view;

    }

    private void findNews() {

        NewsUtil.getApiInterface().getNews(country, 100, newsApi).enqueue(new Callback<MainNews>() {
            //Util을 통해 API 데이터를 호출하고 그에 대한 요청 결과를 반환 받기
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {
                if(response.isSuccessful()){
                    //news 데이터 배열에 반환된 결과의 기사 내용 추가
                    newsModelArrayList.addAll(response.body().getArticles());

                    //데이터 추가에 따른 Adapter 갱신신
                   newsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) { }
        });
    }
}
