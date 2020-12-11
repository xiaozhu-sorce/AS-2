package com.bignerdranch.android.criminalintent;

import androidx.fragment.app.Fragment;

public abstract class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new CrimeListFragment();
    }
}
