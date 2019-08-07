package com.sachin.news_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.sachin.news_app.Pojo.Article;
import com.sachin.news_app.Pojo.MainNews;
import com.sachin.news_app.Pojo.MyPojo;
import com.sachin.news_app.Pojo.Source;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RequestQueue requestQueue;
    String url="https://newsapi.org/v2/everything?q=bitcoin&fro" +
            "m=2019-07-02&sortBy=publishedAt&apiKey=2e7a79b97497485a9f7c5dcfbc16501a";

    List<Article> articleList;
    ArrayList<Source> sourceArrayList=new ArrayList<>();

//Mypojo is only for testing , it is not with this application

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recycleid);

       requestQueue= Volley.newRequestQueue(MainActivity.this);

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject1=new JSONObject(response);

                    JSONArray jsonArray=jsonObject1.getJSONArray("articles");

                   /* for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject2=jsonArray.getJSONObject(1);

                        JSONObject jsonObject3=jsonObject2.getJSONObject("source");

                       // Toast.makeText(MainActivity.this, "name : "+jsonObject3.getString("name"), Toast.LENGTH_SHORT).show();

                       // Toast.makeText(MainActivity.this, "tt  "+jsonObject2.getString("author"), Toast.LENGTH_SHORT).show();

                        Source source=new Gson().fromJson(jsonObject3.toString(),Source.class);

                       // Toast.makeText(MainActivity.this, ""+myPojo.getName(), Toast.LENGTH_SHORT).show();

                        sourceArrayList.add(source);

                    }


                    for (int i=0;i<sourceArrayList.size();i++)
                    {
                        Toast.makeText(MainActivity.this, "" + sourceArrayList.get(i).getName() + sourceArrayList.size(), Toast.LENGTH_SHORT).show();

                    }

*/

                    Gson gson=new Gson();

                    articleList= Arrays.asList(gson.fromJson(jsonArray.toString(),Article[].class));

                   /* for (int i=0;i<articleList.size();i++)
                    {
                        Toast.makeText(MainActivity.this, ""+articleList.get(i).getContent(), Toast.LENGTH_SHORT).show();

                    }
*/
                    News_Adapter news_adapter=new News_Adapter(MainActivity.this,articleList);

                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                    recyclerView.setAdapter(news_adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest);


    }
}
