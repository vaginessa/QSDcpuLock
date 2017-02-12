package com.ryuunoakaihitomi.QSDcpuLock;
import android.app.*;
import android.content.*;
public class serviceNotificationLayout
{
	public static void set(NotificationManager nm)
	{
		Context c=overallSituationContext.get();
		Notification n = new Notification(R.drawable.ic_launcher, "核心锁启动...", System.currentTimeMillis());
		Intent i = new Intent();
		i.setClass(c, MainActivity.class);
		PendingIntent p = PendingIntent.getActivity(c, 0, i, 0);
		n.setLatestEventInfo(c, "高通骁龙CPU锁核心方案开机自启", "设置完成", p);
		n.flags = Notification.FLAG_AUTO_CANCEL;
		n.defaults = Notification.DEFAULT_ALL;
		nm.notify(10, n);
	}
}
