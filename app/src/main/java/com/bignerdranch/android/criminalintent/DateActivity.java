package com.bignerdranch.android.criminalintent;

import androidx.fragment.app.Fragment;

public class DateActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new DatePickerFragment();
    }
}
