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

public class TechnologyFragment extends Fragment {

    //데이터를 가져오기 위한 API 선언
    String newsApi = "bef70722d1874f65a72dcb962491fc8c";
    ArrayList<NewsModel> newsModelArrayList;
    NewsAdapter newsAdapter;

    //한국 기사 가져오기
    String country = "kr";
    private RecyclerView recyclerViewOfTech;
    private String category = "technology";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.technologyfragment, null);

        //RecyclerView를 각 Fragment의 View ID에 연결하여 특정 카테고리에 해당하는 화면이 출력
        recyclerViewOfTech = view.findViewById(R.id.recyclerViewTech);
        newsModelArrayList = new ArrayList<>();
        recyclerViewOfTech.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new NewsAdapter(getContext(),newsModelArrayList);
        recyclerViewOfTech.setAdapter(newsAdapter);

        findNews();

        return view;

    }

    private void findNews() {

        NewsUtil.getApiInterface().getCategoryNews(country,category,100,newsApi).enqueue(new Callback<MainNews>() {
            //Util을 통해 API 데이터를 호출하고 그에 대한 요청 결과를 반환 받기
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {

                //응답 완료 시 뉴스 모델 배열에 가져온 기사 내용이 추가
                newsModelArrayList.addAll(response.body().getArticles());

                //데이터가 추가되어 Adapter 갱신
                newsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {

            }
        });
    }
}
