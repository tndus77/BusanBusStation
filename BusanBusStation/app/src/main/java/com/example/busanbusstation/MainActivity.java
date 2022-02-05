package com.example.busanbusstation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class MainActivity extends AppCompatActivity {

    static RequestQueue requestQueue;
    String TAG = "STATION LIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(
                        Request.Method.GET,
                        "http://apis.data.go.kr/6260000/BusanBIMS/busStopList?serviceKey=fsxe%2BzCe9xRPRjErc4oohWswrtxSmeN2TuN1xMN%2FwOQom2WRwXlTVV8Wc6THUm9KKEtk53ABg72%2BzQeRau1qdw%3D%3D&pageNo=1&numOfRows=10",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                String curr_tag = "";
                                ArrayList<Station> arrayList = new ArrayList<>();
                                Station station = new Station();

                                try {
                                    //xml 파싱
                                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                                    factory.setNamespaceAware(true);
                                    XmlPullParser xpp = factory.newPullParser();

                                    //event type 얻어오기
                                    int eventType = xpp.getEventType();

                                    while (eventType != XmlPullParser.END_DOCUMENT) {
                                        switch (eventType) {
                                            //태그 시작
                                            case XmlPullParser.START_TAG:
                                                //태그의 시작
                                                curr_tag = xpp.getName();
                                                if(xpp.getName().equals("item")){
                                                    station.clear();
                                                }
                                                break;
                                            case XmlPullParser.END_TAG:
                                                //태그의 끝
                                                if(xpp.getName().equals("item")){
                                                    if(station.checkRecvAllData())
                                                        arrayList.add(station);
                                                }
                                                curr_tag = "";
                                            case XmlPullParser.TEXT:
                                                //태그 안의 텍스트
                                                switch(curr_tag)
                                                {
                                                    case "bstopid": station.bstopid  = xpp.getText(); break;
                                                    case "bstopnm": station.bstopnm  = xpp.getText(); break;
                                                    case "arsno":   station.arsno    = xpp.getText(); break;
                                                    case "gpsx":    station.gpsx     = xpp.getText(); break;
                                                    case "gpsy":    station.gpsy     = xpp.getText(); break;
                                                    case "stoptype":station.stoptype = xpp.getText(); break;
                                                }
                                        }
                                        //다음으로 이동
                                        eventType = xpp.next();
                                    }
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(TAG, "onErrorResponse : " + error.toString());
                            }
                        }
                ){
                    //요청 파라미터를 처리하는 메소드
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        //요청 객체가 하나 만들어짐
                        Map<String, String> params = new HashMap<String,String>();

                        //요청 큐에 넣어줌
                        return super.getParams();
                    }
                };
                request.setShouldCache(false);
                requestQueue.add(request);
            }
        });
    }
}