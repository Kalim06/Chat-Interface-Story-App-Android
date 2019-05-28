package com.example.kalimdalwai.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.kalimdalwai.Data.Data;
import com.example.kalimdalwai.DetailActivity;
import com.example.kalimdalwai.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.TitleViewHolder> {

    private Context context;
    private ArrayList<Data> data;

    public TitleAdapter(Context context, ArrayList<Data> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public TitleAdapter.TitleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TitleViewHolder(LayoutInflater.from(context).inflate(R.layout.title_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TitleAdapter.TitleViewHolder titleViewHolder, int i) {

        String imgUrl = data.get(i).getImgUrl();

        Picasso.get().load(imgUrl).fit().into(titleViewHolder.titleImageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class TitleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView titleImageView;
        TitleViewHolder(@NonNull View itemView) {
            super(itemView);
            titleImageView = itemView.findViewById(R.id.titleImageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int position = getLayoutPosition();

            String storyId = String.valueOf(data.get(position).getStoryId());
            String storyName = String.valueOf(data.get(position).getTitle());
            String rightUser = String.valueOf(data.get(position).getRightUser());
            String leftUser = String.valueOf(data.get(position).getLeftUser());

            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("storyId",storyId);
            intent.putExtra("storyName",storyName);
            intent.putExtra("leftUser",leftUser);
            intent.putExtra("rightUser",rightUser);
            context.startActivity(intent);
        }
    }
}
