package zyf.tab.test;

import android.app.TabActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class MyTab extends TabActivity implements OnTabChangeListener {

	private TabHost myTabhost;
	protected int myMenuSettingTag=0;
	protected Menu myMenu;
	private static final int myMenuResources[] = { R.menu.a_menu,R.menu.b_menu, R.menu.c_menu};
	//先把在XML中设计好的MENU放到一个int数组里
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myTabhost=this.getTabHost();//从TabActivity上面获取放置Tab的TabHost
		
		/* inflate(int resource, ViewGroup root, boolean attachToRoot) 
         * resource 很显然是一个资源索引id 
		 * 将根据R.layout.main生成的标签View，添加到由myTabhost.getTabContentView()获得的父容器中 
		 * 当attachToRoot为true时，root代表一个可放置于容器中的组件 
         * 当attachToRoot为false时，root仅代表一个存储值的对象 
         * */
		LayoutInflater.from(this).inflate(R.layout.main, myTabhost.getTabContentView(), true);
		//from(this)从这个TabActivity获取LayoutInflater
		//R.layout.main 一个资源索引id 
		//通过TabHost获得存放Tab标签页内容的FrameLayout
		//是否将inflate 拴系到根布局元素上
		myTabhost.setBackgroundColor(Color.argb(150, 22, 70, 150));
		
		myTabhost.addTab(myTabhost
						.newTabSpec("One")// 设置选项卡1
						.setIndicator("A",getResources().getDrawable(R.drawable.gimp))
						// 设置选项卡Title and Icon
						.setContent(R.id.widget_layout_Blue));
						// 设置选项卡布局，在main.xml中定义的标签（Tag）

		myTabhost.addTab(myTabhost
						.newTabSpec("Two")
						.setIndicator("B",getResources().getDrawable(R.drawable.mumule))
						.setContent(R.id.widget_layout_green));

		myTabhost.addTab(myTabhost
						.newTabSpec("Three")
						.setIndicator("C",getResources().getDrawable(R.drawable.notepad))
						.setContent(R.id.widget_layout_red));
		
		myTabhost.setOnTabChangedListener(this);//标签切换事件处理
	}
	
	//通过MenuInflater过滤器动态加入MENU
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		// Hold on to this
		myMenu = menu;
		myMenu.clear();//清空MENU菜单
		// Inflate the currently selected menu XML resource.
		MenuInflater inflater = getMenuInflater();        
        //从TabActivity这里获取一个MENU过滤器
		switch (myMenuSettingTag) {
		case 1:
			inflater.inflate(myMenuResources[0], menu);
            //动态加入数组中对应的XML MENU菜单
			break;
		case 2:
			inflater.inflate(myMenuResources[1], menu);
			break;
		case 3:
			inflater.inflate(myMenuResources[2], menu);
			break;
		default:
			inflater.inflate(myMenuResources[0], menu);
			break;
		}
		return super.onCreateOptionsMenu(menu);
	}
	
	
	//根据标签的切换情况来设置myMenuSettingTag
	@Override
	public void onTabChanged(String tagString) {
		// TODO Auto-generated method stub
		if (tagString.equals("One")) {
			myMenuSettingTag = 1;
		}
		if (tagString.equals("Two")) {
			myMenuSettingTag = 2;
		}
		if (tagString.equals("Three")) {
			myMenuSettingTag = 3;
		}
		if (myMenu != null) {
			onCreateOptionsMenu(myMenu);
		}
	}

}
