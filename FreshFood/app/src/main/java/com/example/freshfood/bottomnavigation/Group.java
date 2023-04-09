package com.example.freshfood.bottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.freshfood.R;
import com.example.freshfood.adapter.ViewPaperAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

public class Group extends AppCompatActivity {
    BottomNavigationView navigationView;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        viewPager = findViewById(R.id.viewpager);
        setNavigationView();
        setUpViewPaper();
    }
    private void setNavigationView(){
        navigationView = findViewById(R.id.navigation_bottom);
        navigationView.setOnNavigationItemSelectedListener(item -> {
            switch(item.getItemId()){
                case R.id.action_home:
                    viewPager.setCurrentItem(0);
                    Toast.makeText(this,"0",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.action_bill:
                    viewPager.setCurrentItem(1);
                    Toast.makeText(this,"1",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.action_profile:
                    viewPager.setCurrentItem(2);
                    Toast.makeText(this,"2",Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        });
    }
    private void setUpViewPaper(){
        ViewPaperAdapter viewPaperAdapter = new ViewPaperAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPaperAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        navigationView.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.action_bill).setChecked(true);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.action_profile).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}