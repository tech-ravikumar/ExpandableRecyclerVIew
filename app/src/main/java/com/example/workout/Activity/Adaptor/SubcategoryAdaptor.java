package com.example.workout.Activity.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.workout.Activity.MainActivity;
import com.example.workout.Activity.Model.SubcategoryModel;
import com.example.workout.Activity.Url;
import com.example.workout.R;

import java.util.List;

public class SubcategoryAdaptor extends RecyclerView.Adapter<SubcategoryAdaptor.MyViewHolder> {
    Context context;
    List<SubcategoryModel>arraylist;
    public SubcategoryAdaptor(Context context,List<SubcategoryModel>arraylist){

        this.arraylist=arraylist;
        this.context=context;
    }


    @NonNull
    @Override
    public SubcategoryAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());


        View photoView
                = inflater
                .inflate(R.layout.list_subcategoryitem,
                        parent, false);


        return new SubcategoryAdaptor.MyViewHolder(photoView);
    }

    @Override
    public void onBindViewHolder(@NonNull SubcategoryAdaptor.MyViewHolder holder, int position) {

        Glide.with(holder.itemView.getContext()).load(Url.BASEURL+"upload/"+arraylist.get(position).getExer_image()).into(holder.image);
        holder.exerciseName.setText(arraylist.get(position).getExer_name());
        holder.exerciseDescription.setText(arraylist.get(position).getExer_description());


    }

    @Override
    public int getItemCount() {
        return arraylist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView exerciseName,exerciseDescription;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.imageView);
            exerciseName=itemView.findViewById(R.id.textView);
            exerciseDescription=itemView.findViewById(R.id.description);
        }
    }
}