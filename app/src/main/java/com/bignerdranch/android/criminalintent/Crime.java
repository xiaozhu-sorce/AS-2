package com.bignerdranch.android.criminalintent;

import android.widget.ImageView;

import java.util.Date;
import java.util.UUID;

public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private Boolean mSolved;
    private Boolean mRequiresPolice;

    public Crime(){
        mId=UUID.randomUUID();
        mDate=new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public Boolean isSolved() {
        return mSolved;
    }

    public void setSolved(Boolean solved) {
        mSolved = solved;
    }

    public Boolean getRequiresPolice() {
        return mRequiresPolice;
    }

    public void setRequiresPolice(Boolean requiresPolice) {
        mRequiresPolice = requiresPolice;
    }

}
