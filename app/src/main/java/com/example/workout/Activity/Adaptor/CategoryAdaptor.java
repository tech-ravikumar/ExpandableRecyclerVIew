package com.example.workout.Activity.Adaptor;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.workout.Activity.Click;
import com.example.workout.Activity.MainActivity;
import com.example.workout.Activity.Model.CategoryModel;
import com.example.workout.Activity.Model.SubcategoryModel;
import com.example.workout.Activity.Url;
import com.example.workout.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdaptor extends RecyclerView.Adapter<CategoryAdaptor.MyViewHolder> {

    Context context;
    List<CategoryModel> arraylist;
    RequestQueue queue;
    SubcategoryAdaptor subcategoryAdaptor;
    Click click;
    List<SubcategoryModel>arraylist2;

public CategoryAdaptor(Context context,List<CategoryModel>arraylist,List<SubcategoryModel>arraylist2){
    this.context=context;
    this.arraylist=arraylist;
    this.arraylist2=arraylist2;
}
    @NonNull
    @Override
    public CategoryAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());


        View photoView
                = inflater
                .inflate(R.layout.list_categoryitem,
                        parent, false);


        return new CategoryAdaptor.MyViewHolder(photoView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdaptor.MyViewHolder holder, int position) {
        holder.bodyName.setText(arraylist.get(position).getRoutine_name());

        String aa=arraylist.get(position).getRoutine_id();
        queue = Volley.newRequestQueue(context);

        holder.arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subcategoryAdaptor = new SubcategoryAdaptor(context,arraylist2);
                LinearLayoutManager layoutManager = new LinearLayoutManager(holder.itemView.getContext());
                holder.subRecyclerView.setHasFixedSize(true);
                holder.subRecyclerView.setLayoutManager(layoutManager);
                holder.subRecyclerView.setAdapter(subcategoryAdaptor);
                fetchSubcategory(aa);
                holder.arrowUp.setVisibility(View.VISIBLE);
                holder.arrow.setVisibility(View.GONE);
                holder.subRecyclerView.setVisibility(View.VISIBLE);
            }
        });

        holder.arrowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.subRecyclerView.setVisibility(View.GONE);
                holder.arrow.setVisibility(View.VISIBLE);
                holder.arrowUp.setVisibility(View.GONE);
            }
        });
    }

    public void fetchSubcategory(String search){
        ProgressDialog progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        arraylist2.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url.BASEURL+"showsubcategories.php?rid="+search, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("searchresponse",response);
                progressDialog.dismiss();
                try {
                    Gson gson = new Gson();
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length();i++) {
                        SubcategoryModel mm = gson.fromJson(String.valueOf(jsonArray.getJSONObject(i)), SubcategoryModel.class);
                        arraylist2.add(mm);
                    }
                    subcategoryAdaptor.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
            }
        });
        stringRequest.setShouldCache(false);
        queue.add(stringRequest);
    }

    @Override
    public int getItemCount() {
        return arraylist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView bodyName;
    ImageView arrow,arrowUp;

    RecyclerView subRecyclerView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bodyName=itemView.findViewById(R.id.textView3);
            arrow=itemView.findViewById(R.id.money);
            arrowUp=itemView.findViewById(R.id.up);
            subRecyclerView=itemView.findViewById(R.id.subcategoryRecyclerView);
        }
    }
    public void setonItemClick(Click click) {
        this.click = click;
    }
}
