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

import java.util.List;

public class MyAdapterNotes extends RecyclerView.Adapter<NotesViewHolder>{

    private Context mContext;
    private List<NotesData> myNotesList;

    private int lastPosition=-1;

    public MyAdapterNotes(Context mContext, List<NotesData> myNotesList) {
        this.mContext = mContext;
        this.myNotesList = myNotesList;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_roe_item_notes,viewGroup,false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NotesViewHolder notesViewHolder, int i) {

        notesViewHolder.Title.setText(myNotesList.get(i).getTitle());
        notesViewHolder.Description.setText(myNotesList.get(i).getDescription());
        notesViewHolder.postdate.setText("Post Date:"+myNotesList.get(i).getKey());

        notesViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,NotesDetailActivity.class);
                intent.putExtra("NoteTitle",myNotesList.get(notesViewHolder.getAdapterPosition()).getTitle());
                intent.putExtra("NoteDescription",myNotesList.get(notesViewHolder.getAdapterPosition()).getDescription());
                intent.putExtra("NotekeyValue",myNotesList.get(notesViewHolder.getAdapterPosition()).getKey());
                mContext.startActivity(intent);
            }
        });


        setAnimation(notesViewHolder.itemView,i);

    }

    private void setAnimation(View viewToAnimate, int position) {

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
       return myNotesList.size();
    }
}

class NotesViewHolder extends RecyclerView.ViewHolder{

    CardView mCardView;
    TextView Title,Description,postdate;

    public NotesViewHolder (View itemView){

        super(itemView);

        // Card View

        mCardView=itemView.findViewById(R.id.myCardViewNotes);

        // Profile Details

        Title=itemView.findViewById(R.id.tvtitle);
        Description=itemView.findViewById(R.id.tvdescr);
        postdate=itemView.findViewById(R.id.tvpostdate);


    }

}
