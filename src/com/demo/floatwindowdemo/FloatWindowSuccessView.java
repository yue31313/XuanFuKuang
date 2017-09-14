package com.demo.floatwindowdemo;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * 
 * @author tr
 * @time 2014-2-25
 * @description 发射成功状态类
 */
public class FloatWindowSuccessView extends LinearLayout{
	

	/**
	 * 记录小悬浮窗的宽度
	 */
	public static int viewWidth;

	/**
	 * 记录小悬浮窗的高度
	 */
	public static int viewHeight;
	
	/**发射成功view的参数*/
	private WindowManager.LayoutParams successViewParams;
	
	private WindowManager windowManager;

	
	public FloatWindowSuccessView(Context context) {
		super(context);
		//获取WindowManager对象
		windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		LayoutInflater.from(context).inflate(R.layout.floatwindowsuccess, this);
		View view = findViewById(R.id.floatwindowsuccesslayout);
		
		
		viewWidth = view.getLayoutParams().width;
		viewHeight = view.getLayoutParams().height;
		
		new successViewTask().execute();
		
	}

	/**
	 * 将发射成功的参数传入，用于更新发射成功的位置
	 * @param params 发射成功view的参数
	 */
	public void setParams(WindowManager.LayoutParams params){
		successViewParams = params;
	}
	
	class successViewTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try{
				while(successViewParams.y < 0){
						successViewParams.y += 10;
						System.out.println("y:"+successViewParams.y);
						/**调用publishProgress(Progress... values)来更新进度信息*/
						publishProgress();
						Thread.sleep(20);
				}
				Thread.sleep(1000);
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}

		/**在调用publishProgress(Progress... values)时，此方法被执行，直接将进度信息更新到UI组件上*/
		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			windowManager.updateViewLayout(FloatWindowSuccessView.this, successViewParams);
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			MyWindowManager.removeSuccessWindow(getContext());
		}
		
	}

}
