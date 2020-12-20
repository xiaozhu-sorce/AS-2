package com.bignerdranch.android.criminalintent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class CrimePagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<Crime> mCrimes;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        mViewPager=(ViewPager)findViewById(R.id.crime_view_pager);

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
    }
}
