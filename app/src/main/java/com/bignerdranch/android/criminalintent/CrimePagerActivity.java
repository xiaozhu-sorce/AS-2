package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity {
    private static final String EXTRA_CRIME_ID="com.bignerdranch.android.criminalintent.crime_id";

    private ViewPager mViewPager;
    private List<Crime> mCrimes;

    private Button btn_first;
    private Button btn_last;

    public static Intent newIntent(Context packageContext, UUID crimeId){
        Intent intent=new Intent(packageContext,CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID,crimeId);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        UUID crimeId=(UUID)getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        mViewPager=(ViewPager)findViewById(R.id.crime_view_pager);

        btn_first=(Button)findViewById(R.id.btn_to_first);
        btn_last=(Button)findViewById(R.id.btn_to_last);

        btn_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(0);
            }
        });
        btn_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(mCrimes.size()-1);
            }
        });

        mCrimes=CrimeLab.get(this).getCrimes();//从CrimeLab中获取数据集，然后获取Activity的FragmentManager实例;
        FragmentManager fragmentManager=getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {//设置adapter为FragmentStatePagerAdapter的一个匿名实例。

            @NonNull
            @Override
            public Fragment getItem(int position) {//该方法首先获取数据集中指定位置的Crime实例，然后利用该实例的ID创建并返回一个经过有效配置的CrimeFragment
                Crime crime=mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {//该方法返回了数组列表中包含的列表项数目。
                return mCrimes.size();
            }
        });

        for (int i = 0;i < mCrimes.size() ; i++){
            if (mCrimes.get(i).getId().equals(crimeId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position==0){
                    btn_first.setVisibility(View.VISIBLE);
                    btn_last.setVisibility(View.INVISIBLE);
                }
                else if (position==mCrimes.size()-1){
                    btn_first.setVisibility(View.INVISIBLE);
                    btn_last.setVisibility(View.VISIBLE);
                }
                else {
                    btn_first.setVisibility(View.VISIBLE);
                    btn_last.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
