package htgd.com.radiocontrol.visualaudio.base;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import htgd.com.radiocontrol.visualaudio.service.TimeService;

/**
 * Created by Administrator on 2016/7/26.
 */
public class MyApplication extends Application {
    private static MyApplication myApplication;


    @Override
    public void onCreate() {
        super.onCreate();
        myApplication= (MyApplication) getApplicationContext();
    }


    public static Context getContext() {
        return myApplication.getApplicationContext();
    }
   /*public static Context getContext( ) {
        if (myApplication == null) {
            myApplication = new MyApplication( );
        }

        return myApplication;
    }*/
}
