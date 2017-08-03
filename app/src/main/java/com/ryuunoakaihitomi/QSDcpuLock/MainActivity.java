package com.ryuunoakaihitomi.QSDcpuLock;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.io.DataOutputStream;
import android.content.ActivityNotFoundException;
public class MainActivity extends Activity 
{
	private void saveConfig(boolean letAutorunClose)
	{
		CheckBox cpu0=(CheckBox) findViewById(R.id.mainCheckBox1);
		CheckBox cpu1=(CheckBox) findViewById(R.id.mainCheckBox2);
		CheckBox cpu2=(CheckBox) findViewById(R.id.mainCheckBox3);
		CheckBox cpu3=(CheckBox) findViewById(R.id.mainCheckBox4);
		CheckBox cpu4=(CheckBox) findViewById(R.id.mainCheckBox5);
		CheckBox cpu5=(CheckBox) findViewById(R.id.mainCheckBox6);
		CheckBox cpu6=(CheckBox) findViewById(R.id.mainCheckBox7);
		CheckBox cpu7=(CheckBox) findViewById(R.id.mainCheckBox8);
		TextView para =(TextView) findViewById(R.id.mainTextView4);
		Switch autorun=(Switch) findViewById(R.id.mainSwitch2);
		configControl.control(configControl.WRITE, "cpu0", String.valueOf(cpu0.isChecked()));
		configControl.control(configControl.WRITE, "cpu1", String.valueOf(cpu1.isChecked()));
		configControl.control(configControl.WRITE, "cpu2", String.valueOf(cpu2.isChecked()));
		configControl.control(configControl.WRITE, "cpu3", String.valueOf(cpu3.isChecked()));
		configControl.control(configControl.WRITE, "cpu4", String.valueOf(cpu4.isChecked()));
		configControl.control(configControl.WRITE, "cpu5", String.valueOf(cpu5.isChecked()));
		configControl.control(configControl.WRITE, "cpu6", String.valueOf(cpu6.isChecked()));
		configControl.control(configControl.WRITE, "cpu7", String.valueOf(cpu7.isChecked()));
		configControl.control(configControl.WRITE, "parameter", (String) para.getText());
		if (!letAutorunClose)
		{
			configControl.control(configControl.WRITE, "autorun", String.valueOf(autorun.isChecked()));
		}
		else
		{
			configControl.control(configControl.WRITE, "autorun", "false");
		}
		configControl.control(configControl.SAVE, null, null);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			saveConfig(false);
			Toast.makeText(overallSituationContext.get(), "主活动已经保存配置并退出", Toast.LENGTH_SHORT).show();
		}
		return super.onKeyDown(keyCode, event);
	}
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		if (!isRoot())
		{
			Toast.makeText(overallSituationContext.get(), "检测不到你的root权限，你很有可能无法使用此工具！", Toast.LENGTH_SHORT).show();
		}
		TextView head=(TextView) findViewById(R.id.mainTextView1);
		TextView readme=(TextView) findViewById(R.id.mainTextView2);
		TextView root=(TextView) findViewById(R.id.mainTextView3);
		final TextView para=(TextView) findViewById(R.id.mainTextView4);
		final CheckBox cpu0=(CheckBox) findViewById(R.id.mainCheckBox1);
		final CheckBox cpu1=(CheckBox) findViewById(R.id.mainCheckBox2);
		final CheckBox cpu2=(CheckBox) findViewById(R.id.mainCheckBox3);
		final CheckBox cpu3=(CheckBox) findViewById(R.id.mainCheckBox4);
		final CheckBox cpu4=(CheckBox) findViewById(R.id.mainCheckBox5);
		final CheckBox cpu5=(CheckBox) findViewById(R.id.mainCheckBox6);
		final CheckBox cpu6=(CheckBox) findViewById(R.id.mainCheckBox7);
		final CheckBox cpu7=(CheckBox) findViewById(R.id.mainCheckBox8);
		Button exe=(Button) findViewById(R.id.mainButton1);
		Button reset= (Button) findViewById(R.id.mainButton2);
		final Switch autorun=(Switch) findViewById(R.id.mainSwitch2);
		cpu0.setChecked(Boolean.valueOf(configControl.control(configControl.READ, "cpu0", null)));
		cpu1.setChecked(Boolean.valueOf(configControl.control(configControl.READ, "cpu1", null)));
		cpu2.setChecked(Boolean.valueOf(configControl.control(configControl.READ, "cpu2", null)));
		cpu3.setChecked(Boolean.valueOf(configControl.control(configControl.READ, "cpu3", null)));
		cpu4.setChecked(Boolean.valueOf(configControl.control(configControl.READ, "cpu4", null)));
		cpu5.setChecked(Boolean.valueOf(configControl.control(configControl.READ, "cpu5", null)));
		cpu6.setChecked(Boolean.valueOf(configControl.control(configControl.READ, "cpu6", null)));
		cpu7.setChecked(Boolean.valueOf(configControl.control(configControl.READ, "cpu7", null)));
		autorun.setChecked(Boolean.valueOf(configControl.control(configControl.READ, "autorun", null)));
		para.setText(configControl.control(configControl.READ, "parameter", null));
		cpu0.setVisibility(View.GONE);
		cpu1.setVisibility(View.GONE);
		cpu2.setVisibility(View.GONE);
		cpu3.setVisibility(View.GONE);
		cpu4.setVisibility(View.GONE);
		cpu5.setVisibility(View.GONE);
		cpu6.setVisibility(View.GONE);
		cpu7.setVisibility(View.GONE);
		para.setVisibility(View.GONE);
		int cpuCore=cpuCoreCounter.getResult();
		head.setText((String) head.getText() + "（CPU核心数：" + String.valueOf(cpuCore) + "）");
		if (cpuCore != 0)
		{
			cpuCore--;
			cpu0.setVisibility(View.VISIBLE);
			if (cpuCore != 0)
			{
				cpuCore--;
				cpu1.setVisibility(View.VISIBLE);
				if (cpuCore != 0)
				{
					cpuCore--;
					cpu2.setVisibility(View.VISIBLE);
					if (cpuCore != 0)
					{
						cpuCore--;
						cpu3.setVisibility(View.VISIBLE);
						if (cpuCore != 0)
						{
							cpuCore--;
							cpu4.setVisibility(View.VISIBLE);
							if (cpuCore != 0)
							{
								cpuCore--;
								cpu5.setVisibility(View.VISIBLE);
								if (cpuCore != 0)
								{
									cpuCore--;
									cpu6.setVisibility(View.VISIBLE);
									if (cpuCore != 0)
									{
										cpuCore--;
										cpu7.setVisibility(View.VISIBLE);
									}
								}
							}
						}
					}
				}
			}
		}
		root.setOnLongClickListener(new OnLongClickListener(){
				@Override
				public boolean onLongClick(View p1)
				{
					Toast.makeText(overallSituationContext.get(), "( ゜- ゜)つ🔒 →🐲❤", Toast.LENGTH_SHORT).show();
					Uri link=Uri.parse("http://www.coolapk.com/album/1899819");
					Intent i=new Intent("android.intent.action.VIEW", link);
					startActivity(i);
					return true;
				}
			});
		exe.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1)
				{
					para.setText(String.valueOf(parameterCalculator.get(cpu0.isChecked(), cpu1.isChecked(), cpu2.isChecked(), cpu3.isChecked(), cpu4.isChecked(), cpu5.isChecked(), cpu6.isChecked(), cpu7.isChecked())));
					cpuCoreLock.set(Integer.valueOf((String) para.getText()));
					saveConfig(false);
					Toast.makeText(overallSituationContext.get(), "命令执行完成\n写入数值：" + (String) para.getText(), Toast.LENGTH_SHORT).show();
				}
			});
		reset.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1)
				{
					cpuCoreLock.setReset(false);
					Toast.makeText(overallSituationContext.get(), "恢复完成", Toast.LENGTH_SHORT).show();
				}
			});
		reset.setOnLongClickListener(new OnLongClickListener(){
				@Override
				public boolean onLongClick(View p1)
				{
					AlertDialog.Builder ab=new AlertDialog.Builder(MainActivity.this);
					ab.setTitle("确认操作");
					ab.setMessage("准备强制重启设备并关闭自启动设置。\n\n继续请按确定，取消返回请按退出键或本对话框外。");
					ab.setPositiveButton("确定", new DialogInterface.OnClickListener(){

							@Override
							public void onClick(DialogInterface p1, int p2)
							{
								cpuCoreLock.setReset(true);
								saveConfig(true);
							}
						});
					ab.show();
					return true;
				}
			});
		readme.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1)
				{
					AlertDialog.Builder ab=new AlertDialog.Builder(MainActivity.this);
					ab.setTitle("帮助");
					ab.setMessage(getString(R.string.help) + "\n" + getString(R.string.about));
					ab.setPositiveButton("复制命令", new DialogInterface.OnClickListener(){

							@Override
							public void onClick(DialogInterface p1, int p2)
							{
								ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
								cm.setText("am start -n com.ryuunoakaihitomi.QSDcpuLock/com.ryuunoakaihitomi.QSDcpuLock.intentBoot -e \"parameter\" \"0\"");
								Toast.makeText(getApplicationContext(), "已复制命令，可以将它粘贴在运行外壳的命令中", Toast.LENGTH_SHORT).show();
							}
						});
					ab.setNegativeButton("捐赠", new DialogInterface.OnClickListener(){

							@Override
							public void onClick(DialogInterface p1, int p2)
							{
								try
								{
									startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://QR.ALIPAY.COM/FKX040845ILLL6VFEL7K06")));
								}
								catch (ActivityNotFoundException e)
								{
									Toast.makeText(getApplicationContext(), "你的系统没有安装浏览器或者不兼容此操作", Toast.LENGTH_SHORT).show();
								}
							}
						});
					ab.setOnCancelListener(new DialogInterface.OnCancelListener()
						{
							@Override
							public void onCancel(DialogInterface p1)
							{
								return;
							}
						});
					ab.show();
				}
			});
		autorun.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1)
				{
					Toast.makeText(overallSituationContext.get(), "开机服务状态：" + String.valueOf(autorun.isChecked()) + " 返回键退出保存", Toast.LENGTH_SHORT).show();
				}
			});
	}
	public synchronized boolean isRoot()  
	{  
		Process process = null;  
		DataOutputStream os = null;  
		try  
		{  
			process = Runtime.getRuntime().exec("su");  
			os = new DataOutputStream(process.getOutputStream());  
			os.writeBytes("exit\n");  
			os.flush();  
			int exitValue = process.waitFor();  
			if (exitValue == 0)  
			{  
				return true;  
			}
			else  
			{  
				return false;  
			}  
		}
		catch (Exception e)  
		{  
			return false;  
		}
		finally  
		{  
			try  
			{  
				if (os != null)  
				{  
					os.close();  
				}  
				process.destroy();  
			}
			catch (Exception e)  
			{  
				e.printStackTrace();  
			}  
		}  
	}  
}
