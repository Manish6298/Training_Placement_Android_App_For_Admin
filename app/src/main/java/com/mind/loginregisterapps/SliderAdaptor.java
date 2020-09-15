package com.mind.loginregisterapps;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

class SliderAdaptor extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdaptor(Context context)
    {
        this.context =context;
    }

    public int[] slide_image ={
            R.drawable.report3,
            R.drawable.posts,
            R.drawable.notes1
    };

    public String[] slide_heading ={
            "Records",
            "Posts",
            "Notes"
    };

    public String[] slide_desc ={
            "Get All Students Records at a single place in Realtime,Search and Delete Student Records as per the Requirement. ",
            "Create a post and send it to all connected students at a single time with firebase Realtime Database.",
            "Forgot to remmember Something ! No need to worry use our Note, to keep things remmembered."
    };


    @Override
    public int getCount() {
        return slide_heading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        layoutInflater = (LayoutInflater) context.getSystemService( context.LAYOUT_INFLATER_SERVICE );
        View view = layoutInflater.inflate( R.layout.slidelayout, container, false );

        ImageView img = (ImageView) view.findViewById( R.id.imageView );
        TextView heading = (TextView) view.findViewById( R.id.heading );
        TextView desc = (TextView) view.findViewById( R.id.description );

        img.setImageResource( slide_image[position] );
        heading.setText( slide_heading[position] );
        desc.setText( slide_desc[position] );

        container.addView( view );
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container,int position, Object object)
    {
        container.removeView( (RelativeLayout)object );
    }
}
