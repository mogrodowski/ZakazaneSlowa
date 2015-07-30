package pl.mogrodowski.zakazaneslowa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Timer;
import java.util.TimerTask;


public class SplashScreenActivity extends Activity {

    public static final int SPLASH_TIMEOUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    @Override
    protected void onStart(){
        super.onStart();
        this.checkSplash();
    }

    private void checkSplash(){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                proceed();
            }
        }, SplashScreenActivity.SPLASH_TIMEOUT);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            proceed();
        }
        return super.onTouchEvent(event);
    }

    private void proceed(){
        if(this.isFinishing()){
            return;
        }

        startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
        finish();
    }
}
