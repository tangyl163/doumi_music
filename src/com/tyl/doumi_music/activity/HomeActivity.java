package com.tyl.doumi_music.activity;

import java.util.ArrayList;
import java.util.List;

import com.tyl.doumi_music.R;
import com.tyl.doumi_music.pagers.MusicLib;
import com.tyl.doumi_music.pagers.MyMusic;
import com.tyl.doumi_music.pagers.MusicSearch;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

/*
 * 主界面  主管三个分页面，在该界面中，只负责处理页面的转换，不做其他操作
 * 否则会出现代码很长，显得很冗余
 */

public class HomeActivity extends Activity
{
	private ViewPager mPager;	//页面管理器
	private List<View> listPages;	//页面存储容器list
	private MyMusic mPageMyMusic;	//页面--我的音乐
	private MusicLib mPageMusicLib;	//页面--曲库
	private MusicSearch mPageMusicSearch;	//页面--搜歌
	
	private TextView tMusicMy,tMusiclib,tMusicSearch;	//三个页面标签
	private int currentIndex = 0;	//当前页面的编号
	private ImageView imgCursor;	//动画图片(指示当前处于哪个页面)
	private int width_imgCursor;	//图片的宽度
	private int width_offset;	//图片移动的偏移量
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		initPageLabel();	//初始化页面标签
		initImgCursor();	//初始化动画图片
		initViewPager();	//初始化页面管理器
	}
	
	//初始化页面标签
	private void initPageLabel()
	{
		tMusicMy = (TextView)findViewById(R.id.pg_lb_musicmy);
		tMusiclib = (TextView)findViewById(R.id.pg_lb_musiclib);
		tMusicSearch = (TextView)findViewById(R.id.pg_lb_musicsearch);
		if(tMusicMy!=null)
			tMusicMy.setOnClickListener(new mOnClicklistener(0));		
		if(tMusiclib!=null)
			tMusiclib.setOnClickListener(new mOnClicklistener(1));	
		if(tMusicSearch!=null)
			tMusicSearch.setOnClickListener(new mOnClicklistener(2));		
	}

	//设定页面标签的点击事件处理
	private class mOnClicklistener implements OnClickListener
	{
		private int mIndex;
		public mOnClicklistener(int index)
		{
			mIndex = index;
		}
		
		@Override
		public void onClick(View v)
		{
			mPager.setCurrentItem(this.mIndex);
		}
	}
	
	//初始化动画图片
	private void initImgCursor()
	{
		imgCursor = (ImageView)findViewById(R.id.pg_img_cursor);
		if (imgCursor != null)
		{
			//获取图片的宽度
			width_imgCursor = BitmapFactory.decodeResource(getResources(), R.drawable.cursor).getWidth();	
			//获取屏幕分辨率 以此计算图片的位移偏移量
			DisplayMetrics metrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(metrics);
			int width_screen = metrics.widthPixels;
			//计算偏移量	
			width_offset = (width_screen/3 - width_imgCursor)/2;
			/*
			 * 设置图片的初始位置
			 * Matrix 是一个3*3的矩阵 可以通过它进行
			 * 平移(translate) 旋转(rotate) 缩放(scale)和倾斜(skew)等操作
			 */
			android.graphics.Matrix matrix = new android.graphics.Matrix();
			matrix.postTranslate(width_offset, 0);
			imgCursor.setImageMatrix(matrix);
		}
	}
	
	//初始化页面管理器 把需要的页面加入到页面管理器中viewpager
	private void initViewPager()
	{
		mPager = (ViewPager)findViewById(R.id.pagemanager);
		if(mPager != null)
		{
			listPages = new ArrayList<View>();
			//将需要的页面添加到viewpager中
			mPageMyMusic = new MyMusic(this);
			listPages.add(mPageMyMusic.getView());
			mPageMusicLib = new MusicLib(this);
			listPages.add(mPageMusicLib.getView());
			mPageMusicSearch = new MusicSearch(this);
			listPages.add(mPageMusicSearch.getView());
			//设置页面适配器
			mPager.setAdapter(new AdapterForPage(listPages));
			//设置页面切换事件
			mPager.setOnPageChangeListener(new MyOnPageChangeListener());
		}
	}
	
	/*
	 * PageAdapter是一個抽象類，直接繼承于Object，导入包android.support.v4.view.PagerAdapter即可使用
	 *	要使用PagerAdapter，首先要繼承PagerAdapter類，然後至少覆蓋以下方法:
	 * 		instantiateItem(ViewGroup, int);	 
	 * 		destroyItem(ViewGroup, int, Object);	
	 * 		getCount();		
	 * 		isViewFromObject(View, Object);  
	 */
	public class AdapterForPage extends PagerAdapter
	{
		private List<View> views;
		
		public AdapterForPage(List<View> views)
		{
			this.views =  views;
		}
		
		

		@Override
		public void destroyItem(ViewGroup container, int position, Object object)
		{
			//這個方法，是從ViewGroup中移出當前View
			container.removeView(views.get(position));	
		}



		@Override
		public Object instantiateItem(ViewGroup container, int position)
		{
			//這個方法，return一個對象，這個對象表明了PagerAdapter適配器選擇哪個對象放在當前的ViewPager中
			//这里的ViewGroup container应该是当前ui中的控件容器
			container.addView(views.get(position), 0);
			return views.get(position);
		}

		@Override
		public int getCount()
		{
			// 這個方法，是获取当前窗体界面数
			return views.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1)
		{
			// 這個方法，在幫助文檔中原文是could be implemented as return view == object
			//也就是用於判斷是否由對象生成界面
			return arg0 == arg1;
		}
		
	}
	
	//页面切换事件监听
	public class MyOnPageChangeListener implements OnPageChangeListener
	{
		private int offset = width_offset*2 + width_imgCursor;	//偏移量+指示图片的宽度
		
		@Override
		public void onPageScrollStateChanged(int arg0)
		{
			// TODO Auto-generated method stub
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2)
		{
			// TODO Auto-generated method stub
		}

		@Override
		public void onPageSelected(int arg0)
		{
			//arg0这个是系统传递回来的，应该是当前页面的编号
			// 设置动画特效
			Animation animation = new TranslateAnimation(offset*currentIndex, offset*arg0, 0, 0);
			animation.setFillAfter(true); // true:图片停在动画结束位置   false:图片返回最初的位置
			animation.setDuration(200); //设置动画的持续时间 单位 毫秒
			imgCursor.setAnimation(animation); //动画图片执行动画效果
			currentIndex = arg0;
		}
	}
}
