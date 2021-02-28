package com.example.demo.slice;

import com.example.demo.ResourceTable;
import com.github.boxuanjia.toycar.ToyCar;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.Image;
import timber.log.Timber;

public class ImageAbilitySlice extends AbilitySlice {

    private Image image;
    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_image);


        image = (Image)findComponentById(ResourceTable.Id_image);

        findComponentById(ResourceTable.Id_btn1).setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {

                Timber.i("加载图片....");
                ToyCar.load("https://developer.harmonyos.com/resource/image/DevEco-Studio/DevEco-Studio-0.png").into(image);

            }
        });
    }

}
