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

    //현재 날짜를 API 요청을 위해 반환
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
                        //각각의 XML 데이터를 getCoronaData를 통해 파싱
                        decideCnt = getCoronaData("decideCnt","확진자 수 : ","명");
                        stateDt = getCoronaData("stateDt","현재 일자 : ","명");
                        clearCnt = getCoronaData("clearCnt","완치 수 : ","명");
                        deathCnt = getCoronaData("deathCnt", "사망자 수 : ", "명");
                        careCnt = getCoronaData("careCnt", "치료 중 : ", "명");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //TextView를 가져온 API 데이터에 매칭
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

    String getCoronaData(String xml, String value, String unit) {
        //현재 날짜 받기
        Date date = new Date();
        String today = simpleDateFormat.format(date);

        //문자열을 받아오고 붙일 Buffer 생성
        StringBuffer buffer = new StringBuffer();

        //데이터를 요청하는 쿼리 주소 선언 (현재 날짜에 따라 데이터 파싱)
        String queryUrl = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?serviceKey=" + key
                + "&pageNo=1&numOfRows=10&startCreateDt=" + today;

        try {
            //쿼리 주소로 값을 받아오기 위해 URL, Stream 선언
            URL url = new URL(queryUrl);
            InputStream inputStream = url.openStream();

            //Xml 데이터를 Stream을 통해 받아오기
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new InputStreamReader(inputStream, "UTF-8"));

            String tag;

            xmlPullParser.next();
            int eventType = xmlPullParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                //자료의 끝이 아닌 이상 함수 계속 실행
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        //Xml 자료 내용이 존재할 때 파싱 시작
                        buffer.append("파싱 시작\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag = xmlPullParser.getName();

                        //Xml 자료의 Tag 값을 지정하여 내부의 데이터를 파싱
                        if (tag.equals("item"));
                        else if (tag.equals(xml)) {
                            buffer.append(value);
                            xmlPullParser.next();
                            buffer.append(xmlPullParser.getText());
                            buffer.append(unit);
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
        //파싱 종료 후 파싱한 데이터 값을 문자열로 반환
        return buffer.toString();
    }

    /*
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
     */
}
