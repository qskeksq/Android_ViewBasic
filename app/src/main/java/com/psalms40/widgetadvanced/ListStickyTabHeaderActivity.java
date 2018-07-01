package com.psalms40.widgetadvanced;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

public class ListStickyTabHeaderActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> mAdapter;
    private TabHost tabHost, headerHost;
    private static final int TAB_HEADER_POSITION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
    }

    private void setView() {
        setContentView(R.layout.activity_list_sticky_tab_header);

        listView = findViewById(R.id.sticky_header_listView);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        /** tabHost 를 헤더로 세팅 */
        View headerView = getLayoutInflater().inflate(R.layout.view_tab_header, null);
        listView.addHeaderView(headerView, null, false);

        TabHost.TabContentFactory dummyFactory = new DummyContentFactory(this);
        headerHost = (TabHost) getLayoutInflater().inflate(R.layout.view_tab_header, null);
        headerHost.setup();
        headerHost.addTab(headerHost.newTabSpec("tab1").setIndicator("TAB1").setContent(dummyFactory));
        headerHost.addTab(headerHost.newTabSpec("tab2").setIndicator("TAB2").setContent(dummyFactory));
        headerHost.addTab(headerHost.newTabSpec("tab3").setIndicator("TAB3").setContent(dummyFactory));
        headerHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(!tabHost.getCurrentTabTag().equals(tabId)) {
                    tabHost.setCurrentTabByTag(tabId);
                }
            }
        });
        listView.addHeaderView(headerHost, null, false);

        /** content로 들어가 있는 실제 탭호스트 */
        tabHost = findViewById(R.id.fixed_tabhost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("TAB1").setContent(dummyFactory));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("TAB2").setContent(dummyFactory));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("TAB3").setContent(dummyFactory));
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(!headerHost.getCurrentTabTag().equals(tabId)) {
                    headerHost.setCurrentTabByTag(tabId);
                }
                setItem(tabId);
            }
        });

        listView.setAdapter(mAdapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem < TAB_HEADER_POSITION) {
                    tabHost.setVisibility(View.GONE);
                } else {
                    tabHost.setVisibility(View.VISIBLE);
                }
            }
        });
        setItem("tab1");
    }

    private void setItem(String tabId) {
        mAdapter.clear();
        for (int i = 0; i < 20; i++) {
            mAdapter.add(tabId + ":" + i);
        }
    }

    static class DummyContentFactory implements TabHost.TabContentFactory {

        private Context mContext;

        public DummyContentFactory(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public View createTabContent(String tag) {
            return new TextView(mContext);
        }
    }

}
