package com.example.kalimdalwai.Adapter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kalimdalwai.Data.Data;
import com.example.kalimdalwai.Fragments.DetailFragment;
import com.example.kalimdalwai.NetworkFragments.NoNetworkDialog;
import com.example.kalimdalwai.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> implements View.OnClickListener {

    private Context context;
    private ArrayList<Data> data;
    private Fragment fragment;


    public MessageAdapter(Context context, ArrayList<Data> data, Fragment fragment) {
        this.context = context;
        this.data = data;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public MessageAdapter.MessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)  {

        return new MessageViewHolder(LayoutInflater.from(context).inflate(R.layout.message_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MessageViewHolder holder, int i) {

        String message = data.get(i).getMessage();

        String user = message.substring(0,3);

        switch (user) {
            case " A:":
                String messageUserA = message.substring(4);

                holder.leftMsgLayout.setVisibility(LinearLayout.VISIBLE);
                holder.textViewLeft.setText(messageUserA);
                holder.rightMsgLayout.setVisibility(LinearLayout.GONE);
                holder.rightImgLayout.setVisibility(LinearLayout.GONE);
                holder.leftImgLayout.setVisibility(LinearLayout.GONE);
                holder.dateView.setVisibility(TextView.GONE);
                break;
            case " B:":
                String messageUserB = message.substring(4);

                holder.rightMsgLayout.setVisibility(LinearLayout.VISIBLE);
                holder.textViewRight.setText(messageUserB);
                holder.leftMsgLayout.setVisibility(LinearLayout.GONE);
                holder.leftImgLayout.setVisibility(LinearLayout.GONE);
                holder.rightImgLayout.setVisibility(LinearLayout.GONE);
                holder.dateView.setVisibility(TextView.GONE);
                break;
            case " AI":
                String imgMessA = message.substring(5);

                holder.leftMsgLayout.setVisibility(LinearLayout.GONE);
                holder.rightMsgLayout.setVisibility(LinearLayout.GONE);
                holder.rightImgLayout.setVisibility(LinearLayout.GONE);
                holder.dateView.setVisibility(TextView.GONE);

                holder.leftImgLayout.setVisibility(LinearLayout.VISIBLE);
                Picasso.get().load(imgMessA).into(holder.leftImageView);
                break;
            case " BI":
                String imgMessB = message.substring(5);

                holder.rightImgLayout.setVisibility(LinearLayout.VISIBLE);
                holder.rightMsgLayout.setVisibility(LinearLayout.GONE);
                holder.leftMsgLayout.setVisibility(LinearLayout.GONE);
                holder.leftImgLayout.setVisibility(LinearLayout.GONE);
                holder.dateView.setVisibility(TextView.GONE);

                Picasso.get().load(imgMessB).into(holder.rightImageView);
                break;
            case " T:":
                String timeMessage = message.substring(4);

                holder.rightMsgLayout.setVisibility(LinearLayout.GONE);
                holder.rightImgLayout.setVisibility(LinearLayout.GONE);
                holder.leftImgLayout.setVisibility(LinearLayout.GONE);
                holder.leftMsgLayout.setVisibility(LinearLayout.GONE);

                holder.dateView.setVisibility(View.VISIBLE);

                holder.dateView.setText(timeMessage);
                break;
            default:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context, "HI ", Toast.LENGTH_SHORT).show();
    }

    class MessageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewLeft,textViewRight,dateView;
        LinearLayout leftMsgLayout,rightMsgLayout,leftImgLayout,rightImgLayout;
        ImageView leftImageView,rightImageView;

        MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            dateView= itemView.findViewById(R.id.dateTextView);
            textViewLeft = itemView.findViewById(R.id.textMessageLeft);
            textViewRight = itemView.findViewById(R.id.textMessageRight);
            leftMsgLayout = itemView.findViewById(R.id.chatLeftMsgLayout);
            rightMsgLayout = itemView.findViewById(R.id.chatRightMessageLayout);
            leftImgLayout = itemView.findViewById(R.id.chatLeftImgLayout);
            rightImgLayout = itemView.findViewById(R.id.chatRightImgLayout);
            leftImageView = itemView.findViewById(R.id.imgMessageLeft);
            rightImageView = itemView.findViewById(R.id.imgMessageRight);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (isNetworkAvailable()) {
                ((DetailFragment) fragment).LoadData();
            }else {
                NoNetworkDialog dialogFragment = new NoNetworkDialog();
                assert fragment.getFragmentManager() != null;
                dialogFragment.show(fragment.getFragmentManager(),"Network Dialog");
            }

        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
