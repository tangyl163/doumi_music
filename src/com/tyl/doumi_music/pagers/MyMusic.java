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
 *	�Զ�����ͼ �ҵ�����ҳ�� 
 */
public class MyMusic
{
	protected static final String TAG = "MyMusic";
	private View mView;	//�ҵ����� ��ҳ��
	private LayoutInflater lInflater;
	private Context mContext;
	private LinearLayout mLayout;	//����Ӳ����б�����Ĳ���
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
			
		
		//װ�ؽ���
		lInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mView = lInflater.inflate(R.layout.pg_mymusic, null);
		//�󶨲��ֿؼ�LinearLayout
		mLayout = (LinearLayout)mView.findViewById(R.id.layout_mymusic);
		setPlayLists(R.id.mymusic_local, -1, "��������", 0 + "�׸���",-1);
		setPlayLists(R.id.mymusic_download, -1, "�ҵ�����", 0 + "�׸���",-1);
		setPlayLists(R.id.mymusic_default, -1, "Ĭ���б�", 0 + "�׸���",-1);
		setPlayLists(R.id.mymusic_recently, -1, "�������", 0 + "�׸���",-1);

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
					//addPlayLists("�Զ��岥���б�");
				}
			});
	}

	//��ȡ��ʾҳ��
	public View getView()
	{
		return mView;
	}

	//���ò����б��ͼƬ�����ƣ�ӵ�еĸ�����Ŀ��
	public void setPlayLists(int resID,int resIconID, String name, String number, int resOptIconID)
	{
		PlayLists pLists = (PlayLists)mView.findViewById(resID);
		pLists.setView(resIconID, name, number, resOptIconID);
	}
	
	/*
	 * ����Զ���ؼ� �����б������ļ���
	 */
	public void addPlayLists(String aName)
	{
		if(mLayout==null)
			return;
		PlayLists lPlayLists = new PlayLists(this.mContext);
		lPlayLists.setName(aName);
		lPlayLists.setNumber("0�׸���");
		mLayout.addView(lPlayLists);
		//mLayout.invalidate();
	}
}
