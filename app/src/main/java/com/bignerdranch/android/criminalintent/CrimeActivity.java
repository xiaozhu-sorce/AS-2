package com.bignerdranch.android.criminalintent;

import androidx.fragment.app.Fragment;

public class CrimeActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){ //获取FragmentManager
        return new CrimeFragment();
    }
}