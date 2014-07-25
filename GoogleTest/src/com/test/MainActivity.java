package com.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	/*通常使用应用程序包名作为前缀来定义意图键，方便在其他程序中访问 */
	public final static String EXTRA_MESSAGE = "com.test.MESSAGE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btnSend = (Button) this.findViewById(R.id.send);
		btnSend.setOnClickListener(new SendButtonClickListener());
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		//设置向上按钮
		//minSdkVersion = 11 或以上版本使用这种方式；
		//其他使用getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		// 把app icon 设置成可用的向上按钮
		}
	
	private final class SendButtonClickListener implements View.OnClickListener{
		@Override
		public void onClick(View v) {
		}
	}
	
	/*
	 * 必须满足三个条件：
	 * 1，方法是public;2,返回值是void；
	 * 3，有一个唯一的视图（View）参数（这个视图就是将被点击的视图）
	 */
	public void send2(View v){
		EditText edit = (EditText) this.findViewById(R.id.textEdit);
		String message = edit.getText().toString();
		
		/*此处之所有可以用this是因为当前Activity(MainActivity)是Context的子类*/
		Intent intent = new Intent(this,DispalyMessageActivity.class);
		intent.putExtra(EXTRA_MESSAGE,message);
		startActivity(intent);
	}
	
	/*为 action bar 布局菜单条目，
	 * 是通过在 activity 中实现 onCreateOptionsMenu()) 回调方法
	 * 来 inflate 菜单资源从而获取 Menu 对象。*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//为使用action bar而填充menu items
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.action_search:
			openSearch();
			return true;
		case R.id.action_settings:
			openSettings();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void openSettings() {
		// TODO Auto-generated method stub
		
	}

	private void openSearch() {
		// TODO Auto-generated method stub
		
	}
}
