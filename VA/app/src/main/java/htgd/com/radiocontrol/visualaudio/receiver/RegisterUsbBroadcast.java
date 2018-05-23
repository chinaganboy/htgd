package htgd.com.radiocontrol.visualaudio.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import htgd.com.radiocontrol.visualaudio.utils.LogUtils;

public class RegisterUsbBroadcast extends BroadcastReceiver{


    private String Tag="RegisterBroadcast";

    @Override

            public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals("android.hardware.usb.action.USB_STATE")){
                      if (intent.getExtras().getBoolean("connected")){
                              // usb 插入
                          LogUtils.setLog(Tag+"usb get on");
                      }else{
                          //   usb 拔出
                          LogUtils.setLog(Tag+"usb  clear");
                             }
                    }
                String action = intent.getAction();
                if (action.equals(Intent.ACTION_MEDIA_EJECT)) {
                    //USB设备移除，更新UI
                    LogUtils.setLog(Tag+"usb  clear");
                } else if (action.equals(Intent.ACTION_MEDIA_MOUNTED)) {
                    //USB设备挂载，更新UI
                    String usbPath = intent.getDataString();
                    LogUtils.setLog(Tag+"usb get on");

                }
            }
    }



