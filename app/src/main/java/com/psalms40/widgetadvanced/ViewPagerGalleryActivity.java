package com.psalms40.widgetadvanced;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerGalleryActivity extends AppCompatActivity {

    private ViewPager pager;
    private GalleryPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
        setPadding();
        setTransformer();
    }

    private void setView() {
        setContentView(R.layout.activity_view_pager_gallery);
        pager = findViewById(R.id.viewPager);
        adapter = new GalleryPagerAdapter();
        pager.setAdapter(adapter);
    }

    private void setPadding() {
        // 패팅 값을 적용하면 자식을 잘라낼 것인지 여부
        // true 값이면 패딩 값이 적용되지 않고 자식이 잘려나감 됨 - 다음 페이지가 안 나타남
        // false 값이면 패팅 값으로 자르지 않고 resize 함
        pager.setClipToPadding(false);
        // 넘어가는 뷰와 뷰 사이의 간격
        pager.setPageMargin(0);
        // 패딩 값 적용, pixel 이기 때문에 변환값으로 설정하거 xml에 설정하도록 한다
        pager.setPadding(50, 0, 50, 0);
    }

    private void setTransformer() {
        pager.setPageTransformer(true, new GalleryPagerTransformer());
    }

    private class GalleryPagerTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {
            ObjectAnimator in = (ObjectAnimator) AnimatorInflater.loadAnimator(ViewPagerGalleryActivity.this, R.animator.page_in);
            ObjectAnimator out = (ObjectAnimator) AnimatorInflater.loadAnimator(ViewPagerGalleryActivity.this, R.animator.page_out);

            // 페이지가 완전히 왼쪽으로 없어
            if (position < -1) {

                // 페이지가 왼쪽으로 없어지고 있을 때
            } else if (position <= 0) {
                in.setTarget(page);
                // 페이지가 오른쪽으로 없어지고 있을 때
            } else if (position <= 1 ) {
                out.setTarget(page);
                // 페이지가 오른쪽으로 없어짐
            } else {

            }
        }

    }

    private class GalleryPagerAdapter extends PagerAdapter {

        private List<View> pages;

        public GalleryPagerAdapter() {
            Log.e("ViewPagerGallery", "생성자");
            pages = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                View v = getLayoutInflater().inflate(R.layout.view_page, null);
                pages.add(v);
            }
        }

        @Override
        public void startUpdate(ViewGroup container) {
            Log.e("ViewPagerGallery", "startUpdate");
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            Log.e("ViewPagerGallery", "finishUpdate");
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = pages.get(position);
            v.findViewById(R.id.notifyChange).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notifyDataSetChanged();
                }
            });
            container.addView(v);
            Log.e("ViewPagerGallery", "instantiateItem");
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
            Log.e("ViewPagerGallery", "destroyItem");
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
            Log.e("ViewPagerGallery", "[현재 아이템]"+position);
        }

        @Override
        public Parcelable saveState() {
            Log.e("ViewPagerGallery", "saveState");
            return super.saveState();
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
            Log.e("ViewPagerGallery", "restoreState");
            super.restoreState(state, loader);
        }

        @Override
        public int getItemPosition(Object object) {
            Log.e("ViewPagerGallery", "getItemPosition");
            // notifyDataChanged 하더라도 defualt가 UNCHAGED이기 때문에 instantiateItem 호출되지 않음
            // 호출하려면 POSITION_NONE을 호출하거나 변화를 감지해서 instantiateItem가 호출될 수 있도록 해야함
            return POSITION_NONE;
        }

        @Override
        public float getPageWidth(int position) {
            return 1;
        }

        @Override
        public int getCount() {
            return pages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
