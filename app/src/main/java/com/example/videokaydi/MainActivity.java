package com.example.videokaydi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.provider.MediaStore;
import android.view.View;
import android.widget.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;


public class MainActivity extends AppCompatActivity {
    private  static int VIDEO_REQUEST = 101;
   private Uri videoUri = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void captureVideo(View view) {
        // Video kaydı başlat
        Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if(videoIntent.resolveActivity(getPackageManager()) !=null){
              startActivityForResult(videoIntent,VIDEO_REQUEST);
        }
    }

    public void playVideo(View view) {
        // video oynat

        Intent playIntent = new Intent(this,VideoPlayActivity.class);
        playIntent.putExtra("videoUri", videoUri.toString()); // Bu veriyi diğer sayfaya taşır
        startActivity(playIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == VIDEO_REQUEST && resultCode == RESULT_OK){
            videoUri = data.getData();
        }
        //super.onActivityResult(requestCode, resultCode, data);
    }
}