package com.ryuunoakaihitomi.QSDcpuLock;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
public class serviceNotificationLayout
{
	public static void set(NotificationManager nm)
	{
		Context c=overallSituationContext.get();
		Intent i = new Intent();
		i.setClass(c, MainActivity.class);
		PendingIntent p = PendingIntent.getActivity(c, 0, i, 0);
		Notification n =new Notification.Builder(c)
			.setSmallIcon(R.drawable.ic_launcher)
			.setContentTitle("高通骁龙CPU锁核心方案开机自启")
			.setContentText("设置完成")
			.setContentIntent(p)
			.build();
		n.flags = Notification.FLAG_AUTO_CANCEL;
		n.defaults = Notification.DEFAULT_ALL;
		nm.notify("QSDcpuLock", 1, n);
	}
}
