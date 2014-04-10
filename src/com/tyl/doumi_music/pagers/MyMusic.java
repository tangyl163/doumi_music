package com.tyl.doumi_music.pagers;

import com.tyl.doumi_music.R;
import com.tyl.doumi_music.dialog.Dialog_PlayList;
import com.tyl.doumi_music.dialog.Dialog_PlayList.onCustomClickListener;
import com.tyl.doumi_music.widget.PlayLists;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

/*
 *	自定义视图 我的音乐页面 
 */
public class MyMusic
{
	protected static final String TAG = "MyMusic";
	private View mView;	//我的音乐 的页面
	private LayoutInflater lInflater;
	private Context mContext;
	private LinearLayout mLayout;	//能添加播放列表组件的布局
	private onCustomClickListener customClickListener;
	
	public MyMusic(Context context)
	{
		mContext = context;
		customClickListener = new onCustomClickListener()
			{
				
				@Override
				public void back(String name)
				{
					// TODO Auto-generated method stub
					addPlayLists(name);
				}
			};
			
		
		//装载界面
		lInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mView = lInflater.inflate(R.layout.pg_mymusic, null);
		//绑定布局控件LinearLayout
		mLayout = (LinearLayout)mView.findViewById(R.id.layout_mymusic);
		setPlayLists(R.id.mymusic_local, -1, "本地音乐", 0 + "首歌曲",-1);
		setPlayLists(R.id.mymusic_download, -1, "我的下载", 0 + "首歌曲",-1);
		setPlayLists(R.id.mymusic_default, -1, "默认列表", 0 + "首歌曲",-1);
		setPlayLists(R.id.mymusic_recently, -1, "最近播放", 0 + "首歌曲",-1);

		Button lBtn = (Button)mView.findViewById(R.id.addplaylist);
		if(lBtn != null)
			lBtn.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View arg0)
				{
					Dialog_PlayList lDialog_PlayList = new Dialog_PlayList(mContext,200,160, R.layout.dialog_playlists, R.style.Theme_dialog,customClickListener);
					lDialog_PlayList.show();
					//addPlayLists(lDialog_PlayList.GetName());
					//addPlayLists("自定义播放列表");
				}
			});
	}

	//获取显示页面
	public View getView()
	{
		return mView;
	}

	//设置播放列表的图片，名称，拥有的歌曲数目等
	public void setPlayLists(int resID,int resIconID, String name, String number, int resOptIconID)
	{
		PlayLists pLists = (PlayLists)mView.findViewById(resID);
		pLists.setView(resIconID, name, number, resOptIconID);
	}
	
	/*
	 * 添加自定义控件 播放列表到布局文件中
	 */
	public void addPlayLists(String aName)
	{
		if(mLayout==null)
			return;
		PlayLists lPlayLists = new PlayLists(this.mContext);
		lPlayLists.setName(aName);
		lPlayLists.setNumber("0首歌曲");
		mLayout.addView(lPlayLists);
		//mLayout.invalidate();
	}
}
