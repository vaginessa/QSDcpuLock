package com.ryuunoakaihitomi.QSDcpuLock;
import java.io.*;
public class cpuCoreLock
{
	public static void set(int number)
	{
		String[] s={
			"setenforce 0",
			"mount -o rw,remount /sys",
			"chmod 0644 /sys/module/msm_thermal/core_control/enabled",
			"echo 1 > /sys/module/msm_thermal/core_control/enabled",
			"chmod 0444 /sys/module/msm_thermal/core_control/enabled",
			"chmod 0644 /sys/module/msm_thermal/core_control/cpus_offlined",
			"echo " + String.valueOf(number) + " > /sys/module/msm_thermal/core_control/cpus_offlined",
			"chmod 0444 /sys/module/msm_thermal/core_control/cpus_offlined"};
		commandExecutor(s);
	}
	public static void setReset(boolean isReboot)
	{
		String[] s={
			"setenforce 0",
			"mount -o rw,remount /sys",
			"chmod 0644 /sys/module/msm_thermal/core_control/enabled",
			"chmod 0644 /sys/module/msm_thermal/core_control/cpus_offlined",
			"echo 0 > /sys/module/msm_thermal/core_control/cpus_offlined",
		};
		if (isReboot)
		{
			s[3] = "reboot";
		}
		commandExecutor(s);
	} 
	private static void commandExecutor(String[] command)
	{
		try
		{
			Process p = Runtime.getRuntime().exec("su");
			DataOutputStream d = new DataOutputStream(p.getOutputStream());
			for (int i=0;i < command.length;i++)
			{
				d.writeBytes(command[i] + "\n");
			}
			d.writeBytes("exit\n");
			d.flush();
			p.getErrorStream().close();
		}
		catch (IOException s)
		{
		}
	}
}
