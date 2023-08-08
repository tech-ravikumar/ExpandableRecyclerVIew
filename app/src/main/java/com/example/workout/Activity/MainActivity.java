package com.example.workout.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.workout.Activity.Adaptor.CategoryAdaptor;
import com.example.workout.Activity.Adaptor.SubcategoryAdaptor;
import com.example.workout.Activity.Model.CategoryModel;
import com.example.workout.Activity.Model.SubcategoryModel;
import com.example.workout.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RequestQueue queue;
    RecyclerView recyclerView;
    CategoryAdaptor categoryAdaptor;
    List<CategoryModel>arraylist;
    List<SubcategoryModel>arraylist2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.white));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        recyclerView=findViewById(R.id.categoryRecyclerView);
        recyclerView.setHasFixedSize(true);
        arraylist=new ArrayList<>();
        arraylist2=new ArrayList<>();
        categoryAdaptor = new CategoryAdaptor(this,arraylist,arraylist2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(categoryAdaptor);
        queue = Volley.newRequestQueue(MainActivity.this);
        fetchCategory();

    }
    public void fetchCategory(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url.BASEURL+"category.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("res",response);

                try {
                    Gson gson=new Gson();
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        CategoryModel mm=gson.fromJson(String.valueOf(jsonArray.getJSONObject(i)),CategoryModel.class);
                        arraylist.add(mm);
                    }

                    categoryAdaptor.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        stringRequest.setShouldCache(false);
        queue.add(stringRequest);
    }
}