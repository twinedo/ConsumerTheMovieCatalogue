package com.twinedo.consumerthemoviecatalogue.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.google.android.material.tabs.TabLayout;
import com.twinedo.consumerthemoviecatalogue.R;
import com.twinedo.consumerthemoviecatalogue.adapter.FavoriteTabAdapter;

public class MainActivity extends AppCompatActivity {

    //public long id;
    private int[] tabIcon = {R.drawable.ic_movie_white_24dp, R.drawable.ic_live_tv_white_24dp };
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView settings = findViewById(R.id.settings);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.movies).setIcon(tabIcon[0]));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tvShows).setIcon(tabIcon[1]));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        FavoriteTabAdapter favoriteTabAdapter = new FavoriteTabAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(favoriteTabAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        viewPager.setCurrentItem(0);
                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
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

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getBaseContext(), v);
                popupMenu.getMenuInflater().inflate(R.menu.change_language_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.action_change_settings) {
                            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                            startActivity(mIntent);
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }
}
