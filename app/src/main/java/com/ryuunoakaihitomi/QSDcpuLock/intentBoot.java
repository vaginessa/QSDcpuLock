package com.ryuunoakaihitomi.QSDcpuLock;
import android.app.*;
import android.content.*;
import android.os.*;
import android.widget.*;

public class intentBoot extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Intent i=getIntent();
		String s=i.getStringExtra("parameter") ;
		if (s == null)
		{
			s = configControl.control(configControl.READ, "parameter", null);
			Toast.makeText(overallSituationContext.get(), "(外部意图调用_无参)\n执行数值(来自配置)：" + s, Toast.LENGTH_SHORT).show();
		}
		else
		{
			Toast.makeText(overallSituationContext.get(), "(外部意图调用_含参：" + s + ")\n执行数值同参", Toast.LENGTH_SHORT).show();
		}
		cpuCoreLock.set(Integer.valueOf(s));
		finish();
	}
}
