package com.fadaks.kingstouch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    getSupportActionBar().hide();

    Button bt_start = (Button) findViewById(R.id.bt_start);
    Button bt_ranking = (Button) findViewById(R.id.bt_ranking);
    Button bt_exit = (Button) findViewById(R.id.bt_EXIT);

    bt_start.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(MainActivity.this,GameActivity.class);
        startActivity(i);
        finish();
      }
    });
    bt_ranking.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });
    bt_exit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });

  }
}
