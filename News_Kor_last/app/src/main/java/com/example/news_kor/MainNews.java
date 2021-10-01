package com.example.news_kor;

import java.util.ArrayList;

public class MainNews {

    //상태 값 변수
    private String status;

    //결과 값 변수
    private String totalResults;

    //뉴스 기사 데이터 모델을 담을 배열
    private ArrayList<NewsModel> articles;

    public MainNews(String status, String totalResults, ArrayList<NewsModel> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<NewsModel> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<NewsModel> articles) {
        this.articles = articles;
    }
}
