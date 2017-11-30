package com.smarthane.android.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.didi.virtualapk.PluginManager;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import com.smarthane.android.di.component.DaggerMainComponent;
import com.smarthane.android.di.module.MainModule;
import com.smarthane.android.mvp.contract.MainContract;
import com.smarthane.android.mvp.presenter.MainPresenter;

import com.smarthane.android.R;


import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {


    @BindView(R.id.iv_girle)
    ImageView ivGirle;

    @BindView(R.id.btn_open_plugin)
    Button btnOpenPlugin;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_main; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter.getGirl();
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }
    @Override
    public void setData(String url) {
        Glide.with(this).load(url).into(ivGirle);
    }

    @OnClick({R.id.btn_open_plugin})
    public void onClick(View view){
        if (view.getId() == R.id.btn_open_plugin) {
            boolean load = true;
            if (PluginManager.getInstance(getBaseContext()).getLoadedPlugin("com.smarthane.plugin.one") == null) {
                load = loadPlugin(getBaseContext(),"plugin1.apk");
            }
            if (load) {
                Intent intent = new Intent();
                intent.setClassName("com.smarthane.plugin.one", "com.smarthane.plugin.one.SMainActivity");
                startActivityForResult(intent, 0);
            }
        }
    }

    private boolean loadPlugin(Context base, String plugin) {
        PluginManager pluginManager = PluginManager.getInstance(base);
        File apk = new File(Environment.getExternalStorageDirectory(), plugin);
        if (apk.exists()) {
            try {
                pluginManager.loadPlugin(apk);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "SDcard根目录未检测到"+plugin+"插件", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(getApplicationContext(), "SDcard根目录未检测到"+plugin+"插件", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}
