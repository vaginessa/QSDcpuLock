package com.ryuunoakaihitomi.QSDcpuLock;
import android.app.*;
import android.os.*;
import android.widget.*;
import java.util.*;
public class AutoActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.auto);
		if (Boolean.valueOf(configControl.control(configControl.READ, "autorun", null)))
		{
			Toast.makeText(overallSituationContext.get(), "高通锁核已经随开机自启,10秒后执行锁核...", Toast.LENGTH_LONG).show();
			TimerTask task = new TimerTask(){
				public void run()
				{
					serviceNotificationLayout.set((NotificationManager) getSystemService(NOTIFICATION_SERVICE));
					cpuCoreLock.set(Integer.valueOf(configControl.control(configControl.READ, "parameter", null)));
				}
			};
			Timer timer = new Timer();
			timer.schedule(task, 10000);
		}
		finish();
	}
}
