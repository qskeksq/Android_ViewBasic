package com.psalms40.widgetadvanced;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity
        extends AppCompatActivity implements View.OnClickListener {

    private Button goLayoutTransition;
    private Button goTabHost;
    private Button goLayerList;
    private Button goListStickyHeader;
    private Button goViewPagerGallery;
    private Button goCustomDrawables;
    private Intent testActivityIntent;
    private Button goMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
    }

    private void setView() {
        setContentView(R.layout.activity_main);
        goLayoutTransition = findViewById(R.id.goLayoutTransition);
        goTabHost = findViewById(R.id.goTabHost);
        goLayerList = findViewById(R.id.goLayerList);
        goListStickyHeader = findViewById(R.id.goListStickyTabHeader);
        goViewPagerGallery = findViewById(R.id.goViewPagerGallery);
        goCustomDrawables = findViewById(R.id.goCustomDrawables);
        goMenu = findViewById(R.id.goMenu);

        goLayoutTransition.setOnClickListener(this);
        goTabHost.setOnClickListener(this);
        goLayerList.setOnClickListener(this);
        goListStickyHeader.setOnClickListener(this);
        goViewPagerGallery.setOnClickListener(this);
        goCustomDrawables.setOnClickListener(this);
        goMenu.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.goLayoutTransition:
                testActivityIntent
                        = new Intent(this, LayoutTransitionActivity.class);
                break;
            case R.id.goTabHost:
                testActivityIntent
                        = new Intent(this, TabHostActivity.class);
                break;
            case R.id.goLayerList:
                testActivityIntent
                        = new Intent(this, LayerListActivity.class);
                break;
            case R.id.goListStickyTabHeader:
                testActivityIntent
                        = new Intent(this, ListStickyTabHeaderActivity.class);
                break;
            case R.id.goViewPagerGallery:
                testActivityIntent
                        = new Intent(this, ViewPagerGalleryActivity.class);
                break;
            case R.id.goCustomDrawables:
                testActivityIntent
                        = new Intent(this, CustomDrawablesActivity.class);
                break;
            case R.id.goMenu:
                testActivityIntent
                        = new Intent(this, MenuActivity.class);
                break;
            default:
                Toast.makeText(this, "뭘 클릭한거야", Toast.LENGTH_SHORT).show();
        }
        startActivity(testActivityIntent);
    }


}
