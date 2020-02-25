package com.gl.ijkplayer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.airbridge.ijkplayerlib.widget.IjkVideoView;

import tv.danmaku.ijk.media.player.IMediaPlayer;

public class MainActivity extends AppCompatActivity {
    IjkVideoView videoview;
    EditText edit;
//    String path = Environment.getExternalStorageDirectory().getPath() + "/a12.flv";
        String path = Environment.getExternalStorageDirectory().getPath() + "/a11.avi";
//            String path = Environment.getExternalStorageDirectory().getPath() + "/a13.avi";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionUtils.getPermiss(this);//获得权限

        videoview = findViewById(R.id.videoview);
         edit = findViewById(R.id.edit);
        edit.setText(path);

       findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              if (PermissionUtils.getPermiss(MainActivity.this)){
                  path = edit.getText().toString();
                  videoview.setVideoPath(path);
                  videoview.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
                      @Override
                      public void onPrepared(IMediaPlayer iMediaPlayer) {
                          videoview.start();
                      }
                  });
              }
           }
       });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!videoview.isPlaying()){
            videoview.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (videoview.isPlaying()){
            videoview.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoview.stopPlayback();

    }
}
