package com.tyl.doumi_music.widget;

import com.tyl.doumi_music.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PlayLists extends LinearLayout
{
	private ImageView icon;	//播放列表所对应的图片
	private TextView name;	//播放列表的名称
	private TextView number;	//播放列表中存有的歌曲数
	private ImageView optIcon;	//播放列表的可操作图标(该图标可有可无)
	
	public PlayLists(Context context)
	{
		super(context);
		//如果这里不初始化，采用new方法将不会调用这里，子控件将不会画出来
		//初始化自定义控件列表 
		init(context);
	}	
	
	public PlayLists(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		//初始化自定义控件列表
		init(context);
	}

	//初始化自定义控件
	private void init(Context context)
	{
		//装入配置文件
		LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View mView = layoutInflater.inflate(R.layout.widget_playlists, this);
		//绑定控件
		icon = (ImageView)mView.findViewById(R.id.img_icon_playlists);
		name = (TextView)mView.findViewById(R.id.text_name_playlists);
		number = (TextView)mView.findViewById(R.id.text_number_playlists);
		optIcon = (ImageView)mView.findViewById(R.id.img_opticon_playlists);		
	}
	
	//设置图片
	public void setImageIcon(int resId)
	{
		if (icon != null)
			icon.setImageResource(resId);
	}
	
	//设置图片
	public void setImageOptIcon(int resId)
	{
		if (optIcon != null)
			optIcon.setImageResource(resId);
	}
	
	//设置播放列表的名称
	public void setName(String aName)
	{
		if (name != null)
			name.setText(aName);
	}
	
	//设置播放列表的拥有的歌曲数
	public void setNumber(String aNum)
	{
		if (number != null)
			number.setText(aNum);
	}
	
	//设置所有子控件的视图
	public void setView(int resId,String aName,String aNum,int resOptId)
	{
		setImageIcon(resId);
		setName(aName);
		setNumber(aNum);
		setImageOptIcon(resOptId);
	}
}
