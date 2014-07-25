package com.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DispalyMessageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dispaly_message);

		super.onCreate(savedInstanceState);
		/*建活动新实例时系统会调用onCreate()方法*/
		setContentView(R.layout.activity_main);
		/* setContentView()来定义Activity布局，以对Activity进行初始化*/
		
		//从intent得到message com.example.myfirstapp.MESSAGE
		Intent intent = getIntent();
		String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		
		//创建显示的view
		TextView textView = new TextView(this);
		textView.setTextSize(40);
		textView.setText(message);
		
		//把view设置为布局
		setContentView(textView);
	}

	
}
