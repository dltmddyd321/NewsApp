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

    //데이터를 가져오기 위한 API 선언
    String newsApi = "News API 인증키";
    ArrayList<NewsModel> newsModelArrayList;
    NewsAdapter newsAdapter;

    //한국 기사 가져오기
    String country = "kr";
    private RecyclerView recyclerViewOfHealth;
    private String category = "health";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.healthfragment, null);

        //RecyclerView를 각 Fragment의 View ID에 연결하여 특정 카테고리에 해당하는 화면이 출력
        recyclerViewOfHealth = view.findViewById(R.id.recyclerViewHealth);
        newsModelArrayList = new ArrayList<>();
        recyclerViewOfHealth.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new NewsAdapter(getContext(),newsModelArrayList);
        recyclerViewOfHealth.setAdapter(newsAdapter);

        findNews();

        return view;

    }

    private void findNews() {
        //Util을 통해 newsAPI의 한국 기사 데이터를 가져오기
        NewsUtil.getApiInterface().getCategoryNews(country,category,100,newsApi).enqueue(new Callback<MainNews>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {
                //응답 완료 시 뉴스 모델 배열에 가져온 기사 내용이 추가
                newsModelArrayList.addAll(response.body().getArticles());

                //데이터가 추가되어 Adapter 갱신
                newsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) { }
        });
    }
}
