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
 * ������  ����������ҳ�棬�ڸý����У�ֻ������ҳ���ת����������������
 * �������ִ���ܳ����Եú�����
 */

public class HomeActivity extends Activity
{
	private ViewPager mPager;	//ҳ�������
	private List<View> listPages;	//ҳ��洢����list
	private MyMusic mPageMyMusic;	//ҳ��--�ҵ�����
	private MusicLib mPageMusicLib;	//ҳ��--����
	private MusicSearch mPageMusicSearch;	//ҳ��--�Ѹ�
	
	private TextView tMusicMy,tMusiclib,tMusicSearch;	//����ҳ���ǩ
	private int currentIndex = 0;	//��ǰҳ��ı��
	private ImageView imgCursor;	//����ͼƬ(ָʾ��ǰ�����ĸ�ҳ��)
	private int width_imgCursor;	//ͼƬ�Ŀ��
	private int width_offset;	//ͼƬ�ƶ���ƫ����
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		initPageLabel();	//��ʼ��ҳ���ǩ
		initImgCursor();	//��ʼ������ͼƬ
		initViewPager();	//��ʼ��ҳ�������
	}
	
	//��ʼ��ҳ���ǩ
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

	//�趨ҳ���ǩ�ĵ���¼�����
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
	
	//��ʼ������ͼƬ
	private void initImgCursor()
	{
		imgCursor = (ImageView)findViewById(R.id.pg_img_cursor);
		if (imgCursor != null)
		{
			//��ȡͼƬ�Ŀ��
			width_imgCursor = BitmapFactory.decodeResource(getResources(), R.drawable.cursor).getWidth();	
			//��ȡ��Ļ�ֱ��� �Դ˼���ͼƬ��λ��ƫ����
			DisplayMetrics metrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(metrics);
			int width_screen = metrics.widthPixels;
			//����ƫ����	
			width_offset = (width_screen/3 - width_imgCursor)/2;
			/*
			 * ����ͼƬ�ĳ�ʼλ��
			 * Matrix ��һ��3*3�ľ��� ����ͨ��������
			 * ƽ��(translate) ��ת(rotate) ����(scale)����б(skew)�Ȳ���
			 */
			android.graphics.Matrix matrix = new android.graphics.Matrix();
			matrix.postTranslate(width_offset, 0);
			imgCursor.setImageMatrix(matrix);
		}
	}
	
	//��ʼ��ҳ������� ����Ҫ��ҳ����뵽ҳ���������viewpager
	private void initViewPager()
	{
		mPager = (ViewPager)findViewById(R.id.pagemanager);
		if(mPager != null)
		{
			listPages = new ArrayList<View>();
			//����Ҫ��ҳ����ӵ�viewpager��
			mPageMyMusic = new MyMusic(this);
			listPages.add(mPageMyMusic.getView());
			mPageMusicLib = new MusicLib(this);
			listPages.add(mPageMusicLib.getView());
			mPageMusicSearch = new MusicSearch(this);
			listPages.add(mPageMusicSearch.getView());
			//����ҳ��������
			mPager.setAdapter(new AdapterForPage(listPages));
			//����ҳ���л��¼�
			mPager.setOnPageChangeListener(new MyOnPageChangeListener());
		}
	}
	
	/*
	 * PageAdapter��һ�������ֱ���^����Object�������android.support.v4.view.PagerAdapter����ʹ��
	 *	Ҫʹ��PagerAdapter������Ҫ�^��PagerAdapter�Ȼ�����ٸ��w���·���:
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
			//�@���������Ǐ�ViewGroup���Ƴ���ǰView
			container.removeView(views.get(position));	
		}



		@Override
		public Object instantiateItem(ViewGroup container, int position)
		{
			//�@��������returnһ�������@�����������PagerAdapter�m�����x���Ă�������ڮ�ǰ��ViewPager��
			//�����ViewGroup containerӦ���ǵ�ǰui�еĿؼ�����
			container.addView(views.get(position), 0);
			return views.get(position);
		}

		@Override
		public int getCount()
		{
			// �@���������ǻ�ȡ��ǰ���������
			return views.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1)
		{
			// �@���������ڎ����ęn��ԭ����could be implemented as return view == object
			//Ҳ��������Д��Ƿ��Ɍ������ɽ���
			return arg0 == arg1;
		}
		
	}
	
	//ҳ���л��¼�����
	public class MyOnPageChangeListener implements OnPageChangeListener
	{
		private int offset = width_offset*2 + width_imgCursor;	//ƫ����+ָʾͼƬ�Ŀ��
		
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
			//arg0�����ϵͳ���ݻ����ģ�Ӧ���ǵ�ǰҳ��ı��
			// ���ö�����Ч
			Animation animation = new TranslateAnimation(offset*currentIndex, offset*arg0, 0, 0);
			animation.setFillAfter(true); // true:ͼƬͣ�ڶ�������λ��   false:ͼƬ���������λ��
			animation.setDuration(200); //���ö����ĳ���ʱ�� ��λ ����
			imgCursor.setAnimation(animation); //����ͼƬִ�ж���Ч��
			currentIndex = arg0;
		}
	}
}
