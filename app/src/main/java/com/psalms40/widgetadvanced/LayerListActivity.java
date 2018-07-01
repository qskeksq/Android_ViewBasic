package com.psalms40.widgetadvanced;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

public class LayerListActivity extends AppCompatActivity {

    private int[] imageList = {
            R.drawable.layer,
            R.drawable.layer2,
            R.drawable.layer3,
            R.drawable.layer4,
            R.drawable.layer5
    };
    private int current = 0;

    ImageSwitcher imageSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
    }

    private void setView() {
        setContentView(R.layout.activity_layer_list);

        imageSwitcher = findViewById(R.id.imageSwitcher);
        imageSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView iv = (ImageView) imageSwitcher.getNextView();
                current = (current+1) % imageList.length;
                Log.e("확인", current+"");
                iv.setImageResource(imageList[current]);
                imageSwitcher.showNext();
            }
        });

    }
}
