package com.psalms40.widgetadvanced;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class TabHostActivity extends AppCompatActivity {

    private TabHost testTabHost;
    private TextView tabView, tabView2, tabView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
    }

    private void setView() {
        setContentView(R.layout.activity_tab_host);

        testTabHost = findViewById(R.id.tabHost);
        tabView = (TextView) getLayoutInflater().inflate(R.layout.view_tab, testTabHost.getTabWidget(), false);
        tabView.setText("탭1");
        tabView2 = (TextView) getLayoutInflater().inflate(R.layout.view_tab, testTabHost.getTabWidget(), false);
        tabView2.setText("탭2");
        tabView3 = (TextView) getLayoutInflater().inflate(R.layout.view_tab, testTabHost.getTabWidget(), false);
        tabView3.setText("탭3");
        testTabHost.setup();
        testTabHost.addTab(testTabHost.newTabSpec("Tab1").setIndicator(tabView).setContent(R.id.tab1));
        testTabHost.addTab(testTabHost.newTabSpec("Tab2").setIndicator(tabView2).setContent(R.id.tab2));
        testTabHost.addTab(testTabHost.newTabSpec("Tab3").setIndicator(tabView3).setContent(R.id.tab3));

        testTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                switch (tabId) {
                    case "Tab1":
                        Toast.makeText(TabHostActivity.this, "태그1", Toast.LENGTH_SHORT).show();
                        break;
                    case "Tab2":
                        Toast.makeText(TabHostActivity.this, "태그2", Toast.LENGTH_SHORT).show();
                        break;
                    case "Tab3":
                        Toast.makeText(TabHostActivity.this, "태그3", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
