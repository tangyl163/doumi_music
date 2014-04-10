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
	private ImageView icon;	//�����б�����Ӧ��ͼƬ
	private TextView name;	//�����б������
	private TextView number;	//�����б��д��еĸ�����
	private ImageView optIcon;	//�����б�Ŀɲ���ͼ��(��ͼ����п���)
	
	public PlayLists(Context context)
	{
		super(context);
		//������ﲻ��ʼ��������new�����������������ӿؼ������ử����
		//��ʼ���Զ���ؼ��б� 
		init(context);
	}	
	
	public PlayLists(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		//��ʼ���Զ���ؼ��б�
		init(context);
	}

	//��ʼ���Զ���ؼ�
	private void init(Context context)
	{
		//װ�������ļ�
		LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View mView = layoutInflater.inflate(R.layout.widget_playlists, this);
		//�󶨿ؼ�
		icon = (ImageView)mView.findViewById(R.id.img_icon_playlists);
		name = (TextView)mView.findViewById(R.id.text_name_playlists);
		number = (TextView)mView.findViewById(R.id.text_number_playlists);
		optIcon = (ImageView)mView.findViewById(R.id.img_opticon_playlists);		
	}
	
	//����ͼƬ
	public void setImageIcon(int resId)
	{
		if (icon != null)
			icon.setImageResource(resId);
	}
	
	//����ͼƬ
	public void setImageOptIcon(int resId)
	{
		if (optIcon != null)
			optIcon.setImageResource(resId);
	}
	
	//���ò����б������
	public void setName(String aName)
	{
		if (name != null)
			name.setText(aName);
	}
	
	//���ò����б��ӵ�еĸ�����
	public void setNumber(String aNum)
	{
		if (number != null)
			number.setText(aNum);
	}
	
	//���������ӿؼ�����ͼ
	public void setView(int resId,String aName,String aNum,int resOptId)
	{
		setImageIcon(resId);
		setName(aName);
		setNumber(aNum);
		setImageOptIcon(resOptId);
	}
}
