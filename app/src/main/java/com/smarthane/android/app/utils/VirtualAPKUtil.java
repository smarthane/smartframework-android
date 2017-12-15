package com.smarthane.android.app.utils;

import android.content.Context;
import android.os.Environment;

import com.didi.virtualapk.PluginManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by smarthane on 2017/12/15.
 */

public class VirtualAPKUtil {

    public static String ASSETS_PLUGIN_PATH = "plugins";
    public static String PLUGIN_PATH = "smarthane_plugins";

    public static void initLoadPlugins(final Context mContext) {
        FileUtil.getInstance(mContext).copyAssetsToSD(ASSETS_PLUGIN_PATH, PLUGIN_PATH).setFileOperateCallback(new FileUtil.FileOperateCallback() {
            @Override
            public void onSuccess() {
                Observable.just(1).subscribeOn(Schedulers.newThread()).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        File file = new File(Environment.getExternalStorageDirectory(), PLUGIN_PATH);
                        if (file.exists() && file.isDirectory() && file.list().length > 0) {
                            for (String fileName:file.list()) {
                                try {
                                    File pluginApk = new File(Environment.getExternalStorageDirectory()+File.separator +PLUGIN_PATH, fileName);
                                    PluginManager.getInstance(mContext).loadPlugin(pluginApk);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });
            }
            @Override
            public void onFailed(String error) {
            }
        });
    }

}
