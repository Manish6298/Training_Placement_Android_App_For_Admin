package com.mind.loginregisterapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    ImageView iv1,iv2,iv3,iv4;
    Animation a,b,c,d,e,f;
    TextView tv1,tv2,tv3,tv4;
    Button btn;
    Timer timer;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        firebaseAuth = FirebaseAuth.getInstance();

        a = AnimationUtils.loadAnimation( getApplicationContext(), R.anim.anim);
        iv1 = (ImageView) findViewById( R.id.logo);

        b = AnimationUtils.loadAnimation( getApplicationContext(), R.anim.circle1 );
        iv2 = (ImageView) findViewById( R.id.circle1 );

        c = AnimationUtils.loadAnimation( getApplicationContext(), R.anim.circle2 );
        iv3 = (ImageView) findViewById( R.id.circle2 );

        d = AnimationUtils.loadAnimation( getApplicationContext(), R.anim.circle3 );
        iv4 = (ImageView) findViewById( R.id.circle3 );

        e = AnimationUtils.loadAnimation( getApplicationContext(), R.anim.text );
        tv1 = (TextView) findViewById( R.id.tplogo );

        f = AnimationUtils.loadAnimation( getApplicationContext(), R.anim.welcome );
        tv2 = (TextView) findViewById( R.id.welcome);
        tv3 = (TextView) findViewById( R.id.to);
        tv4 = (TextView) findViewById( R.id.tp );

        iv1.startAnimation( a );
        iv2.startAnimation( b );
        iv3.startAnimation( c );
        iv4.startAnimation( d );
        tv1.startAnimation( e );
        tv2.startAnimation( f );
        tv3.startAnimation( f );
        tv4.startAnimation( f );


        btn = (Button)findViewById(R.id.splash);
        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(SplashActivity.this, IntroActivity.class));
            }
        } );


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        updateUI( currentUser );
    }

    private void updateUI(FirebaseUser currentUser) {

        if (currentUser != null) {
            btn.setVisibility( View.INVISIBLE );
            tv2.setAlpha( 0.0f );
            tv3.setAlpha( 0.0f );
            tv4.setAlpha( 0.0f );
            timer = new Timer(  );
            timer.schedule( new TimerTask() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
            }, 5000);
        } else {

        }
    }
}
