package com.tbkt.model_lib.Tools;



import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.tbkt.model_lib.R;
/**
 * @Author: DBJ
 * @Date: 2018/6/8 11:16
  *
 * @Description: 视频回访
 *
 */
public class playback extends Activity{
	VideoView vv;
	MovieRecorderView mRecorderView;
	String path="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.playback);
		   vv = (VideoView) findViewById(R.id.vv2);
		   vv.setMediaController(new MediaController(playback.this));
		 Intent a = getIntent();
		 path=a.getStringExtra("path");
		    play();
		  
	}
	 void play(){
    	  
           vv.
           setVideoURI(Uri.parse(path));
        //   vv.setVideoPath(mRecorderView.getmRecordFile().getAbsolutePath());
         vv.start();
        
    }

}
