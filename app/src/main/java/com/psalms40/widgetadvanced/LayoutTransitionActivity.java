package com.psalms40.widgetadvanced;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class LayoutTransitionActivity extends AppCompatActivity {


    private ListView listView;
    private ArrayAdapter<String> listAdapter;
    private ArrayList<String> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setView();
        startEnteringAnimation();
    }


    private void setView() {
        setContentView(R.layout.activity_layout_transition);

        listView = (ListView) findViewById(R.id.listview);
        listItems = new ArrayList<String>();
        listAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(listAdapter);

        listItems.add("A");
        listItems.add("B");
        listItems.add("C");
        listItems.add("D");
        listItems.add("E");
        listItems.add("F");
        listItems.add("G");
        listItems.add("H");
        listItems.add("I");

        listAdapter.notifyDataSetChanged();
    }


    private void startEnteringAnimation() {

        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_anim);
        listView.setLayoutAnimation(controller);

    }
}
