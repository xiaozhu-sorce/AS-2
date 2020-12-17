package com.bignerdranch.android.criminalintent;

import androidx.fragment.app.Fragment;

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment creatFragment() {
        return new CrimeListFragment();
    }
}
