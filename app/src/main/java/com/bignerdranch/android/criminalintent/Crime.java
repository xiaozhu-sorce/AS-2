package com.bignerdranch.android.criminalintent;

import java.util.Date;
import java.util.UUID;

public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private Boolean mSolved;

    public Crime(){
        mId=UUID.randomUUID();
        mDate=new Date();
    }
}
