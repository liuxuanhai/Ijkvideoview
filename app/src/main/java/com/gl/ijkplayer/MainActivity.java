package com.gl.ijkplayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import com.airbridge.ijkplayerlib.widget.IjkVideoView;

public class MainActivity extends AppCompatActivity {
    IjkVideoView videoview;
    EditText edit;
//        String path = "android.resource://" + getPackageName() + "/" + R.raw.a11;//放在程序内部 raw文件夹
//        String path = "http://ivi.bupt.edu.cn/hls/cctv2.m3u8";//直播链接
//        String path = "http://mp4.vjshi.com/2013-05-28/2013052815051372.mp4";//网络获取视频资源
//        String path = Environment.getExternalStorageDirectory().getPath() + "/a12.flv";//存放在手机存储卡根目录下
          String path = Environment.getExternalStorageDirectory().getPath() + "/a11.avi";//存放在手机存储卡根目录下

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
                  videoview.setVideoURI(Uri.parse(path));
                  videoview.start();
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
