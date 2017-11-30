package com.smarthane.android.app;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.didi.virtualapk.PluginManager;
import com.jess.arms.base.BaseApplication;
import com.smarthane.android.BuildConfig;
import com.tuzhenlei.crashhandler.CrashHandler;


/**
 * Created by smarthane on 2017/11/22.
 */

public class SmartApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this, BuildConfig.DEBUG);
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        PluginManager.getInstance(base).init();
        MultiDex.install(base);
    }
}
