package com.example.viewpager_tuto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.viewpager_tuto.model.HorizontalFlipTransformation;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Button btnPrevious, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnPrevious.setOnClickListener(this);
        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);

        FragmentManager manager = getSupportFragmentManager();
        FragmentCustomAdapter adapter = new FragmentCustomAdapter(manager, 3);
        viewPager.setPageTransformer(true, new HorizontalFlipTransformation());
        viewPager.setAdapter(adapter);
//        Gắn viewPager vào tabLayout
        tabLayout.setupWithViewPager(viewPager);
        setTabLayoutTitleColor();

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.colorPink));
                        btnNext.setBackgroundColor(getResources().getColor(R.color.colorPink));
                        btnPrevious.setBackgroundColor(getResources().getColor(R.color.colorPink));
                        break;
                    case 1:
                        tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.colorGreen));
                        btnNext.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                        btnPrevious.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                        break;
                    case 2:
                        tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.colorBlue));
                        btnNext.setBackgroundColor(getResources().getColor(R.color.colorBlue));
                        btnPrevious.setBackgroundColor(getResources().getColor(R.color.colorBlue));
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNext:
                if (viewPager.getCurrentItem() == 2) return;
                else {
                    viewPager.setCurrentItem((viewPager.getCurrentItem() + 1));
                    setTabLayoutTitleColor();
                }
                break;
            case R.id.btnPrevious:
                if (viewPager.getCurrentItem() == 0) return;
                else {
                    viewPager.setCurrentItem((viewPager.getCurrentItem() - 1));
                    setTabLayoutTitleColor();
                }
                break;
        }
    }

    public void setTabLayoutTitleColor() {
        switch (viewPager.getCurrentItem()) {
            case 0:
                tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.colorPink));
                break;
            case 1:
                tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.colorGreen));
                break;
            case 2:
                tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.colorBlue));
                break;
            default:
        }
    }

//    Xử lý cho nút back trên điện thoại
    public void onBackPressed() {
        if(viewPager.getCurrentItem() == 0) {
            super.onBackPressed(); /* Ăn theo back của hệ thống */
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }
}