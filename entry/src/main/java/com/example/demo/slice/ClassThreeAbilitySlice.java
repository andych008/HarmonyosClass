package com.example.demo.slice;

import com.example.demo.class3.BusinessApiManager;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.DirectionalLayout.LayoutConfig;
import ohos.agp.components.Text;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.utils.Color;
import ohos.agp.utils.TextAlignment;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassThreeAbilitySlice extends AbilitySlice {

    private DirectionalLayout myLayout = new DirectionalLayout(this);

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        LayoutConfig config = new LayoutConfig(LayoutConfig.MATCH_PARENT, LayoutConfig.MATCH_PARENT);
        myLayout.setLayoutConfig(config);
        ShapeElement element = new ShapeElement();
        element.setRgbColor(new RgbColor(255, 255, 255));
        myLayout.setBackground(element);

        Text text = new Text(this);
        text.setLayoutConfig(config);
        text.setText("点我访问网络");
        text.setTextColor(new Color(0xFF000000));
        text.setTextSize(50);
        text.setTextAlignment(TextAlignment.CENTER);
        myLayout.addComponent(text);
        super.setUIContent(myLayout);

        text.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {

                getDataRx();
            }
        });
    }
    private void getDataRx(){

        BusinessApiManager.get()
                .getHtmlContentRx("https://www.baidu.com")
        .subscribeOn(Schedulers.io())
        .subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                HiLog.warn(new HiLogLabel(HiLog.LOG_APP, 0, "===demo==="), Thread.currentThread().getName()+"网页返回结果disposable rx：");

            }

            @Override
            public void onNext(String result) {
                HiLog.warn(new HiLogLabel(HiLog.LOG_APP, 0, "===demo==="), Thread.currentThread().getName()+"网页返回结果rx："+result);

            }

            @Override
            public void onError(Throwable throwable) {
                HiLog.warn(new HiLogLabel(HiLog.LOG_APP, 0, "===demo==="), Thread.currentThread().getName()+"onError rx："+HiLog.getStackTrace(throwable));

            }

            @Override
            public void onComplete() {
                HiLog.warn(new HiLogLabel(HiLog.LOG_APP, 0, "===demo==="), Thread.currentThread().getName()+"onComplete rx：");

            }
        });
    }
    private void getData(){

        BusinessApiManager.get().getHtmlContent("https://www.baidu.com").enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (!response.isSuccessful() || response.body() == null){
                    onFailure(null,null);
                    return;
                }
                String result = response.body();
                HiLog.warn(new HiLogLabel(HiLog.LOG_APP, 0, "===demo==="), Thread.currentThread().getName()+"网页返回结果："+result);
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                HiLog.warn(new HiLogLabel(HiLog.LOG_APP, 0, "===demo==="), "网页访问异常");
            }
        });
    }
}
