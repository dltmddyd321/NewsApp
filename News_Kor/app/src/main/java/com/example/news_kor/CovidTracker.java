package com.example.news_kor;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CovidTracker extends AppCompatActivity {

    TextView state, decide, clear, death, care;
    String key = "SRfaVCVtmpXmxrec%2FKI%2B2lVTRevXOkSEwl7COrPvsLlGcFxZp1rkpft3QlkOFRkGJK%2FOG39WItiH9Bu32AXbHA%3D%3D";
    String decideCnt, clearCnt, careCnt, stateDt, deathCnt;

    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.covid_tracker);

        state = findViewById(R.id.stateDt);
        decide = findViewById(R.id.decideCnt);
        clear = findViewById(R.id.clearCnt);
        death = findViewById(R.id.deathCnt);
        care = findViewById(R.id.careCnt);
    }

    public void vOnClick(View view) {

        switch (view.getId()) {
            case R.id.updateBtn:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        decideCnt = getDecideCntData();
                        clearCnt = getClearCntData();
                        careCnt = getCareCntData();
                        stateDt = getStateDtData();
                        deathCnt = getDeathCntData();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                state.setText(stateDt);
                                decide.setText(decideCnt);
                                clear.setText(clearCnt);
                                death.setText(deathCnt);
                                care.setText(careCnt);
                            }
                        });
                    }
                }).start();
                break;
        }
    }

    String getDecideCntData() {
        Date date = new Date();
        String today = simpleDateFormat.format(date);
        StringBuffer buffer = new StringBuffer();

        String queryUrl = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?serviceKey=" + key
                + "&pageNo=1&numOfRows=10&startCreateDt=" + today;

        try {
            URL url = new URL(queryUrl);
            InputStream inputStream = url.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new InputStreamReader(inputStream, "UTF-8"));

            String tag;

            xmlPullParser.next();
            int eventType = xmlPullParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag = xmlPullParser.getName();

                        if (tag.equals("item"));
                        else if (tag.equals("decideCnt")) {
                            buffer.append("확진자 수 : ");
                            xmlPullParser.next();
                            buffer.append(xmlPullParser.getText());
                            buffer.append("명");
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag = xmlPullParser.getName();

                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //buffer.append("파싱 종료\n");
        return buffer.toString();
    }

    String getClearCntData() {
        Date date = new Date();
        String today = simpleDateFormat.format(date);
        StringBuffer buffer = new StringBuffer();

        String queryUrl = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?serviceKey=" + key
                + "&pageNo=1&numOfRows=10&startCreateDt=" + today;

        try {
            URL url = new URL(queryUrl);
            InputStream inputStream = url.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new InputStreamReader(inputStream, "UTF-8"));

            String tag;

            xmlPullParser.next();
            int eventType = xmlPullParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag = xmlPullParser.getName();

                        if (tag.equals("item"));
                        else if (tag.equals("clearCnt")) {
                            buffer.append("완치 수 : ");
                            xmlPullParser.next();
                            buffer.append(xmlPullParser.getText());
                            buffer.append("명");
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag = xmlPullParser.getName();

                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //buffer.append("파싱 종료\n");
        return buffer.toString();
    }

    String getCareCntData() {
        Date date = new Date();
        String today = simpleDateFormat.format(date);
        StringBuffer buffer = new StringBuffer();

        String queryUrl = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?serviceKey=" + key
                + "&pageNo=1&numOfRows=10&startCreateDt=" + today;

        try {
            URL url = new URL(queryUrl);
            InputStream inputStream = url.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new InputStreamReader(inputStream, "UTF-8"));

            String tag;

            xmlPullParser.next();
            int eventType = xmlPullParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag = xmlPullParser.getName();

                        if (tag.equals("item"));
                        else if (tag.equals("careCnt")) {
                            buffer.append("치료 중 : ");
                            xmlPullParser.next();
                            buffer.append(xmlPullParser.getText());
                            buffer.append("명");
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag = xmlPullParser.getName();

                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //buffer.append("파싱 종료\n");
        return buffer.toString();
    }

    String getStateDtData() {
        Date date = new Date();
        String today = simpleDateFormat.format(date);
        StringBuffer buffer = new StringBuffer();

        String queryUrl = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?serviceKey=" + key
                + "&pageNo=1&numOfRows=10&startCreateDt=" + today;

        try {
            URL url = new URL(queryUrl);
            InputStream inputStream = url.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new InputStreamReader(inputStream, "UTF-8"));

            String tag;

            xmlPullParser.next();
            int eventType = xmlPullParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag = xmlPullParser.getName();

                        if (tag.equals("item"));
                        else if (tag.equals("stateDt")) {
                            buffer.append("현재 일자 : ");
                            xmlPullParser.next();
                            buffer.append(xmlPullParser.getText());
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag = xmlPullParser.getName();

                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //buffer.append("파싱 종료\n");
        return buffer.toString();
    }

    String getDeathCntData() {
        Date date = new Date();
        String today = simpleDateFormat.format(date);
        StringBuffer buffer = new StringBuffer();

        String queryUrl = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?serviceKey=" + key
                + "&pageNo=1&numOfRows=10&startCreateDt=" + today;

        try {
            URL url = new URL(queryUrl);
            InputStream inputStream = url.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new InputStreamReader(inputStream, "UTF-8"));

            String tag;

            xmlPullParser.next();
            int eventType = xmlPullParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag = xmlPullParser.getName();

                        if (tag.equals("item"));
                        else if (tag.equals("deathCnt")) {
                            buffer.append("사망자 수 : ");
                            xmlPullParser.next();
                            buffer.append(xmlPullParser.getText());
                            buffer.append("명");
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag = xmlPullParser.getName();

                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //buffer.append("파싱 종료\n");
        return buffer.toString();
    }
}
