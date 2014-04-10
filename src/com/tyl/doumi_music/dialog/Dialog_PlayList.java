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
	private static int default_width = 200; //默认宽度
	private static int default_height = 120; //默认高度
	private EditText mEdit;
	private mOnClickListener mListener;
	private boolean bStatus;	//判断按钮是否确认
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
		 //设置dialog的宽度大小等 需要dp值，转换成各种屏幕分辨率下的大小
		 Window window = getWindow();
		 WindowManager.LayoutParams params = window.getAttributes();
		 //获取屏幕密度density值  该值是屏幕dpi/160得到的
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
	
	//注册回调函数
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
					Toast.makeText(mContext, "请输入列表名称", Toast.LENGTH_SHORT).show();
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
