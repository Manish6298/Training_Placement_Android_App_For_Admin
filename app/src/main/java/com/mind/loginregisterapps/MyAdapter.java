package com.mind.loginregisterapps;

//import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<StudentViewHolder> {

    private Context mContext;
    private List<StudentData> myStudentList;



    // Constructor

    public MyAdapter(Context mContext, List<StudentData> myStudentList) {
        this.mContext = mContext;
        this.myStudentList = myStudentList;
    }


    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View mview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_row_item,viewGroup,false);
        return new  StudentViewHolder(mview);
    }

    @Override
    public void onBindViewHolder(final StudentViewHolder holder, int i) {

        // Profile Data Store in Holder

          holder.sname.setText("Name :"+myStudentList.get(i).getName());
          holder.semail.setText("Email :"+myStudentList.get(i).getEmail());
          holder.senroll.setText("Enrollment No :"+myStudentList.get(i).getEno());
          holder.smob.setText("Mobile No :"+myStudentList.get(i).getMno());
          holder.sadd.setText("Address :"+myStudentList.get(i).getAddress());
          holder.sdob.setText("D.O.B :"+myStudentList.get(i).getDate());
          holder.sgender.setText("Gender :"+myStudentList.get(i).getGender());

        // Qualification Data

        holder.sbranch.setText("Branch :"+myStudentList.get(i).getSbranch());
        holder.syear.setText("Year :"+myStudentList.get(i).getSyear());
        holder.sssc.setText("SSC Marks :"+myStudentList.get(i).getSsc()+"%");
        holder.shsc.setText("HSC Marks :"+myStudentList.get(i).getHsc()+"%");
        holder.sdiploma.setText("Diploma Marks :"+myStudentList.get(i).getDiploma()+"%");
        holder.sfe.setText("FE Marks :"+myStudentList.get(i).getFe()+"SGPA");
        holder.sse.setText("SE Marks :"+myStudentList.get(i).getSe()+"SGPA");
        holder.ste.setText("TE Marks :"+myStudentList.get(i).getTe()+"SGPA");
        holder.sbe.setText("BE Marks :"+myStudentList.get(i).getBe()+"SGPA");
        holder.slback.setText("LIVE Back :"+myStudentList.get(i).getLive());
        holder.sdback.setText("DEAD Back :"+myStudentList.get(i).getDead());


        // Other Skills Data

        holder.shobby.setText("Hobbies :"+myStudentList.get(i).getHobbies());
        holder.sinter.setText("Internship INFO :"+myStudentList.get(i).getInternship());
        holder.sachive.setText("Achivement :"+myStudentList.get(i).getAchieve());

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(mContext,DetailActivity.class);

                // Profile Data
                intent.putExtra("Email",myStudentList.get(holder.getAdapterPosition()).getEmail());
                intent.putExtra("Eno",myStudentList.get(holder.getAdapterPosition()).getEno());
                intent.putExtra("Mno",myStudentList.get(holder.getAdapterPosition()).getMno());
                intent.putExtra("Name",myStudentList.get(holder.getAdapterPosition()).getName());
                intent.putExtra("Gender",myStudentList.get(holder.getAdapterPosition()).getGender());
                intent.putExtra("Date",myStudentList.get(holder.getAdapterPosition()).getDate());
                intent.putExtra("Address",myStudentList.get(holder.getAdapterPosition()).getAddress());


                // Qualification Data

                intent.putExtra("Ssc",myStudentList.get(holder.getAdapterPosition()).getSsc());
                intent.putExtra("Hsc",myStudentList.get(holder.getAdapterPosition()).getHsc());
                intent.putExtra("Diploma",myStudentList.get(holder.getAdapterPosition()).getDiploma());

                intent.putExtra("FE",myStudentList.get(holder.getAdapterPosition()).getFe());
                intent.putExtra("SE",myStudentList.get(holder.getAdapterPosition()).getSe());
                intent.putExtra("BE",myStudentList.get(holder.getAdapterPosition()).getBe());
                intent.putExtra("TE",myStudentList.get(holder.getAdapterPosition()).getTe());
                intent.putExtra("Year",myStudentList.get(holder.getAdapterPosition()).getSyear());
                intent.putExtra("Branch",myStudentList.get(holder.getAdapterPosition()).getSbranch());
                intent.putExtra("Live",myStudentList.get(holder.getAdapterPosition()).getLive());
                intent.putExtra("Dead",myStudentList.get(holder.getAdapterPosition()).getDead());


                // Skills Data
                intent.putExtra("Hobbies",myStudentList.get(holder.getAdapterPosition()).getHobbies());
                intent.putExtra("Internship",myStudentList.get(holder.getAdapterPosition()).getInternship());
                intent.putExtra("Achivement",myStudentList.get(holder.getAdapterPosition()).getAchieve());

                // Student Key

                intent.putExtra("StudentKey",myStudentList.get(holder.getAdapterPosition()).getKey());

                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return myStudentList.size();
    }

}

class StudentViewHolder extends RecyclerView.ViewHolder {

    CardView mCardView;
    TextView sname,semail,senroll,smob,sadd,sdob,sgender,syear,sbranch,sssc,shsc,sdiploma,sfe,sse,ste,sbe,slback,sdback,shobby,sachive,sinter;

    public  StudentViewHolder (View itemView) {

        super(itemView);

        // Card View

        mCardView=itemView.findViewById(R.id.myCardView);

        // Profile Details

         sname=itemView.findViewById(R.id.tvname);
         semail=itemView.findViewById(R.id.tvemail);
         senroll=itemView.findViewById(R.id.tvenroll);
         smob=itemView.findViewById(R.id.tvmobile);
         sadd=itemView.findViewById(R.id.tvaddress);
         sdob=itemView.findViewById(R.id.tvdob);
         sgender=itemView.findViewById(R.id.gender);

        // Qualification Details

        syear=itemView.findViewById(R.id.tvyear);
        sbranch=itemView.findViewById(R.id.tvbranch);
        sssc=itemView.findViewById(R.id.tvssc);
        shsc=itemView.findViewById(R.id.tvhsc);
        sdiploma=itemView.findViewById(R.id.tvdiploma);
        sfe=itemView.findViewById(R.id.tvfe);
        sse=itemView.findViewById(R.id.tvse);
        ste=itemView.findViewById(R.id.tvte);
        sbe=itemView.findViewById(R.id.tvbe);
        slback=itemView.findViewById(R.id.tvlive);
        sdback=itemView.findViewById(R.id.tvdead);

        // Skills Details

        shobby=itemView.findViewById(R.id.tvhobbies);
        sinter=itemView.findViewById(R.id.tvinternship);
        sachive=itemView.findViewById(R.id.tvachieve);


    }

}


