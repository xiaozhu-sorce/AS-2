package com.bignerdranch.android.criminalintent;

import androidx.fragment.app.Fragment;

public class CrimeActivity extends SingleFragmentActivity {

    @Override
    protected Fragment creatFragment(){
        return new CrimeFragment();
    }
}