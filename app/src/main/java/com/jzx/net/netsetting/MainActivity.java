package com.jzx.net.netsetting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jzx.net.netsetting.receiver.NetChangeReceiver;
import com.jzx.net.netsetting.util.IpUtil;
import com.jzx.net.netsetting.util.NetWorkType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }

    private void initData() {
        Observable.just("one")
                .map(new Function<String, NetWorkType>() {
                    @Override
                    public NetWorkType apply(String s) throws Exception {
                        NetWorkType netWorkType = IpUtil.getNetWorkType();
                        return netWorkType;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NetWorkType>() {
                            @Override
                            public void accept(NetWorkType s) throws Exception {
                                tvContent.setText("ip:" + s.getIp() + "\n" +
                                        "type:" + s.getType());
                            }
                });
    }

    private void initView() {
        tvContent = findViewById(R.id.tv_content);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String event) {
        if (NetChangeReceiver.NET_CHANGE.equals(event)) {
            initData();
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


}
