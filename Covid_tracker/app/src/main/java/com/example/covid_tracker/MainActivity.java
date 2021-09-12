package com.example.covid_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView text;
    String key = "SRfaVCVtmpXmxrec%2FKI%2B2lVTRevXOkSEwl7COrPvsLlGcFxZp1rkpft3QlkOFRkGJK%2FOG39WItiH9Bu32AXbHA%3D%3D";
    String data;
    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text= (TextView)findViewById(R.id.text);
    }

    public void mOnClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        data = getXmlData();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                text.setText(data);
                            }
                        });
                    }
                }).start();
                break;
        }
    }


    String getXmlData() {
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
                        else if (tag.equals("accDefRate")) {
                            buffer.append("accDefRate : ");
                            xmlPullParser.next();
                            buffer.append(xmlPullParser.getText());
                            buffer.append("\n");
                        }

                        else if (tag.equals("accExamCnt")) {
                            buffer.append("accExamCnt : ");
                            xmlPullParser.next();
                            buffer.append(xmlPullParser.getText());
                            buffer.append("\n");
                        }

                        else if (tag.equals("accExamCompCnt")) {
                            buffer.append("accExamCompCnt : ");
                            xmlPullParser.next();
                            buffer.append(xmlPullParser.getText());
                            buffer.append("\n");
                        }

                        else if (tag.equals("careCnt")) {
                            buffer.append("careCnt : ");
                            xmlPullParser.next();
                            buffer.append(xmlPullParser.getText());
                            buffer.append("\n");
                        }

                        else if (tag.equals("clearCnt")) {
                            buffer.append("clearCnt : ");
                            xmlPullParser.next();
                            buffer.append(xmlPullParser.getText());
                            buffer.append("\n");
                        }

                        else if (tag.equals("stateDt")) {
                            buffer.append("stateDt : ");
                            xmlPullParser.next();
                            buffer.append(xmlPullParser.getText());
                            buffer.append("\n");
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag = xmlPullParser.getName();

                        if(tag.equals("item")) buffer.append("\n");
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        buffer.append("파싱 종료\n");
        return buffer.toString();
    }
}