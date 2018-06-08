package com.tbkt.model_lib.Tools;

import java.io.File;
import java.io.FileNotFoundException;


import org.json.JSONException;
import org.json.JSONObject;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Toast;

import com.tbkt.model_lib.Base.BaseActivity;
import com.tbkt.model_lib.R;
import com.tbkt.model_lib.bean.VideoInfo;

public class VideoRecordActivity extends BaseActivity {

	private Button shortbutton;
	private MovieRecorderView mrecord;
	private File videopath;
	private Toast toast;

	private Context context;

	private String video_path;

	private VideoInfo info;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.video_record);
		context=this;

		mrecord = (MovieRecorderView) findViewById(R.id.movierecorderview);

		shortbutton = (Button) findViewById(R.id.shoot_button);


		requestMyPermission(new String[]{
				Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO});
				//点击拍视频
		shortbutton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					mrecord.record(new MovieRecorderView.OnRecordFinishListener() {

						@Override
						public void onRecordFinish() {
							mrecord.stopRecord();
							mrecord.stop();
							videopath = mrecord.getmRecordFile();
							playback();
							mrecord.setEnabled(true);
							//uploadFile(videopath);

						}
					});
				} else if (event.getAction() == MotionEvent.ACTION_UP) {

					if (mrecord.getTimeCount() > 1) {
						mrecord.stopRecord();
						mrecord.stop();

						videopath = mrecord.getmRecordFile();
						playback();
						mrecord.setEnabled(true);
						uploadFile(videopath);

					} else {
						if (mrecord.getmRecordFile() != null)
							mrecord.getmRecordFile().delete();
						mrecord.stopRecord();
						mrecord.stop();
						Toast.makeText(VideoRecordActivity.this, "视频录制太短", Toast.LENGTH_SHORT)
								.show();
						// mrecord.setEnabled(false);

					}
				}
				return true;
			}
		});
	}

	@Override
	public int setLayoutId() {
		return R.layout.video_record;
	}

	@Override
	public void initView() {

	}

	@Override
	public void initData() {

	}
	//视频回放
	protected void playback() {
		mrecord.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent in = new Intent(VideoRecordActivity.this, playback.class);
				in.putExtra("path", mrecord.getmRecordFile().getAbsolutePath());
				VideoRecordActivity.this.startActivity(in);
			}
		});

	}
	//上传视频
	private void uploadFile(File file) {
		Log.e("dbj",file.toString());

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

	}
}
