package com.mind.loginregisterapps;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MyAdapterUpload extends RecyclerView.Adapter<UpdateViewHolder> {

    private Context mContext;
    private List<UpdateData> myUpdateList;

    private int lastPosition=-1;
    // Constructor

    public MyAdapterUpload (Context mContext, List<UpdateData> myUpdateList) {
        this.mContext = mContext;
        this.myUpdateList = myUpdateList;
    }


    @NonNull
    @Override
    public UpdateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyler_row_item_update,viewGroup,false);
        return new UpdateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UpdateViewHolder updateViewHolder, int i) {

        updateViewHolder.Title.setText(myUpdateList.get(i).getTitle());
        updateViewHolder.Description.setText(myUpdateList.get(i).getDescription());
        updateViewHolder.postdate.setText("Post Date:"+myUpdateList.get(i).getKey());

        updateViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,DetailUpdateActivity.class);
                intent.putExtra("Title",myUpdateList.get(updateViewHolder.getAdapterPosition()).getTitle());
                intent.putExtra("Description",myUpdateList.get(updateViewHolder.getAdapterPosition()).getDescription());
                intent.putExtra("keyValue",myUpdateList.get(updateViewHolder.getAdapterPosition()).getKey());
                mContext.startActivity(intent);
            }
        });


        setAnimation(updateViewHolder.itemView,i);

    }

    // Card Animation Method

    public void setAnimation(View viewToAnimate,int position){

        if (position>lastPosition){

            ScaleAnimation animation = new ScaleAnimation(0.0f,1.0f,0.0f,1.0f,
                    Animation.RELATIVE_TO_SELF,0.5f,
                    Animation.RELATIVE_TO_SELF,0.5f);
            animation.setDuration(1000);
            viewToAnimate.setAnimation(animation);
            lastPosition=position;
        }

    }

    @Override
    public int getItemCount() {
        return myUpdateList.size();
    }

}


 class UpdateViewHolder extends RecyclerView.ViewHolder{

    CardView mCardView;
    TextView Title,Description,postdate;

    public UpdateViewHolder (View itemView){

        super(itemView);

        // Card View

        mCardView=itemView.findViewById(R.id.myCardView);

        // Profile Details

        Title=itemView.findViewById(R.id.tvtitle);
        Description=itemView.findViewById(R.id.tvdescr);
        postdate=itemView.findViewById(R.id.tvpostdate);


    }

}


