package com.fadaks.kingstouch;

import android.annotation.SuppressLint;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class GameActivity extends AppCompatActivity {

  private boolean mIsPlaying = false;
  private int mScore = 0;
  private float mTime = 10;

  private static final int MSG_SHOWSTART = 0;
  private static final int MSG_SHOWTOUCHTHIS = 1;
  private static final int MSG_UPDATETIME = 2;
  private static final int MSG_ENDGAME = 3;

  private Handler mHandler = new Handler(){
    @Override
    public void handleMessage(Message msg) {
      super.handleMessage(msg);
      switch (msg.what){
        case MSG_SHOWSTART:
          tv_msg.setText("START!!!");
          mHandler.sendEmptyMessageDelayed(MSG_SHOWTOUCHTHIS,3000);
          break;
        case MSG_SHOWTOUCHTHIS:
          tv_msg.setTextColor(0xff33b5e5);
          tv_msg.setText("Touch This!!");
          mIsPlaying = true;
          mHandler.sendEmptyMessage(MSG_UPDATETIME);
          break;
        case MSG_UPDATETIME:
          updateTime();
          if(mTime<=0){
            mHandler.sendEmptyMessage(MSG_ENDGAME);
          }else{
            mHandler.sendEmptyMessageDelayed(MSG_UPDATETIME,10);
          }
          break;
        case MSG_ENDGAME:
          endgame();
          break;
      }
    }
  };

  private TextView tv_msg;
  private TextView tv_score;
  private TextView tv_time;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_game);
    getSupportActionBar().hide();

    tv_msg = (TextView) findViewById(R.id.fullscreen_content);
    tv_score = (TextView) findViewById(R.id.tv_score_text);
    tv_time = (TextView) findViewById(R.id.tv_time);

    ready();
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if(mIsPlaying == false) return false;
    final int action = event.getAction();
    switch (action&MotionEvent.ACTION_MASK){
      case MotionEvent.ACTION_POINTER_DOWN:
      case MotionEvent.ACTION_DOWN:
        mScore++;
        tv_score.setText("SCORE : "+mScore);
        break;
    }
    return false;
  }

  private void ready(){
    init();
    tv_msg.setTextColor(0xffff0000);
    tv_msg.setText("Are you ready?!");
    tv_time.setText(String.format("TIME : %4.2f",mTime) );
    mHandler.sendEmptyMessageDelayed(MSG_SHOWSTART,3000);
  }

  private void init(){
    mIsPlaying = false;
    mScore = 0;
    mTime = 10;
  }
  private void updateTime(){
    mTime -= 0.01f;
    tv_time.setText(String.format("TIME : %4.2f",mTime) );
  }
  private void endgame(){
    mIsPlaying = false;
    tv_msg.setTextColor(0xffff0000);
    tv_msg.setText("GAME OVER!");
  }
}
