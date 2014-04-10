package com.tyl.doumi_music.pagers;

import com.tyl.doumi_music.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class MusicLib 
{
	private View mView;
	private LayoutInflater lInflater;
	private Context mContext;
	
	public MusicLib(Context context)
	{
		mContext = context;
		//×°ÔØ½çÃæ
		lInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mView = lInflater.inflate(R.layout.pg_musiclib, null);
	}
	
	public View getView()
	{
		return mView;
	}	
}
