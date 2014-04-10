package com.tyl.doumi_music.dialog;

import java.security.PublicKey;

import com.tyl.doumi_music.R;

import android.R.bool;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Dialog_PlayList extends Dialog
{
	private Context mContext;
	private static int default_width = 200; //Ĭ�Ͽ��
	private static int default_height = 120; //Ĭ�ϸ߶�
	private EditText mEdit;
	private mOnClickListener mListener;
	private boolean bStatus;	//�жϰ�ť�Ƿ�ȷ��
	public onCustomClickListener onCustomClickListener;
	
	public Dialog_PlayList(Context context,int layout, int style,onCustomClickListener customClickListener)
	{
		this(context, default_width, default_height, layout, style,customClickListener); 
	}
	
	public Dialog_PlayList(Context context, int width, int height,int layout, int style,onCustomClickListener customClickListener)
	{
		 super(context, style);
		 setContentView(layout);
		 
		 mContext = context;
		 bStatus = false;
		 this.onCustomClickListener = customClickListener;
		 mListener = new mOnClickListener();
		 //LayoutInflater lInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 //View view = lInflater.inflate(layout, null);
		 mEdit = (EditText)findViewById(R.id.new_name_playlist);
		 Button lbtn = (Button)findViewById(R.id.btn_pl_ok);
		 if(lbtn!=null)
			 lbtn.setOnClickListener(mListener);
		 lbtn = (Button)findViewById(R.id.btn_pl_cancel);
		 if(lbtn!=null)
			 lbtn.setOnClickListener(mListener);
		 //����dialog�Ŀ�ȴ�С�� ��Ҫdpֵ��ת���ɸ�����Ļ�ֱ����µĴ�С
		 Window window = getWindow();
		 WindowManager.LayoutParams params = window.getAttributes();
		 //��ȡ��Ļ�ܶ�densityֵ  ��ֵ����Ļdpi/160�õ���
		 float density = context.getResources().getDisplayMetrics().density;
		 params.width = (int)(width*density);
		 params.height = (int)(height*density);
		 params.gravity = Gravity.CENTER;
		 window.setAttributes(params);
	}
	
	public String GetName()
	{
		return mEdit.getText().toString();
	}
	
	//ע��ص�����
	public interface onCustomClickListener
	{
		public void back(String name);
	}
	
	public class mOnClickListener implements android.view.View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			// TODO Auto-generated method stub
			switch (v.getId())
			{
			case R.id.btn_pl_ok:
				bStatus = true;
				String lName = GetName();
				if(lName.equals("")||lName==null)
				{
					Toast.makeText(mContext, "�������б�����", Toast.LENGTH_SHORT).show();
					return;
				}
				onCustomClickListener.back(lName);
				dismiss();
				break;
			case R.id.btn_pl_cancel:
				bStatus =false;
				dismiss();
				break;
			default:
				break;
			}
		}
	
	}
}
